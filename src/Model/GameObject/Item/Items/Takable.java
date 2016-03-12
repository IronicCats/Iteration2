package Model.GameObject.Item.Items;

import Model.Effects.Effect;
import Model.GameObject.Item.Item;
import Model.Location;
import Model.Requirement;

/**
 * Created by Wimberley on 2/25/16.
 */

// The abstract parent class of all items that are takable. Holds attributes that are common
// to all takable items that aren't already inherited from the parent Item class
public abstract class Takable extends Item {

    private Requirement requirement;
    protected Effect effect;

    public Takable() {
        super();
        this.requirement = new Requirement();
    } // end default constructor

    public Takable(int id, String name, String description, int value, Location location, Requirement requirement, Effect effect) {
        super(id, name, description, value, location);
        this.requirement = requirement;
        this.effect = effect;
    } // end default constructor

    public Requirement getRequirements() {
        return requirement;
    }

    public Effect getEffect() {
        return effect;
    }
}
