package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Requirement;
import Model.Effects.Effect;

/**
 * Created by Wimberley on 2/25/16.
 */

// The abstract parent class of all items that are takable. Holds attributes that are common
// to all takable items that aren't already inherited from the parent Item class
public abstract class Takable extends Item{

    private Requirement requirement;
    protected Effect effect;

    public Takable(ItemEnum id, String name, String description, Location location, Requirement requirement, Effect effect){
        super(id, name, description, location);
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
