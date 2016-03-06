package Model;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Location {

    private int x,y;
    int dir;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
        this.dir = 0;
    }

    public Location(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    };

}
