package Utilities.AIUtilities;

import Model.Location;
import Model.Map.Tile;

/**
 * Created by Aidan on 3/13/2016.
 */
public class TilegivenBase {

    //TODO: check infinite range not just

    public static Location find(Location base, int range){

        switch (base.getDir()) {
            case 45:
                return new Location(base.getX() + 2, base.getY() -1, 45);
            case 90:
                return new Location(base.getX(), base.getY() - 2, 90);
            case 135:
                return new Location(base.getX() - 2, base.getY() - 1, 135);
            case 225:
                return new Location(base.getX() - 2, base.getY() + 1, 225);
            case 270:
                return new Location(base.getX(), base.getY() + 2, 270);
            case 315:
                return new Location(base.getX() + 2, base.getY() + 1, 315);
        }

        return null;
    }

}
