package Model.Map;

import Model.GameObject.Item.Item;
import Model.Location;
import Utilities.Subject;
import Utilities.Observer;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Map implements Subject {

    private Tile tiles[][];
    private int width;
    private int height;
    private Location spawn;
    protected Observer observer;

    public Map(Tile tiles[][], int width, int height, Location spawn){
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.spawn = spawn;
    }

    public Tile getTile(int x , int y) {

        return tiles[x][y];
    }

    public void placeItem(Item item) {
        try {
            tiles[item.getX()][item.getY()].addItem(item);
        }catch (Exception e) {
            System.out.println(e);
            System.out.println("Error while adding Item to Map");
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

    }

}
