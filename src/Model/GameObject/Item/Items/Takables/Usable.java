package Model.GameObject.Item.Items.Takables;

import Model.Effects.Effect;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;

/**
 * Created by Wimberley on 2/25/16.
 */

// A usable item can be added to the inventory and used at some point during the game.
// this item inherits all attributes and methods from Takable and Item
public class Usable extends Takable {

    public Usable(int id, String name, String description, int value, Location location, Requirement requirement, Effect effect) {
        super(id, name, description, value, location, requirement, effect);
    }


}
