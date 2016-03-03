package Model.GameObject.MobileObjects;

import Model.Location;

/**
 * Created by Wimberley on 3/3/16.
 */

// Holds attributes and methods common to all mobile objects
public abstract class MobileObject {

    Location location;
    // int speed? not sure how it's going to be implemented
    // private Nav navigation


    public void move(){

    }

    public Location getLocation() {
        return location;
    }
}
