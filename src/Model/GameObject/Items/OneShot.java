package Model.GameObject.Items;

import Model.GameObject.Item;
import Model.GameObject.ItemEnum;
import Model.Location;
import Model.Effect;
import Utitlies.Observer;
import javafx.beans.InvalidationListener;

/**
 * Created by Wimberley on 2/25/16.
 */
public class OneShot extends Item {

    Effect []effects;

    public OneShot(ItemEnum id, String name, String description, Location location, Effect effects[]){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.effects = effects;
    }

    public Effect[] getEffects() {
        return effects;
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
