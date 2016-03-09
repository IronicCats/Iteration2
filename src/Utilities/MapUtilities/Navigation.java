package Utilities.MapUtilities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Map;
import Model.Location;

/**
 * Created by Wimberley on 3/6/16.
 */

/*
    This class has an instance of map passed to it in the
 */
public class Navigation {

    public Navigation(){
    };

    public static boolean checkMove(Location location, Map map, MobileObject type){
        System.out.println("Testing Move: " + location);
        System.out.println(map.getTile(location).isWalkable());
        if(location.getY() < 0 || location.getY() >= map.getHeight()) {
            return false;
        }
        else if(location.getX() < 0 || location.getX() >= map.getWidth()) {
            return false;
        }
        else if(!map.getTile(location.getX(), location.getY()).isWalkable()) {
            return false;
        }
        else {
            return true;
        }
    }
}
