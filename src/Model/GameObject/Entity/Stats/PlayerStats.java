package Model.GameObject.Entity.Stats;

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
} // end class PlayerStats
