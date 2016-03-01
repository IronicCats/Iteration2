package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Effects.Effect;
import Utitlies.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class OneShot extends Item {

    Effect effect;

    public OneShot(ItemEnum id, String name, String description, Location location, Effect effect){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }


    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void alert() {

    }
}
