package Model.Effects;

import Model.StatStruc;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Effect {

    /*Variables*/
    private StatStruc modification;
    private long duration;

    /*Default Constructor*/
    public Effect(StatStruc modification){
        duration = 0;
        this.modification = modification;
    }

    /*Constructor*/
    public Effect(StatStruc modification , long duration){
        this.modification = modification;
        this.duration = duration;
    }

    public StatStruc getModification() {
        return modification;
    }
}
