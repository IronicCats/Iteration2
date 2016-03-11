package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.Location;
import Model.Requirement;

/**
 * Created by Wimberley on 2/25/16.
 */

// This item will simply act as a "roadblock" for the player. That is any tiles this item is on will be impassable
public class Obstacle extends Item {

    public Obstacle() {
        super();
    } // end constructor

    public Obstacle(int id, String name, String description, int value, Location location){
        super(id, name, description, value, location);
    } // end constructor
}
