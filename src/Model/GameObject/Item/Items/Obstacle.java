package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Obstacle extends Item {

    Obstacle(ItemEnum id, String name, String description, Location location, int imageId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageId = imageId;
    }
}
