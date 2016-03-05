package Model.Effects;

import Model.Stats.ModificationEnum;
import Model.Stats.StatStruc;

/**
 * Created by Wimberley on 2/25/16.
 */

/* This is a long way from complete :(
   ...
 */
public class Effect {

    /*Variables*/
    private StatStruc modification;
    private ModificationEnum type;
    private long duration;

    /*Default Constructor*/
    public Effect(StatStruc modification){
        duration = 0;
        this.modification = modification;
        this.type = ModificationEnum.REAL;
    }

    /*Constructor*/
    public Effect(StatStruc modification, ModificationEnum type, long duration){
        this.modification = modification;
        this.duration = duration;
        this.type = type;
    }

    public StatStruc getModification() {
        return modification;
    }
    public long getDuration() { return duration; }
    public ModificationEnum getType() { return type; }
}
