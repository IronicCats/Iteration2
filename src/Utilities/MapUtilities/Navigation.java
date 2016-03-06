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

    private Map map;

    public Navigation(Map map){
        this.map = map;
    };

    public boolean checkMove(Location location){
        if(location.getY() <= 0 && location.getY() >= 20)
            return false;
        if(location.getX() <= 0 && location.getX() >= 10)
            return false;
        if(map.getTile(location.getX(), location.getY()) instanceof Mountain)
            return false;
        if(map.getTile(location.getX(), location.getY()) instanceof Water)
            return false;
        return true;
    }
}
