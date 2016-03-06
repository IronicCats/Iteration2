package Model;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Location {

    private int x, y, dir;

    public Location(int x, int y){
        //some code, set dir to 0
        this.x = x;
        this.y = y;
    }

    public Location(int x, int y, int dir){
        this(x, y);
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

}
