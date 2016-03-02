package Model.GameObject.Entity.Stats;

import Model.GameObject.Entity.StatsEnum;

/**
 * Created by broskj on 3/2/16.
 */
public class PrimaryStats {
    private int livesLeft;
    private int strength;
    private int agility;
    private int intellect;
    private int hardiness;
    private int experience;
    private int movement;
    private int baseLives,
            baseStr,
            baseAgi,
            baseIntel,
            baseHard,
            baseMovement;
    private int xpThreshhold;
    private double xpMultiplier,
            statMultiplier;

    public PrimaryStats() {
        this.livesLeft = 0;
        this.strength = 0;
        this.agility = 0;
        this.intellect = 0;
        this.hardiness = 0;
        this.baseLives = 0;
    } // end default constructor

    public void levelUp() {
        /*
        reset experience and lives left, modify xp threshhold and stats
            retain leftover experience
         */
        experience = experience - xpThreshhold;
        xpThreshhold *= xpMultiplier;
        livesLeft = baseLives;

        baseStr *= statMultiplier;
        baseAgi*= statMultiplier;
        baseIntel *= statMultiplier;
        baseHard *= statMultiplier;

        strength = baseStr;
        agility = baseAgi;
        intellect = baseIntel;
        hardiness = baseHard;
    } // end levelUp

    public void kill() {
        /*
        kills the player; decrements lives, and resets boosted stats
         */
        livesLeft--;

        strength = baseStr;
        agility = baseAgi;
        intellect = baseIntel;
        hardiness = baseHard;
        movement = baseMovement;
    } // end kill

    public PrimaryStats(StatStruc ss) {
        /*
        initialize stats
         */
        baseLives = ss.getStat(StatsEnum.LIVES_LEFT);
        baseStr = ss.getStat(StatsEnum.STRENGTH);
        baseAgi = ss.getStat(StatsEnum.AGILITY);
        baseIntel = ss.getStat(StatsEnum.INTELLECT);
        baseHard = ss.getStat(StatsEnum.HARDINESS);
        baseMovement = ss.getStat(StatsEnum.MOVEMENT);

        livesLeft = baseLives;
        strength = baseStr;
        agility = baseAgi;
        intellect = baseIntel;
        hardiness = baseHard;
        experience = ss.getStat(StatsEnum.EXPERIENCE);
        movement = baseMovement;

        /*
        predefined multipliers
            xpThreshhold: total amount of xp until level up
            xpMultiplier: amount xpThreshhold is multiplied by on level up
            statMultiplier: amount stats are multiplied by on level up
         */
        xpThreshhold = 10;
        xpMultiplier = 1.5;
        statMultiplier = 1.2;
    } // end constructor

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
    } // end modifyStats

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
    public int getMovement() { return movement; }
    public int getBaseMovement() { return baseMovement; }
} // end class PrimaryStats
