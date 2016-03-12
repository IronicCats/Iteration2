package Utilities.AIUtilities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Tile;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/11/2016.
 */
public class FindTargetinTiles {

    public static boolean find(ArrayList<Tile> range, MobileObject target) {

        for (Tile tile : range) {
            if (tile.getObject() == target) {
                return true;
            }
        }
        return false;

    }

}
