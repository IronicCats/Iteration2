package Utilities.AIUtilities;

import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Utilities.MapUtilities.Neighbors;
import Utilities.Settings;
import Model.GameObject.MobileObjects.ViewLocation;
import Utilities.Utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Aidan on 3/9/2016.
 */
public class FindTilesAround {

    public static ArrayList<Tile> find(Location Loc, Map map, int sight, ViewLocation viewLoc) {

        Tile BFS[][] = new Tile[Settings.TILEWIDTH][Settings.TILEHEIGHT];   //this is going to be an array handling all tiles found in BFS
        Queue<Tile> Queue = new LinkedList<>();
        Queue.add(map.getTile(Loc.getX(), Loc.getY()));  //a Queue for adding tiles that are encountered
        while (!Queue.isEmpty()) {
            Tile current = Queue.remove();
            Tile neighbors[] = Neighbors.neighbors(current, map);
            for (Tile tile : neighbors) {
                if (tile != null && BFS[tile.getLocation().getX()][tile.getLocation().getY()] == null) { //for all neighboring tiles if they are not tile and aren't already in BFS array add them
                    Queue.add(tile);
                    BFS[tile.getLocation().getX()][tile.getLocation().getY()] = current;
                }
            }
        }

        ArrayList<Tile> range = new ArrayList<Tile>();  //now that you have all tiles in range

        for (int i = 0; i < Settings.MAPWIDTH; i++) {
            for (int j = 0; j < Settings.MAPHEIGHT; j++) {

                if (!Utilities.outOfSite(viewLoc, new ViewLocation(BFS[i][j].getLocation().getX(), BFS[i][j].getLocation().getY()), sight)) {
                    range.add(BFS[i][j]);
                }

            }
        }
        return range;
    }
}
