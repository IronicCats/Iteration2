package Model.Map;

import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Utilities.Settings;
import Utilities.Subject;
import Utilities.Observer;
import View.ViewUtilities.Renderable;
import View.Views.ItemView;
import View.Views.MobileObjectView;

import java.awt.*;
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
    private HashMap<Item, ItemView> mapItems;


    public Map(Tile tiles[][], int width, int height, Location spawn){
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.spawn = spawn;
        map = this;
    }

    public Tile getTile(int x , int y) {
        if(x < 0 || y < 0 || x > Settings.MAPWIDTH-1 || y > Settings.MAPHEIGHT-1 ){
            return null;
        }
        return tiles[x][y];
    }
    public Tile getTile(Location location) {
        int x = location.getX();
        int y = location.getY();
        if(x < 0 || y < 0 || x > Settings.MAPWIDTH-1 || y > Settings.MAPHEIGHT-1 ){
            return null;
        }
        return tiles[x][y];
    }

    // notifies tile that Mobile object is on it
    public void register(MobileObject object){
        tiles[object.getLocation().getX()][object.getLocation().getY()].register(object);
    }

    public void deRegister(Location location){
        tiles[location.getX()][location.getY()].deregister();
    }

    public void placeItem(Item item) {
        try {
            tiles[item.getX()][item.getY()].addItem(item);

        }catch (Exception e) {
            System.out.println(e);
            System.out.println("Error while adding Item to Map");
        }
    }

    public void placeAreaEffect(AreaEffect a) {
        try {
            tiles[a.getX()][a.getY()].setAreaEffectTile(a);
        }catch (Exception e) {
            System.out.println(e);
            System.out.println("Error while adding AreaEffect to Map");
        }
    }


    public Location getSpawn(){
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
        for(Tile t[]: tiles){
            for(Tile tile: t){
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

}
