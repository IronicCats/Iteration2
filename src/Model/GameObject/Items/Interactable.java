package Model.GameObject.Items;

import Model.GameObject.Item;
import Model.GameObject.ItemEnum;
import Model.Location;
import Model.Requirement;
import Utitlies.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Interactable extends Item {

    Requirement requirements;

    Interactable(ItemEnum id, String name, String description, Location location, int imageId, Requirement requirements){
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageId = imageId;
        this.requirements = requirements;
    }

    public Requirement getRequirements() {
        return requirements;
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
