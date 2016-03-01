package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Utitlies.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Obstacle extends Item {

    public Obstacle(ItemEnum id, String name, String description, Location location){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
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
