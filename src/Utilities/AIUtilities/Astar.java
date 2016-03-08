package Utilities.AIUtilities;

import Model.Location;
import Model.Map.Tile;
import Utilities.Settings;
import Model.Map.Map;

import java.util.*;

/**
 * Created by Aidan on 3/6/2016.
 */

public class Astar {

    public static ArrayList<Location> Findpath(Map map, Location start, Location end) {


        Queue<Location> Queue = new LinkedList<>();
        Tile parent[][] = new Tile[Settings.TILEWIDTH][Settings.TILEHEIGHT];

        Queue.add(start);
        parent[start.getX()][start.getY()] = null;

        while (!Queue.isEmpty()) {
            Location current = Queue.remove();
            Tile currentTile = map.getTile(current.getX(),current.getY());
            if(currentTile.IsWalkable) {
                Tile neighbors[] = neighbors(currentTile, map);
                for (int i = 0; i < 6; i++) {
                    Tile next = neighbors[i];
                    if (next != null && parent[next.getLocation().getX()][next.getLocation().getY()] == null) {
                        Queue.add(next.getLocation());
                        parent[next.getLocation().getX()][next.getLocation().getY()] = currentTile;
                    }
                }
            }
        }

        ArrayList<Location> path = new ArrayList<Location>();

        Tile current = map.getTile(end.getX(),end.getY());
        path.add(current.getLocation());
        while(current != map.getTile(start.getX(),start.getY())){
            current = parent[current.getLocation().getX()][current.getLocation().getY()];
            path.add(current.getLocation());
        }
        Collections.reverse(path);
        for(int i = 0; i < path.size() - 1; i++){
            int  startx = path.get(i).getX();
            int  starty = path.get(i).getY();
            int  endx = path.get(i + 1).getX();
            int  endy = path.get(i + 1).getY();
            if(startx % 2 == 0){
                if(endx - startx == 1 && endy - starty == -1){
                    path.get(i).setDir(45);
                }
                else if(endx - startx == 0 && endy - starty == -1){
                    path.get(i).setDir(90);
                }
                else if(endx - startx == -1 && endy - starty == -1){
                    path.get(i).setDir(135);
                }
                else if(endx - startx == -1 && endy - starty == 0){
                    path.get(i).setDir(225);
                }
                else if(endx - startx == 0 && endy - starty == 1){
                    path.get(i).setDir(270);
                }
                else if(endx - startx == 1 && endy - starty == 0){
                    path.get(i).setDir(315);
                }
            }
            else{
                if(endx - startx == 1 && endy - starty == 0){
                    path.get(i).setDir(45);
                }
                else if(endx - startx == 0 && endy - starty == -1){
                    path.get(i).setDir(90);
                }
                else if(endx - startx == -1 && endy - starty == 0){
                    path.get(i).setDir(135);
                }
                else if(endx - startx == -1 && endy - starty == 1){
                    path.get(i).setDir(225);
                }
                else if(endx - startx == 0 && endy - starty == 1){
                    path.get(i).setDir(270);
                }
                else if(endx - startx == 1 && endy - starty == 1){
                    path.get(i).setDir(315);
                }
            }
        }
        return path;
    }

    public static Tile[] neighbors(Tile start, Map map) {
        int x = start.getLocation().getX();
        int y = start.getLocation().getY();
        Tile neighbors[] = new Tile[6];
        if (x % 2 == 0) {
            neighbors[0] = map.getTile(x,y - 1);
            neighbors[1] = map.getTile(x + 1,y - 1);
            neighbors[2] = map.getTile(x + 1,y);
            neighbors[3] = map.getTile(x,y + 1);
            neighbors[4] = map.getTile(x - 1,y);
            neighbors[5] = map.getTile(x - 1,y - 1);
        }
        else{
            neighbors[0] = map.getTile(x,y - 1);
            neighbors[1] = map.getTile(x + 1,y);
            neighbors[2] = map.getTile(x + 1,y + 1);
            neighbors[3] = map.getTile(x ,y + 1);
            neighbors[4] = map.getTile(x - 1,y + 1);
            neighbors[5] = map.getTile(x - 1,y);
        }
        return neighbors;
    }
}
