package Model.GameObject.MobileObjects;

import Model.GameObject.GameObject;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Model.Stats.Stats;
import State.States.GameState.GameState;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This class is the parent class for all objects that will be moving in the game.
e.g., Pet, Projectile, Vehicle, Entity.
It holds all common attributes between the above listed objects.
Also inherits location from GameObject
*/
public abstract class MobileObject extends GameObject {

    protected Map map;

    private float speed;
    protected Tile tile;
    // private Nav navigation
    protected Stats stats;
    protected boolean canMove;
    private Location baseLocation;
    private ViewLocation viewLocation;
    private int view, range;
    private int id;

    public MobileObject() {
        super();
        canMove = true;
        stats = new Stats();
        viewLocation = new ViewLocation(location.getX(), location.getY());
        baseLocation = new Location(location.getX(), location.getY());
        map = GameState.map;
        tile = map.register(this);
        view = 2;
        range = 2;
        id = 0;
    }

    public MobileObject(Location location, int id, Stats stats) {
        super(location);
        canMove = true;
        viewLocation = new ViewLocation(location.getX(), location.getY());
        baseLocation = new Location(location.getX(), location.getY());
        this.stats = stats;
        map = GameState.map;
        tile = map.register(this);
        view = 2;
        range = 2;
        this.id = id;
    }

    public void move(int degrees) {
        if (location.getDir() == degrees) {
            if (canMove) {
                canMove = false;
                location = Location.newLocation(degrees, location);
                location.setDir(degrees);
                registerTile(location);
                alert();
            }
        }
        else {
            face(degrees);
        }
    }

    public void face(int degrees) {
        location.setDir(degrees);
        alert();
    }

    public Map getMap() {
        return this.map;
    }

    public Stats getStats() {
        return stats;
    }

    public int getMovement() {
        return stats.getMovement();
    }

    public ViewLocation getViewLocation() {
        return viewLocation;
    }

    public void setViewLocation(ViewLocation viewLocation) {
        this.viewLocation = viewLocation;
    }

    public void updateViewLocation(float x, float y) {
        viewLocation.setX(x);
        viewLocation.setY(y);
    }

    public void resetLocation() {
        this.viewLocation = new ViewLocation(baseLocation.getX(), baseLocation.getY());
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }


    public Tile registerTile(Location location) {
        deregister();
        tile = map.register(this);
        return tile;
    }

    public void deregister() {
        if(tile != null) {
            tile.deregister();
            tile = null;
        }
    }

    public void interactWithTile() {
        tile.interact();
    }

    public Tile getTile() {
        return tile;
    }


    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getRange() {
        return range;
    }


    public void setRange(int range) {
        this.range = range;
    }

    public MobileObjectEnum getID() {
        return MobileObjectEnum.values()[id];
    }

    public void interact(MobileObject mo) {
        DisplayMessage.addMessage(new GameMessage("No Interaction Possible", 2));
    }

    public Location getBaseLocation() {
        return baseLocation;
    }
}
