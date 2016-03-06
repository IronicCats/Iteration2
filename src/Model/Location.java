package Model;

import Model.Map.Map;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Location {

    private int x, y, dir;

    public Location(int x, int y){
        this.dir = 0;
        this.x = x;
        this.y = y;
    }

    public Location(int x, int y, int dir){
        this(x, y);
        this.dir = dir;
    }

    // returns appropriate location based on degrees and current location
    // used to check tile through navigation
    public static Location newLocation(int degrees, Location location){
        switch(degrees){
            case 45: if(location.getX() % 2 == 0){
                        location.setY(location.getY() - 1);
                     }
                     location.setX(location.getX() + 1);
                break;
            case 90: location.setY(location.getY() - 1);
                break;
            case 135: if(location.getX() % 2 == 0){
                location.setY(location.getY() - 1);
            }
                location.setX(location.getX() - 1);
                break;
            case 225: if(location.getX() % 2 != 0){
                            location.setY(location.getY() + 1);
                        }
                location.setX(location.getX() - 1);
                break;
            case 270: location.setY(location.getY() + 1);
                break;
            case 315: if(location.getX() % 2 != 0){
                        location.setY(location.getY() + 1);
                      }
                location.setX(location.getX() + 1);
                break;
            default: break;
        }
        return location;
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
