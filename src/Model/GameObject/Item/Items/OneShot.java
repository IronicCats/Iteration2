package Model.GameObject.Item.Items;

import Model.Effects.Effect;
import Model.GameObject.Item.Item;
import Model.Location;

/**
 * Created by Wimberley on 2/25/16.
 */

// This shot triggers an effect on the player immediately when the player steps onto the same tile
public class OneShot extends Item {

    Effect effect;

    public OneShot() {
        super();
        this.effect = new Effect();
    } // end default constructor

    public OneShot(int id, String name, String description, int value, Location location, Effect effect) {
        super(id, name, description, value, location);
        this.effect = effect;
    } // end constructor

    public Effect getEffect() {
        return effect;
    }


}
