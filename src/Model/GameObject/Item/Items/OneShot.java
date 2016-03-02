package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Effects.Effect;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class OneShot extends Item {

    Effect effect;

    public OneShot(ItemEnum id, String name, String description, Location location, Effect effect){
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }



}
