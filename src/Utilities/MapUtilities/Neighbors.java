package Utilities.MapUtilities;

import Model.Map.Map;
import Model.Map.Tile;

/**
 * Created by Aidan on 3/9/2016.
 */
public class Neighbors {

    public static Tile[] neighbors(Tile start, Map map) {
        int x = start.getLocation().getX();
        int y = start.getLocation().getY();
        Tile neighbors[] = new Tile[6];
        if (x % 2 == 0) {
            neighbors[0] = map.getTile(x, y - 1);
            neighbors[1] = map.getTile(x + 1, y - 1);
            neighbors[2] = map.getTile(x + 1, y);
            neighbors[3] = map.getTile(x, y + 1);
            neighbors[4] = map.getTile(x - 1, y);
            neighbors[5] = map.getTile(x - 1, y - 1);
        } else {
            neighbors[0] = map.getTile(x, y - 1);
            neighbors[1] = map.getTile(x + 1, y);
            neighbors[2] = map.getTile(x + 1, y + 1);
            neighbors[3] = map.getTile(x, y + 1);
            neighbors[4] = map.getTile(x - 1, y + 1);
            neighbors[5] = map.getTile(x - 1, y);
        }
        return neighbors;
    }

    public static Tile[] neighborsAtSixtyDegrees(Tile start,Map map, int direction) {
        int x = start.getLocation().getX();
        int y = start.getLocation().getY();
        Tile neighbors[] = new Tile[4];
        switch(direction){
            case 45:
                neighbors[0] = map.getTile(x + 1, y - 1);
                neighbors[1] = map.getTile(x + 1, y - 2);
                neighbors[2] = map.getTile(x + 2, y - 1);
                neighbors[3] = map.getTile(x + 2, y);
                break;
            case 90:
                neighbors[0] = map.getTile(x, y - 1);
                neighbors[1] = map.getTile(x , y - 2);
                neighbors[2] = map.getTile(x - 1, y - 2);
                neighbors[3] = map.getTile(x + 1, y - 2);
                break;
            case 135:
                neighbors[0] = map.getTile(x - 1, y - 1);
                neighbors[1] = map.getTile(x - 2 , y);
                neighbors[2] = map.getTile(x - 1, y - 2);
                neighbors[3] = map.getTile(x - 2, y);
                break;
            case 225:
                neighbors[0] = map.getTile(x - 1, y);
                neighbors[1] = map.getTile(x - 2 , y - 1);
                neighbors[2] = map.getTile(x - 2, y );
                neighbors[3] = map.getTile(x - 1, y + 1);
                break;
            case 270:
                neighbors[0] = map.getTile(x, y + 1);
                neighbors[1] = map.getTile(x , y + 2);
                neighbors[2] = map.getTile(x + 1, y + 1 );
                neighbors[3] = map.getTile(x - 1, y + 1);
                break;
            case 315:
                neighbors[0] = map.getTile(x + 1, y);
                neighbors[1] = map.getTile(x + 2 , y);
                neighbors[2] = map.getTile(x + 2, y + 1 );
                neighbors[3] = map.getTile(x + 1, y + 1);
                break;
        }
        return neighbors;
    }

}
