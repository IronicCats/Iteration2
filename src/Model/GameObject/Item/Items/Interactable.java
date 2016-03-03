package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Requirement;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Interactable extends Item {

    Requirement requirement;

    public Interactable(ItemEnum id, String name, String description, Location location, Requirement requirement){
        super(id, name, description, location);
        this.requirement = requirement;
    }

    public Requirement getRequirements() {
        return requirement;
    }


}
