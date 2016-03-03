package Model.GameObject.MobileObjects.Entities.Stats;

import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * Created by broskj on 3/2/16.
 */
public class PlayerStats {
    private PrimaryStats primaryStats;
    private DerivedStats derivedStats;
    private ArrayList<Effect> effects;
    private ArrayList<Long> finishTimes;

    public PlayerStats() {
        this.primaryStats = new PrimaryStats();
        this.derivedStats = new DerivedStats(primaryStats);
        this.effects = new ArrayList<>();
        this.finishTimes = new ArrayList<>();
    } // end default constructor

    public PlayerStats(StatStruc ss) {
        primaryStats = new PrimaryStats(ss);
        derivedStats = new DerivedStats(primaryStats);
        effects = new ArrayList<>();
        finishTimes = new ArrayList<>();
    } // end constructor

    public void levelUp() {
        /*
        calls levelup methods of each of its members
         */
        primaryStats.levelUp();
        derivedStats.levelUp();

    } // end levelUp

    public void kill() {
        /*
        kills the player; clears active effects (and finish times), calls
         kill methods of each of its members, and exits game if player is
         out of lives
         */
        effects.clear();
        finishTimes.clear();
        primaryStats.kill();
        derivedStats.kill();
        /*
        if(primaryStats.getLivesLeft() <= 0)
            State.setState(KillState.state);
        else{
            controller.getPlayer().getInventory().dropAll();
            controller.getPlayer().setX(controller.getMap().getSpawn().getX());
            controller.getPlayer().setY(controller.getMap().getSpawn().getY());
            controller.getPlayer().getNavigation().setGoalX(controller.getMap().getSpawn().getPixelX());
            controller.getPlayer().getNavigation().setGoalY(controller.getMap().getSpawn().getPixelY());
        }
         */
    } // end kill()

    public void applyEffect(Effect e) {
        /*
        take in Effect and apply it to character

        USAGE:
            This method is passed an Effect (which contains a StatStructure array).  The StatStructure contains a list
             of the skills which are to be modified and by how much.

            The Effect's duration affects what happens when the Effect is applied.  If the duration is 0, then the
             effect is meant to happen instantaneously and not persist.  This is the case for taking damage or using
             mana, for example.  Other Effects with durations greater than zero are added to an ArrayList of Effects
             and its finish time (calculated as System.currentTimeInMillis() + duration) is added to a parallel
             ArrayList of system times.  The finish time is checked each game tick to check for expired Effects, and on
             expiration, the effect is removed by adding its negative value to its respective stat.

            The entire SS array is traversed (though its size is likely to be 1 outside of initial character creation),
             and at each element a switch statement will execute code unique to that stat, i.e. a StatStructure with
             one element containing the pair {Strength, 2} will add 2 to the Strength stat (with BaseStrength being
             retained) for the duration of the effect.

        DESIGNATED VALUES:
            The following StatStructure StatsEnum/Value pairs are unique:
                {StatsEnum.LEVEL, 1} -> Level Up (experience is set to 0)
                {StatsEnum.LIVES_LEFT, -1} -> Kill Player
         */
        if(e.getDuration() > 0) {
            effects.add(e);
            finishTimes.add(System.currentTimeMillis() + e.getDuration());
        }

        for(StatsEnum s : e.getModification().getKeySet()) {
            switch (s){
                // primary stats
                case LIVES_LEFT:
                    for(int i = 0; i < Math.abs(e.getModification().getStat(s)); i++)
                        kill();
                    derivedStats.update();
                    break;
                case STRENGTH:
                case AGILITY:
                case INTELLECT:
                case HARDINESS:
                case EXPERIENCE:
                case MOVEMENT:
                    primaryStats.modifyStat(s, e.getType(), e.getModification().getStat(s));
                    derivedStats.update();
                    break;
                // derived stats
                case LEVEL:
                    primaryStats.modifyStat(StatsEnum.EXPERIENCE, null, 0);
                    for(int i = 0; i < e.getModification().getStat(s); i++)
                        levelUp();
                    break;
                case LIFE:
                    if(e.getModification().getStat(s) + getLife() > getBaseLife())
                        derivedStats.resetLife();
                    else if((e.getModification().getStat(s) + derivedStats.getLife()) <= 0) {
                        kill();
                    }
                    else
                        derivedStats.modifyStat(s, e.getType(), e.getModification().getStat(s));
                    break;
                case MANA:
                    if(e.getModification().getStat(s) + getMana() > getBaseMana())
                        derivedStats.resetMana();
                    else if(e.getModification().getStat(s) + derivedStats.getMana() <= 0) {
                        derivedStats.emptyMana();
                    }
                    else
                        derivedStats.modifyStat(s, e.getType(), e.getModification().getStat(s));
                    break;
                case OFFENSIVE_RATING:
                case DEFENSIVE_RATING:
                case ARMOR_RATING:
                    primaryStats.modifyStat(s, e.getType(), e.getModification().getStat(s));
                    break;
                // default
                default:
                    break;
            }
        }
    } // end applyEffect

    public PrimaryStats getPrimaryStats(){return primaryStats;}
    public DerivedStats getDerivedStats(){return derivedStats;}

    public int getLivesLeft() { return primaryStats.getLivesLeft(); }
    public int getBaseLives() { return primaryStats.getBaseLives(); }
    public int getStrength() { return primaryStats.getStrength(); }
    public int getBaseStr() { return primaryStats.getBaseStr(); }
    public int getAgility() { return primaryStats.getAgility(); }
    public int getBaseAgi() { return primaryStats.getBaseAgi(); }
    public int getIntellect() { return primaryStats.getIntellect(); }
    public int getBaseIntel() { return primaryStats.getBaseIntel(); }
    public int getHardiness() { return primaryStats.getHardiness(); }
    public int getBaseHard() { return primaryStats.getBaseHard(); }
    public int getExperience() { return primaryStats.getExperience(); }
    public int getMovement() { return primaryStats.getMovement(); }
    public int getBaseMovement() { return primaryStats.getBaseMovement(); }
    public int getLevel() { return derivedStats.getLevel(); }
    public int getLife() { return derivedStats.getLife(); }
    public int getBaseLife() { return derivedStats.getBaseLife(); }
    public int getMana() { return derivedStats.getMana(); }
    public int getBaseMana() { return derivedStats.getBaseMana(); }
    public int getOffensiveRating() { return derivedStats.getOffensiveRating(); }
    public int getDefensiveRating() { return derivedStats.getDefensiveRating(); }
    public int getArmorRating() { return derivedStats.getArmorRating(); }
} // end class PlayerStats
