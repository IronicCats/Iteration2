package Model.GameObject.Items;

import Model.GameObject.Item;
import Model.GameObject.ItemEnum;
import Model.Location;
import Utitlies.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Obstacle extends Item {

    Obstacle(ItemEnum id, String name, String description, Location location, int imageId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageId = imageId;
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
