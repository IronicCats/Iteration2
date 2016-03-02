package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Obstacle extends Item {

    public Obstacle(ItemEnum id, String name, String description, Location location){
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }
}
