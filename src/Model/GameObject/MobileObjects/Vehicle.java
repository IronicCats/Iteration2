package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.Location;
import Model.Stats.Stats;

/**
 * Created by Wimberley on 3/3/16.
 */

// Vehicle class!
public class Vehicle extends MobileObject {

    private Effect effect; // used to hold added defensive rating from vehicle. Also attack rating if hitting another entity

    public Vehicle(Location location, int id, Stats stats) {
        super(location, id, stats);
    }

    public void interact(){

    }

    @Override
    public void move(int degrees) {
        // some code
    }
}
