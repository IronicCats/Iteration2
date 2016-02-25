package Model.GameObject.Item.Items.Takeable;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Requirement;

/**
 * Created by Wimberley on 2/25/16.
 */
public abstract class Takeable extends Item{

    Requirement requirements;
    Effect []effects;

    public Takeable(ItemEnum id, String name, String description, Location location, int imageId, Requirement requirements, Effect []effects){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageId = imageId;
        this.requirements = requirements;
        this.effects = effects;
    }

    public Requirement getRequirements() {
        return requirements;
    }

    public Effect[] getEffects() {
        return effects;
    }
}
