package Utilities.AIUtilities;

import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Utilities.MapUtilities.Neighbors;
import Utilities.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Aidan on 3/6/2016.
 */

public class Astar {

    public static ArrayList<Location> Findpath(Map map, Location start, Location end) {

        /*
        You need a queue for the breadth first search algorithm. It searches every tile that
        is walkable while also adding its parent and storing it in an array.
        */

        Queue<Location> Queue = new LinkedList<>();  //The quee which stores the tiles
        Tile parent[][] = new Tile[Settings.TILEWIDTH][Settings.TILEHEIGHT]; //This stores the parents

        Queue.add(start); //Automatically add the start tile to the queue
        parent[start.getX()][start.getY()] = null; //Set its parent to null because it is the starting tile

        while (!Queue.isEmpty()) {
            Location current = Queue.remove();
            Tile currentTile = map.getTile(current.getX(), current.getY());
            if (currentTile.isWalkable() || start.equals(current)) {
                Tile neighbors[] = Neighbors.neighbors(currentTile, map);   //This uses the neighbor method which calculates all the
                for (Tile tile : neighbors) {                     //surrounding tiles of a particular tile.
                    if (tile != null && parent[tile.getLocation().getX()][tile.getLocation().getY()] == null && !tile.getLocation().equals(start)) { //This if statement makes sure that the tile isnt null it is not already a parent and it's not the starting tile
                        Queue.add(tile.getLocation());
                        parent[tile.getLocation().getX()][tile.getLocation().getY()] = currentTile;
                    }
                }
            }
        }


        ArrayList<Location> path = new ArrayList<Location>();

        //The while loop below gets the path starting from the end node and following its parents until it reaches the starting tile

        Tile current = map.getTile(end.getX(), end.getY());
        path.add(current.getLocation());
        while (current != map.getTile(start.getX(), start.getY())) {
            current = parent[current.getLocation().getX()][current.getLocation().getY()];
            path.add(current.getLocation());
        }
        Collections.reverse(path); //The path comes in opposite order so reverse it

        //Now get the direction of every tile depending on what the next tile in the path is
        for (int i = 0; i < path.size() - 1; i++) {
            int startx = path.get(i).getX();
            int starty = path.get(i).getY();
            int endx = path.get(i + 1).getX();
            int endy = path.get(i + 1).getY();
            if (startx % 2 == 0) {
                if (endx - startx == 1 && endy - starty == -1) {
                    path.get(i).setDir(45);
                } else if (endx - startx == 0 && endy - starty == -1) {
                    path.get(i).setDir(90);
                } else if (endx - startx == -1 && endy - starty == -1) {
                    path.get(i).setDir(135);
                } else if (endx - startx == -1 && endy - starty == 0) {
                    path.get(i).setDir(225);
                } else if (endx - startx == 0 && endy - starty == 1) {
                    path.get(i).setDir(270);
                } else if (endx - startx == 1 && endy - starty == 0) {
                    path.get(i).setDir(315);
                }
            } else {
                if (endx - startx == 1 && endy - starty == 0) {
                    path.get(i).setDir(45);
                } else if (endx - startx == 0 && endy - starty == -1) {
                    path.get(i).setDir(90);
                } else if (endx - startx == -1 && endy - starty == 0) {
                    path.get(i).setDir(135);
                } else if (endx - startx == -1 && endy - starty == 1) {
                    path.get(i).setDir(225);
                } else if (endx - startx == 0 && endy - starty == 1) {
                    path.get(i).setDir(270);
                } else if (endx - startx == 1 && endy - starty == 1) {
                    path.get(i).setDir(315);
                }
            }
        }
        return path;
    }

}
