package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.Location;

/**
 * Created by Wimberley on 3/3/16.
 */

// Vehicle class!
public class Vehicle extends MobileObject {

    private Effect effect; // used to hold added defensive rating from vehicle. Also attack rating if hitting another entity

    public Vehicle(Location location) {
        super(location);
    }


    @Override
    public void move() {
        // some code
    }
}
