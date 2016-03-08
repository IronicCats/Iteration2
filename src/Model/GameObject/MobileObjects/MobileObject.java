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
    private boolean canMove;
    private ViewLocation viewLocation;
    // private Nav navigation

    public MobileObject() {
        super();
        canMove = true;
        speed = 0;
        viewLocation = new ViewLocation(location.getX(), location.getY());
    }
    public MobileObject(Location location) {
        super(location);
        canMove = true;
        viewLocation = new ViewLocation(location.getX(), location.getY());
    }

    public void move(int degrees){
        location = Location.newLocation(degrees, location);
        location.setDir(degrees);
        alert();
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public ViewLocation getViewLocation() {
        return viewLocation;
    }

    public void setViewLocation(ViewLocation viewLocation) {
        this.viewLocation = viewLocation;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void toggleCanMove() {
        this.canMove = !this.canMove;
    }

}
