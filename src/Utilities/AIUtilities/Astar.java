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

    Map map;

    public Astar(Map map){
        this.map = map;
    }

    public ArrayList<Tile> Findpath(Tile start, Tile end) {


        Queue<Tile> Queue = new LinkedList<Tile>();
        Tile parent[][] = new Tile[Settings.TILEWIDTH][Settings.TILEHEIGHT];

        Queue.add(start);
        parent[start.getLocation().getX()][start.getLocation().getY()] = null;

        while (!Queue.isEmpty()) {
            Tile current = Queue.remove();
            if(current.IsWalkable) {
                Tile neighbors[] = neighbors(current, map);
                for (int i = 0; i < 6; i++) {
                    Tile next = neighbors[i];
                    if (next != null && parent[next.getLocation().getX()][next.getLocation().getY()] == null) {
                        Queue.add(next);
                        parent[next.getLocation().getX()][next.getLocation().getY()] = current;
                    }
                }
            }
        }

        ArrayList<Tile> path = new ArrayList<Tile>();

        Tile current = end;
        path.add(current);
        while(current != start){
            current = parent[current.getLocation().getX()][current.getLocation().getY()];
            path.add(current);
        }
        Collections.reverse(path);
        for(int i = 0; i < path.size() - 1; i++){
            int  startx = path.get(i).getLocation().getX();
            int  starty = path.get(i).getLocation().getY();
            int  endx = path.get(i + 1).getLocation().getX();
            int  endy = path.get(i + 1).getLocation().getY();
            if(startx % 2 == 0){
                if(endx - startx == 1 && endy - starty == -1){
                    path.get(i).getLocation().setDir(45);
                }
                else if(endx - startx == 0 && endy - starty == -1){
                    path.get(i).getLocation().setDir(90);
                }
                else if(endx - startx == -1 && endy - starty == -1){
                    path.get(i).getLocation().setDir(135);
                }
                else if(endx - startx == -1 && endy - starty == 0){
                    path.get(i).getLocation().setDir(225);
                }
                else if(endx - startx == 0 && endy - starty == 1){
                    path.get(i).getLocation().setDir(270);
                }
                else if(endx - startx == 1 && endy - starty == 0){
                    path.get(i).getLocation().setDir(315);
                }
            }
            else{
                if(endx - startx == 1 && endy - starty == 0){
                    path.get(i).getLocation().setDir(45);
                }
                else if(endx - startx == 0 && endy - starty == -1){
                    path.get(i).getLocation().setDir(90);
                }
                else if(endx - startx == -1 && endy - starty == 0){
                    path.get(i).getLocation().setDir(135);
                }
                else if(endx - startx == -1 && endy - starty == 1){
                    path.get(i).getLocation().setDir(225);
                }
                else if(endx - startx == 0 && endy - starty == 1){
                    path.get(i).getLocation().setDir(270);
                }
                else if(endx - startx == 1 && endy - starty == 1){
                    path.get(i).getLocation().setDir(315);
                }
            }
        }
        return path;
    }

    public Tile[] neighbors(Tile start, Map map) {
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
