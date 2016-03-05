package Model.Effects;

import Model.Stats.StatStructure;

/**
 * Created by Wimberley on 2/25/16.
 */

/* This is a long way from complete :(
   ...
 */
public class Effect {

    /*Variables*/
    private StatStructure modification;
    private ModificationEnum type;
    private long duration;

    /*Default Constructor*/
    public Effect() {
        this.modification = new StatStructure();
        this.type = ModificationEnum.REAL;
        this.duration = 0;
    }

    /*Constructor*/
    public Effect(StatStructure modification){
        duration = 0;
        this.modification = modification;
        this.type = ModificationEnum.REAL;
    }

    /*Constructor*/
    public Effect(StatStructure modification, ModificationEnum type, long duration){
        this.modification = modification;
        this.duration = duration;
        this.type = type;
    }

    public StatStructure getModification() {
        return modification;
    }
    public long getDuration() { return duration; }
    public ModificationEnum getType() { return type; }
}
