package Model;

import Model.Map.Map;
import Utilities.Settings;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Location {

    private int x, y, dir;


    public Location(int x, int y){
        this.dir = 270;
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
        Location newLocation = new Location(0,0);
        switch(degrees){
            case 45:
                if (location.getX() % 2 == 0) {
                    newLocation.setY(location.getY() - 1);
                }
                else{
                    newLocation.setY(location.getY());
                }
                newLocation.setX(location.getX() + 1);
                break;
            case 90:
                newLocation.setY(location.getY() - 1);
                newLocation.setX(location.getX());
                break;
            case 135:
                if (location.getX() % 2 == 0) {
                    newLocation.setY(location.getY() - 1);
                }
                else{
                    newLocation.setY(location.getY());
                }
                newLocation.setX(location.getX() - 1);
                break;
            case 225:
                if (location.getX() % 2 != 0) {
                    newLocation.setY(location.getY() + 1);
                }
                else{
                    newLocation.setY(location.getY());
                }
                newLocation.setX(location.getX() - 1);
                break;
            case 270:
                newLocation.setY(location.getY() + 1);
                newLocation.setX(location.getX());
                break;
            case 315:
                if (location.getX() % 2 != 0) {
                    newLocation.setY(location.getY() + 1);
                }
                else{
                    newLocation.setY(location.getY());
                }
                newLocation.setX(location.getX() + 1);
                break;
            default:
                break;
        }
        newLocation.setDir(degrees);
        //System.out.println(newLocation.getDir());
        return newLocation;
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

    public boolean equals(Location o) {
        return o.getX() == x && o.getY() == y;
    }

    public int getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }

    public String simpleToString() {
        return "(" + x + "," + y + ")";
    } // end simpleToString

    public void setDir(int dir) {
        this.dir = dir;
    }

}
