package Utilities.MapUtilities;

import Model.Location;

/**
 * Created by Aidan on 3/12/2016.
 */
public class RangeofTilesinSight {

    public static int find(Location base, Location target) {
        switch (base.getDir()) {
            case 45:
                return target.getX() - base.getX();
            case 90:
                return base.getY() - target.getY();
            case 135:
                return base.getX() - target.getX();
            case 225:
                return base.getX() - target.getX();
            case 270:
                return target.getY() - base.getY();
            case 315:
                return target.getX() - base.getX();
            default:
                return 0;
        }
    }
}