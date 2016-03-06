package Model.GameObject;

import Model.Location;

/**
 * Created by Joshua Kegley on 3/1/2016.
 */
public abstract class GameObject {
    private Location location;
    public GameObject() { location = new Location(0,0);}
    public GameObject(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location l) {
        location = l;
    }

    public int getX() {
        return location.getX();
    }
    public int getY() {
        return location.getY();
    }

    public String toString() {
        return "GameObject with Location: " + location.toString();
    }
}
