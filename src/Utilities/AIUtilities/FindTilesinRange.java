package Utilities.AIUtilities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Utilities.MapUtilities.Neighbors;
import Utilities.Settings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Aidan on 3/9/2016.
 */
public class FindTilesinRange {

    public static ArrayList<Tile> find(MobileObject AI, Map map, int sight) {
        Tile BFS[][] = new Tile[Settings.TILEWIDTH][Settings.TILEHEIGHT];
        Queue<Tile> Queue = new LinkedList<>();
        Queue.add(AI.getTile());
        while (!Queue.isEmpty()) {
            Tile current = Queue.remove();
            Tile neighbors[] = Neighbors.neighbors(current, map);
            for (Tile tile : neighbors) {
                if (tile != null && BFS[tile.getLocation().getX()][tile.getLocation().getY()] == null) {
                    Queue.add(tile);
                    BFS[tile.getLocation().getX()][tile.getLocation().getY()] = current;
                }
            }
        }

        ArrayList<Tile> range = new ArrayList<Tile>();

        for (int i = 0; i < Settings.TILEWIDTH; i++) {
            for (int j = 0; j < Settings.TILEHEIGHT; j++) {
                if(BFS[i][j] != null) {
                    if (Math.abs(AI.getLocation().getX() - BFS[i][j].getLocation().getX()) < sight) {
                        range.add(BFS[i][j]);
                    }
                    if (Math.abs(AI.getLocation().getY() - BFS[i][j].getLocation().getY()) < sight) {
                        range.add(BFS[i][j]);
                    }
                }
            }
        }
        return range;
    }
}
