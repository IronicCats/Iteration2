package Utilities.MapUtilities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tiles.Mountain;

/**
 * Created by Wimberley on 3/6/16.
 */

/*
    This class has an instance of map passed to it in the
 */
public class Navigation {

    public Navigation() {
    }

    public static boolean checkMove(Location location, Map map, MobileObject type) {
        if (location.getY() < 0 || location.getY() >= map.getHeight()) {
            return false;
        } else if (location.getX() < 0 || location.getX() >= map.getWidth()) {
            return false;
        } else if (!map.getTile(location.getX(), location.getY()).isWalkable()) {
            if(map.getTile(location.getX(), location.getY()) instanceof Mountain && type instanceof Vehicle){
                return true;
            }
            return false;
        } else {
            return true;
        }
    }
}
