package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;

/**
 * Created by Wimberley on 2/25/16.
 */
public class OneShot extends Item{

    Effect []effects;

    OneShot(ItemEnum id, String name, String description, Location location, int imageId, Effect []effects){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageId = imageId;
        this.effects = effects;
    }

    public Effect[] getEffects() {
        return effects;
    }
}
