package Model.GameObject.MobileObjects;

import Model.GameObject.GameObject;
import Model.Location;

import Model.Map.Map;
import Model.Map.Tile;

import Model.Stats.Stats;
import State.States.GameState.GameState;

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

    protected Map map;

    private float speed;
    private Tile tile;
    // private Nav navigation
    private Stats stats;
    private boolean canMove;
    private ViewLocation viewLocation;

    public MobileObject() {
        super();
        canMove = true;
        stats = new Stats();
        viewLocation = new ViewLocation(location.getX(), location.getY());
        map = GameState.map;
        tile = map.register(this);

    }
    public MobileObject(Location location, Stats stats) {
        super(location);
        canMove = true;
        viewLocation = new ViewLocation(location.getX(), location.getY());
        this.stats = stats;
        map = GameState.map;
        tile = map.getTile(location);
    }

    public void move(int degrees){
        location = Location.newLocation(degrees, location);
        location.setDir(degrees);
        registerTile(location);
        alert();
    }


    public Stats getStats() {
        return stats;
    }

    public int getMovement() { return stats.getMovement(); }

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


    public Tile registerTile(Location location) {
        tile.deregister();
        tile = map.register(this);
        return tile;
    }

    public void interactWithTile() {
        tile.interact();
    }



}
