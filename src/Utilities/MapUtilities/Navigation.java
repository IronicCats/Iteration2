package Utilities.MapUtilities;

import Model.Map.Map;
import Model.Location;
import Model.Map.Tiles.*;

/**
 * Created by Wimberley on 3/6/16.
 */

/*
    This class has an instance of map passed to it in the
 */
public class Navigation {

    public Navigation(){
    };

    public static boolean checkMove(Location location, Map map){
        if(location.getY() < 0 || location.getY() > 20) {
            return false;
        }
        if(location.getX() < 0 || location.getX() > 10) {
            return false;
        }
        if(map.getTile(location.getX(), location.getY()) instanceof Mountain) {
            return false;
        }
        if(map.getTile(location.getX(), location.getY()) instanceof Water) {
            return false;
        }
        return true;
    }
}
