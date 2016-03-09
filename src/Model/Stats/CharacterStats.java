package Model.Stats;

import Model.Effects.Effect;
import Model.Effects.EquipmentModification;
import Model.Effects.ModificationEnum;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by broskj on 3/6/16.
 *
 * Stats class for use by Player and NPC (e.g., enemy)
 */
public class CharacterStats extends Stats implements Subject {
    // primary stats
    private int livesLeft,
            strength,
            agility,
            intellect,
            hardiness,
            experience,
            movement;
    // derived stats
    private int level,
            life,
            mana,
            offensiveRating,
            defensiveRating,
            armorRating;
    // base stats
    private int baseLives,
            baseStr,
            baseAgi,
            baseIntel,
            baseHard,
            baseMovement,
            baseLife,
            baseMana;
    private int equippedWeapon, equippedArmor;
    private int xpThreshold;
    private double xpMultiplier,
            statMultiplier;
    private ArrayList<Effect> effects;
    private ArrayList<Long> finishTimes;
    private ArrayList<EquipmentModification> equipmentModifications;
    private ArrayList<Observer> observers;

    public CharacterStats() {
        super();

        level = 1;
        baseLives = 0;
        baseStr = 0;
        baseAgi = 0;
        baseIntel = 0;
        baseHard = 0;
        experience = 0;
        baseLives = 0;
        baseLife = baseHard + level;
        baseMana = baseIntel + level;

        life = baseLife;
        mana = baseMana;

        equippedWeapon = 0;
        equippedArmor = 0;
        armorRating = 0;
        offensiveRating = equippedWeapon + strength + level;
        defensiveRating = agility + level;
        armorRating = equippedArmor + hardiness;

        equipmentModifications = new ArrayList<>();
        observers = new ArrayList<>();

        /*
        predefined multipliers
            xpThreshold: total amount of xp until level up
            xpMultiplier: amount xpThreshold is multiplied by on level up
            statMultiplier: amount stats are multiplied by on level up
         */
        xpThreshold = 10;
        xpMultiplier = 1.5;
        statMultiplier = 1.2;

        this.effects = new ArrayList<>();
        this.finishTimes = new ArrayList<>();
    } // end default constructor

    public CharacterStats(StatStructure ss) {
        super(ss.getStat(StatsEnum.MOVEMENT));

        level = 1;
        baseLives = ss.getStat(StatsEnum.LIVES_LEFT);
        baseStr = ss.getStat(StatsEnum.STRENGTH);
        baseAgi = ss.getStat(StatsEnum.AGILITY);
        baseIntel = ss.getStat(StatsEnum.INTELLECT);
        baseHard = ss.getStat(StatsEnum.HARDINESS);
        baseLife = baseHard + level;
        baseMana = baseIntel + level;

        livesLeft = baseLives;
        strength = baseStr;
        agility = baseAgi;
        intellect = baseIntel;
        hardiness = baseHard;
        experience = ss.getStat(StatsEnum.EXPERIENCE);
        life = baseLife;
        mana = baseMana;

        equippedWeapon = 0;
        equippedArmor = 0;
        armorRating = 0;
        offensiveRating = equippedWeapon + strength + level;
        defensiveRating = agility + level;
        armorRating = equippedArmor + hardiness;

        equipmentModifications = new ArrayList<>();
        observers = new ArrayList<>();

        /*
        predefined multipliers
            xpThreshold: total amount of xp until level up
            xpMultiplier: amount xpThreshold is multiplied by on level up
            statMultiplier: amount stats are multiplied by on level up
         */
        xpThreshold = 10;
        xpMultiplier = 1.5;
        statMultiplier = 1.2;

        this.effects = new ArrayList<>();
        this.finishTimes = new ArrayList<>();
    } // end constructor

    public void tick() {
        checkExpiredEffect();
        checkLevelUp();
    } // end tick

    public void update() {
        /*
        method to be called with each stat update
        recomputes derived stats
         */
        baseLife = hardiness + level;
        baseMana = intellect + level;
        offensiveRating = equippedWeapon + strength + level;
        defensiveRating = agility + level;
        armorRating = equippedArmor + hardiness;

        alert();
    } // end update

    public void checkExpiredEffect() {
        /*
        Stats game tick.
        Each tick, check for expired Effects and check if player's XP is greater than the threshhold to level up
         */
        if (!effects.isEmpty()) {
            for (int i = effects.size()-1; i >= 0; --i) {
                if (System.currentTimeMillis() >= finishTimes.get(i)) {
                    System.out.println("Effect expired.");
                    removeEffect(effects.get(i));
                    effects.remove(i);
                    finishTimes.remove(i);
                }
            }
        }
    } // end checkExpiredEffect

    public void checkLevelUp() {
        /*
        check for level up
         */
        if(experience >= xpThreshold) {
            levelUp();
        }
    } // end checkLevelUp

    public void levelUp() {
        /*
        reset experience and lives left, modify xp threshhold and stats
            retain leftover experience
        increase level by one and reset life and mana to max
         */
        System.out.println("Level up!");

        level++;

        experience = experience - xpThreshold;
        xpThreshold *= xpMultiplier;
        livesLeft = baseLives;

        baseStr *= statMultiplier;
        baseAgi *= statMultiplier;
        baseIntel *= statMultiplier;
        baseHard *= statMultiplier;

        strength = baseStr;
        agility = baseAgi;
        intellect = baseIntel;
        hardiness = baseHard;

        life = baseLife;
        mana = baseMana;

        update();
    } // end levelUp

    public void kill() {
        /*
        kills the player; clears active effects (and finish times), calls
         kill methods of each of its members, and exits game if player is
         out of lives
        decrements lives, resets any boosts from stats
         */
        System.out.println("Oh dear, you are dead!");
        effects.clear();
        finishTimes.clear();

        livesLeft--;

        strength = baseStr;
        agility = baseAgi;
        intellect = baseIntel;
        hardiness = baseHard;
        movement = baseMovement;

        life = baseLife;
        mana = baseMana;

        update();
    } // end kill

    public void applyEffect(Effect[] e) {
        for (Effect effect : e){
            applyEffect(effect);
        }
    } // end applyEffect

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
            //System.out.println("Effect to be applied: " + s + " by " + e.getModification().getStat(s) + " for duration " + e.getDuration());
            switch (s){
                // primary stats
                case LIVES_LEFT:
                    if(e.getModification().getStat(s) < 0) {
                        for (int i = 0; i < Math.abs(e.getModification().getStat(s)); i++) {
                            kill();
                        }
                    } else {
                        modifyStat(s, e.getType(), e.getModification().getStat(s));
                    }
                    break;
                case STRENGTH:
                case AGILITY:
                case INTELLECT:
                case HARDINESS:
                case EXPERIENCE:
                case MOVEMENT:
                    modifyStat(s, e.getType(), e.getModification().getStat(s));
                    break;
                // derived stats
                case LEVEL:
                    if(e.getModification().getStat(s) > 0) {
                        for (int i = 0; i < e.getModification().getStat(s); i++) {
                            levelUp();
                        }
                    }
                    break;
                case LIFE:
                    if(e.getModification().getStat(s) + life > baseLife)
                        resetLife();
                    else if((e.getModification().getStat(s) + life) <= 0) {
                        kill();
                    }
                    else
                        modifyStat(s, e.getType(), e.getModification().getStat(s));
                    break;
                case MANA:
                    if(e.getModification().getStat(s) + mana > baseMana)
                        resetMana();
                    else if(e.getModification().getStat(s) + mana <= 0) {
                        emptyMana();
                    }
                    else
                        modifyStat(s, e.getType(), e.getModification().getStat(s));
                    break;
                case OFFENSIVE_RATING:
                case DEFENSIVE_RATING:
                case ARMOR_RATING:
                    break;
                // default
                default:
                    break;
            }
        }
        update();
    } // end applyEffect

    public void removeEffect(Effect e) {
        for(StatsEnum s : e.getModification().getKeySet()) {
            //System.out.println("Effect to be removed: " + s + " by " + e.getModification().getStat(s) + " for duration " + e.getDuration());
            switch (s){
                // primary stats
                case LIVES_LEFT:
                    if(e.getModification().getStat(s) < 0) {
                        for (int i = 0; i < Math.abs(e.getModification().getStat(s)); i++) {
                            kill();
                        }
                    } else {
                        modifyStat(s, e.getType(), -1 * e.getModification().getStat(s));
                    }
                    break;
                case STRENGTH:
                case AGILITY:
                case INTELLECT:
                case HARDINESS:
                case EXPERIENCE:
                case MOVEMENT:
                    modifyStat(s, e.getType(), -1 * e.getModification().getStat(s));
                    break;
                // derived stats
                case OFFENSIVE_RATING:
                case DEFENSIVE_RATING:
                case ARMOR_RATING:
                    break;
                // default
                default:
                    break;
            }
        }
        update();
    } // end removeEffect

    public void modifyStat(StatsEnum s, ModificationEnum m, int amount) {
        switch (s) {
            case STRENGTH:
                if(m.equals(ModificationEnum.PERCENT))
                    this.strength += this.strength * amount/100;
                else
                    this.strength += amount;
                break;
            case AGILITY:
                if(m.equals(ModificationEnum.PERCENT))
                    this.agility += this.agility * amount/100;
                else
                    this.agility += amount;
                break;
            case INTELLECT:
                if(m.equals(ModificationEnum.PERCENT))
                    this.intellect += this.intellect * amount/100;
                else
                    this.intellect += amount;
                break;
            case HARDINESS:
                if(m.equals(ModificationEnum.PERCENT))
                    this.hardiness += this.hardiness * amount/100;
                else
                    this.hardiness += amount;
                break;
            case EXPERIENCE:
                if(m.equals(ModificationEnum.PERCENT))
                    this.experience += this.experience * amount/100;
                else
                    this.experience += amount;
                break;
            case MOVEMENT:
                if(m.equals(ModificationEnum.PERCENT))
                    this.movement += this.movement * amount/100;
                else
                    this.movement += amount;
                break;
            case LIVES_LEFT:
                this.livesLeft += livesLeft;
                if(livesLeft > baseLives)
                    livesLeft = baseLives;
                break;
            default:
                break;
        }
        System.out.println(s + " modified by " + amount + " (" + m + ").");
        update();
    } // end modifyStats

    public void applyEquipmentModification(EquipmentModification e) {
        equipmentModifications.add(e);
        if(e.hasWeaponValue()) {
            equippedWeapon += e.getWeaponRating();
            System.out.println("Weapon modification applied: " + e.getWeaponRating());
        }
        if(e.hasArmorValue()) {
            equippedArmor += e.getArmorRating();
            System.out.println("Armor modification applied: " + e.getArmorRating());
        }
        applyEffect(new Effect(e.getModification(), e.getType(), 0));

        update();
    } // end applyEquipmentModification

    public void removeEquipmentModification(EquipmentModification e) {
        equipmentModifications.remove(e);
        if(e.hasWeaponValue()) {
            equippedWeapon -= e.getWeaponRating();
            System.out.println("Weapon modification removed: " + e.getWeaponRating());
        }
        if(e.hasArmorValue()) {
            equippedArmor -= e.getArmorRating();
            System.out.println("Armor modification removed: " + e.getArmorRating());
        }
        removeEffect(new Effect(e.getModification(), e.getType(), 0));

        update();
    } // end removeEquipmentModification

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    } // end addObserver

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    } // end removeObserver

    @Override
    public void alert() {
        for(Observer o : observers) {
            o.update();
        }
    } // end alert

    public int getLivesLeft() { return livesLeft; }
    public int getBaseLives() { return baseLives; }
    public int getStrength() { return strength; }
    public int getBaseStr() { return baseStr; }
    public int getAgility() { return agility; }
    public int getBaseAgi() { return baseAgi; }
    public int getIntellect() { return intellect; }
    public int getBaseIntel() { return baseIntel; }
    public int getHardiness() { return hardiness; }
    public int getBaseHard() { return baseHard; }
    public int getExperience() { return experience; }
    public int getBaseMovement() { return baseMovement; }
    public int getXpThreshhold() { return xpThreshold; }
    public int getLevel() { return level; }
    public int getLife() { return life; }
    public int getBaseLife() { return baseLife; }
    public int getMana() { return mana; }
    public int getBaseMana() { return baseMana; }
    public int getOffensiveRating() { return offensiveRating; }
    public int getDefensiveRating() { return defensiveRating; }
    public int getArmorRating() { return armorRating; }
    public void resetLife() { this.life = this.baseLife; }
    public void resetMana() { this.mana = this.baseMana; }
    public void emptyMana() { this.mana = 0; }
} // end class CharacterStats
