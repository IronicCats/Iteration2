package Model.GameObject.MobileObjects;

import Model.GameObject.GameObject;
import Model.Location;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This class is the parent class for all objects that will be moving in the game.
e.g., Pet, Projectile, Vehicle, Entity.
It holds all common attributes between the above listed objects.
Also inherits location from GameObject
*/
public abstract class MobileObject extends GameObject{

    private float speed;
    // private Nav navigation

    public MobileObject() {
        super();
        speed = 0;
    }
    public MobileObject(Location location) {
        super(location);
    }

    public void move(){

    }

    public Location getLocation() {
        return location;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
