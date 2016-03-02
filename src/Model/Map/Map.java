package Model.Map;

import Model.Location;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Map {
    private Tile tiles[][];
    private int width;
    private int height;
    private Location spawn;

    public Map(Tile tiles[][], int width, int height, Location spawn){
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this. spawn = spawn;
    }

    public Tile getTile(int x , int y) {
        if(x < 0 || y < 0 || x >= width  || y >= height){
            return tiles[2][2];
        }
        return tiles[x][y];
    }

    /*public Tile getTile(Location location) {
        return getTile(location.getX(), location.getY());
    }*/

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


}
