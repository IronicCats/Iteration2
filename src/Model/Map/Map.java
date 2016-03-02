package Model.Map;

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
        if(x < 0 || y < 0 || x >= width  || y >= height){
            return tiles[2][2];
        }
        return tiles[x][y];
    }

    public Location getSpawn(){
        return spawn;
    }

    public int getWidth() {

        return width * 2;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height * 2;
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
