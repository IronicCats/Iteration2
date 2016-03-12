package Model.GameObject.Item.Items.Takables;

import Model.Effects.Effect;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;

/**
 * Created by Wimberley on 2/25/16.
 */

// A quest item can be added to the inventory but not used. It is simply held for "quest" purposes
// e.g., a key. This item inherits all attributes and methods from Takable and Item
public class Quest extends Takable {

    // Note: effect in this case can be used to add experience to player when quest item is successfully used to
    // complete a quest.

    public Quest(int id, String name, String description, int value, Location location, Requirement requirement, Effect effect) {
        super(id, name, description, value, location, requirement, effect);
    }
}
