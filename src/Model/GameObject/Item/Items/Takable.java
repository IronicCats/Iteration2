package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Requirement;
import Model.Effects.Effect;

/**
 * Created by Wimberley on 2/25/16.
 */
public abstract class Takable extends Item{

    private Requirement requirement;
    protected Effect effect;

    public Takable(ItemEnum id, String name, String description, Location location, Requirement requirement, Effect effect){
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.requirement = requirement;
        this.effect = effect;
    }

    public Requirement getRequirements() {
        return requirement;
    }

    public Effect getEffect() {
        return effect;
    }
}
