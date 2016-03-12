package Model.Stats;

import Model.Effects.Effect;
import Model.Effects.ModificationEnum;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by broskj on 3/5/16.
 */
public class PetStats extends Stats implements Subject {

    private ArrayList<Effect> effects;
    private ArrayList<Long> finishTimes;
    private ArrayList<Observer> observers;
    private int movement;

    public PetStats() {
        effects = new ArrayList<>();
        finishTimes = new ArrayList<>();
        observers = new ArrayList<>();
        movement = 3;
    } // end default constructor

    public PetStats(StatStructure ss) {
        effects = new ArrayList<>();
        finishTimes = new ArrayList<>();
        observers = new ArrayList<>();
        movement = ss.getStat(StatsEnum.MOVEMENT);
    } // end constructor

    public void tick() {
        checkExpiredEffects();
    } // end tick

    public void checkExpiredEffects() {
        /*
        Stats game tick.
        Each tick, check for expired Effects and check if player's XP is greater than the threshhold to level up
         */
        if (!effects.isEmpty()) {
            for (int i = effects.size()-1; i >= 0; --i) {
                if (System.currentTimeMillis() >= finishTimes.get(i)) {
                    removeEffect(effects.get(i));
                    effects.remove(i);
                    finishTimes.remove(i);
                }
            }
        }
    } // checkExpiredEffects

    public void applyEffect(Effect[] e) {
        for (Effect effect : e) {
            applyEffect(effect);
        }
    } // end applyEffect

    public void applyEffect(Effect e) {
        if(e.getDuration() > 0) {
            effects.add(e);
            finishTimes.add(System.currentTimeMillis() + e.getDuration());
        }

        for (StatsEnum s : e.getModification().getKeySet()) {
            switch (s) {
                case MOVEMENT:
                    modifyStat(s, e.getType(), e.getModification().getStat(s));
                    break;
                default:
                    break;
            }
        }
    } // end applyEffect

    public void removeEffect(Effect e) {
        for (StatsEnum s : e.getModification().getKeySet()) {
            System.out.println("Effect to be removed: " + s + " by " + e.getModification().getStat(s) + " for duration " + e.getDuration());
            switch (s) {
                case MOVEMENT:
                    modifyStat(s, e.getType(), -1 * e.getModification().getStat(s));
                    break;
                default:
                    break;
            }
        }
    } // end removeEffect

    public void modifyStat(StatsEnum s, ModificationEnum m, int amount) {
        switch(s) {
            case MOVEMENT:
                if(m.equals(ModificationEnum.PERCENT))
                    this.movement += this.movement * amount/100;
                else
                    this.movement += amount;
                break;
            default:
                break;
        }
        alert();
    } // end modifyStat

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
} // end class PetStats
