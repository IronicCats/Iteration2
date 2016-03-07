package Utilities.AIUtilities;

import Model.Location;
import Utilities.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aidan on 3/6/2016.
 */

public class Astar {

    private Map map;
    private int maxDistance = Settings.GAMEHEIGHT * Settings.GAMEWIDTH;
    private Node[][] nodes;
    List<Node> openList = new ArrayList<Node>();
    List<Node> closedList = new ArrayList<Node>();

    public List FindPath(Location start, Location end){

        //if end point is blocked return null because there is nothing there


        openList.add(new Node(start));


        boolean done = false;



        return openList;
    }

}
