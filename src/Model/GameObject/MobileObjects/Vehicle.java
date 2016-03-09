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

    public Vehicle(Location location, Stats stats) {
        super(location, stats);
    }


    @Override
    public void move(int degrees) {
        // some code
    }
}
