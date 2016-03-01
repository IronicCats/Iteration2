package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Requirement;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Interactable extends Item {

    Requirement requirements;

    Interactable(ItemEnum id, String name, String description, Location location, int imageId, Requirement requirements){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageId = imageId;
        this.requirements = requirements;
    }

    public Requirement getRequirements() {
        return requirements;
    }
}
