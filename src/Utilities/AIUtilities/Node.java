package Utilities.AIUtilities;

import Model.Location;

/**
 * Created by Aidan on 3/6/2016.
 */
public class Node {
    private int x;
    private int y;
    private float cost;
    private Node parent;
    private float heuristic;
    private int depth;

    public Node(Location location){
        this.x = location.getX();
        this.y = location.getY();
    }

    public int setParent(Node parent) {
        depth = parent.depth + 1;
        this.parent = parent;

        return depth;
    }

    public int compareTo(Object other) {
        Node o = (Node) other;

        float f = heuristic + cost;
        float of = o.heuristic + o.cost;

        if (f < of) {
            return -1;
        } else if (f > of) {
            return 1;
        } else {
            return 0;
        }
    }
}


