package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */

// This item will simply act as a "roadblock" for the player. That is any tiles this item is on will be impassable
public class Obstacle extends Item {

    public Obstacle(ItemEnum id, String name, String description, Location location){
        super(id, name, description, location);
    }
}
