package Model.Stats;

import Model.Effects.ModificationEnum;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by broskj on 3/2/16.
 */
public class DerivedStats implements Subject {
    private PrimaryStats primaryStats;
    private int level;
    private int life;
    private int mana;
    private int offensiveRating;
    private int defensiveRating;
    private int armorRating;
    private int baseLife,
            baseMana;
    private ArrayList<Observer> observers;

    public DerivedStats(PrimaryStats ps) {
        /*
        initialize stats
         */
        primaryStats = ps;
        level = 1;
        baseLife = ps.getHardiness() + level;
        baseMana = ps.getIntellect() + level;

        life = baseLife;
        mana = baseMana;
        defensiveRating = ps.getAgility() + level;

        offensiveRating = 0;
        armorRating = 0;

        observers = new ArrayList<>();
    } // end constructor

    /*
    implement subject methods
     */
    @Override
    public void addObserver(Utilities.Observer o) { observers.add(o); }

    @Override
    public void removeObserver(Utilities.Observer o) { observers.remove(o); }

    @Override
    public void alert() {
        for (Observer observer : observers) {
            observer.update();
        }
    } // end alert

    public void levelUp() {
        /*
        increase level and reset health and mana
         */
        level++;
        update();
        life = baseLife;
        mana = baseMana;
    } // end levelUp

    public void kill() {
        /*
        kills the player; resets life and mana
         */
        life = baseLife;
        mana = baseMana;
    } // end kill

    public void update() {
        /*
        method to be called with each stat update
        recomputes derived stats
         */
        baseLife = primaryStats.getHardiness() + level;
        baseMana = primaryStats.getIntellect() + level;
        //offensiveRating = equipmentStats.getWeaponStats() + primaryStats.getBaseStr() + level;
        defensiveRating = primaryStats.getAgility() + level;
        //armorRating = equipmentStats.getArmorStats() + primaryStats.getBaseHard();
    } // end update

    public void modifyStat(StatsEnum s, ModificationEnum m, int amount) {
        switch(s) {
            case LIFE:
                if(m.equals(ModificationEnum.PERCENT))
                    this.life += this.life * amount/100;
                else
                    this.life += amount;
                break;
            case MANA:
                if(m.equals(ModificationEnum.PERCENT))
                    this.mana += this.mana * amount/100;
                else
                    this.mana += amount;
                break;
            case OFFENSIVE_RATING:
                if(m.equals(ModificationEnum.PERCENT))
                    this.offensiveRating += this.offensiveRating * amount/100;
                else
                    this.offensiveRating += amount;
                break;
            case DEFENSIVE_RATING:
                if(m.equals(ModificationEnum.PERCENT))
                    this.defensiveRating += this.defensiveRating * amount/100;
                else
                    this.defensiveRating += amount;
                break;
            case ARMOR_RATING:
                if(m.equals(ModificationEnum.PERCENT))
                    this.armorRating += this.armorRating * amount/100;
                else
                    this.armorRating += amount;
                break;
            default:
                break;
        }
    } // end modifyStat

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
} // end class DerivedStats
