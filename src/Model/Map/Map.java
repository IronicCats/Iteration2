package Model.Map;

import Model.Abilities.Abilities;
import Model.Abilities.DirectAbility;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Utilities.MobileObjectUtilities.MobileObjectFactory;
import Utilities.Observer;
import Utilities.RespawnQueue;
import Utilities.Settings;
import Utilities.Subject;
import View.Views.ItemView;
import View.Views.MobileObjectView;

import java.util.HashMap;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Map implements Subject {

    public static Map map;
    private Tile tiles[][];
    private int width;
    private int height;
    private Location spawn;
    protected Observer observer;

    private HashMap<MobileObject, MobileObjectView> mobileObjects;
    private RespawnQueue respawnQueue;
    private HashMap<Item, ItemView> mapItems;


    public Map(Tile tiles[][], int width, int height, Location spawn) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.spawn = spawn;
        map = this;
        respawnQueue = new RespawnQueue();
        respawnQueue.registerMap(this);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x > Settings.MAPWIDTH - 1 || y > Settings.MAPHEIGHT - 1) {
            return null;
        }
        return tiles[x][y];
    }

    public Tile getTile(Location location) {
        int x = location.getX();
        int y = location.getY();
        if (x < 0 || y < 0 || x > Settings.MAPWIDTH - 1 || y > Settings.MAPHEIGHT - 1) {
            return null;
        }
        return tiles[x][y];
    }

    // notifies tile that Mobile object is on it
    public Tile register(MobileObject object) {
        return tiles[object.getX()][object.getY()].register(object);
    }

    public void deRegister(Location location) {
        tiles[location.getX()][location.getY()].deregister();
    }

    public void placeItem(Item item) {
        try {
            tiles[item.getX()][item.getY()].addItem(item);
            tiles[item.getX()][item.getY()].alert();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void placeAreaEffect(AreaEffect a) {
        try {
            tiles[a.getX()][a.getY()].setAreaEffectTile(a);
            tiles[a.getX()][a.getY()].alert();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void carryAttack(Character c, Abilities a) {
        if (a instanceof DirectAbility) {
            getTile(Location.newLocation(c.getDir(), c.getLocation())).receiveAttack(c, a);
        } else {
            System.out.println("Not a Direct Ability");
        }
    }

    public void carryInteraction(MobileObject mo) {
        Location destination = Location.newLocation(mo.getDir(), mo.getLocation());
        if (isInMap(destination)) {
            System.out.println("Tile within map");
            getTile(destination).receiveInteraction(mo);
        }
    }

    public Location getSpawn() {
        return spawn;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void alert() {
        for (Tile t[] : tiles) {
            for (Tile tile : t) {
                tile.alert();
            }
        }
    }

    public void alert(Tile tile) {
        tile.alert();
    }

    public HashMap<MobileObject, MobileObjectView> getMobileObjects() {
        return mobileObjects;
    }

    public void setMobileObjects(HashMap<MobileObject, MobileObjectView> mobileObjects) {
        this.mobileObjects = mobileObjects;
    }

    public HashMap<Item, ItemView> getMapItems() {
        return mapItems;
    }

    public void setMapItems(HashMap<Item, ItemView> mapItems) {
        this.mapItems = mapItems;
    }

    public boolean isInMap(Location l) {
        if (l.getX() < 0 || l.getX() > map.getWidth() || l.getY() < 0 || l.getY() > map.getHeight()) {
            System.out.println("Trying to interact outside map");
            return false;
        }
        return true;
    }

    public void respawn(MobileObject object) {
        object.resetLocation();
        mobileObjects.put(object, MobileObjectFactory.makeAsset(object.getID(), object));
    } // end respawn

    public void addToRespawnQueue(MobileObject object) {
        mobileObjects.remove(object);
        respawnQueue.push(object);
    } // end addToRespawnQueue

    @Override
    public String toString() {
        return "Map{" +
                ", width=" + width +
                ", height=" + height +
                ", spawn=" + spawn +
                '}';
    }
}
