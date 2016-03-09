package Model.Stats;

/**
 * Created by broskj on 3/6/16.
 */
public class Stats {
    private int movement,
            baseMovement;

    public Stats() {
        movement = 0;
        baseMovement = 0;
    } // end default constructor

    public Stats(int movement) {
        this.baseMovement = movement;
        this.movement = baseMovement;
    } // end constructor

    public int getMovement() { return movement; }

    public void setMovement(int movement) { this.movement = movement;}

    public void resetMovement(){ this.movement = baseMovement;}
} // end class Stats
