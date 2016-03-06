package Model.GameObject.Item.Items.Takables;

import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;
import Model.Effects.Effect;

/**
 * Created by Wimberley on 2/25/16.
 */

// A usable item can be added to the inventory and used at some point during the game.
// this item inherits all attributes and methods from Takable and Item
public class Usable extends Takable {

    public Usable(int id, String name, String description, Location location, Requirement requirement, Effect effect){
        super(id, name, description, location, requirement, effect);
    }


}
