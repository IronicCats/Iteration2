package Model.GameObject.MobileObjects;

import Model.GameObject.GameObject;
import Model.Location;

import Model.Map.Map;
import Model.Map.Tile;
import Controller.Controller;

import Model.Stats.Stats;
import State.States.GameState.GameState;
import Utilities.MobileObjectUtilities.MobileObjectEnum;

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
    protected Stats stats;
    private boolean canMove;
    private ViewLocation viewLocation;
    private int sight, range;
    private int id;

    public MobileObject() {
        super();
        canMove = true;
        stats = new Stats();
        viewLocation = new ViewLocation(location.getX(), location.getY());
        map = GameState.map;
        tile = map.register(this);
        sight = 2;
        range = 2;
        id = 0;

    }
    public MobileObject(Location location, int id,  Stats stats) {
        super(location);
        canMove = true;
        viewLocation = new ViewLocation(location.getX(), location.getY());
        this.stats = stats;
        map = GameState.map;
        tile = map.register(this);
        sight = 2;
        range = 2;
        this.id = id;

    }

    public void move(int degrees){
        if(canMove) {
            canMove = false;
            location = Location.newLocation(degrees, location);
            location.setDir(degrees);
            registerTile(location);
            alert();
        }
    }
    public Map getMap(){
        return this.map;
    }

    public Stats getStats() {
        return stats;
    }

    public int getMovement() {
        return stats.getMovement(); }

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
        while(map.getTile(location).hasObject()) {
            //Waiting to register with the next tile;
        }
        tile = map.register(this);
        return tile;
    }

    public void interactWithTile() {
        tile.interact(this.location);
    }

    public Tile getTile(){
        return tile;
    }

    public int getSight() {
        return sight;
    }

    public void setSight(int sight) {
        this.sight = sight;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }


}
