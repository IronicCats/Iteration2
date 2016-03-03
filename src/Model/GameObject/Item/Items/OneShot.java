package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Effects.Effect;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */

// This shot triggers an effect on the player immediately when the player steps onto the same tile
public class OneShot extends Item {

    Effect effect;

    public OneShot(ItemEnum id, String name, String description, Location location, Effect effect){
        super(id, name, description, location);
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }



}
