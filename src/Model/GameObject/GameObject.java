package Model.GameObject;

import Model.Location;

/**
 * Created by Joshua Kegley on 3/1/2016.
 */
public abstract class GameObject {
    private Location location;


    public GameObject(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location l) {
        location = l;
    }

    public String toString() {
        return "GameObject with Location: " + location.toString();
    }
}
