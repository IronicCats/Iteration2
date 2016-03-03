package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Requirement;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */

// Item that holds no effects. Simply is interactable with player e.g., treasure chest
public class Interactable extends Item {

    Requirement requirement; // Holds requirements player must meet in order to trigger interaction

    public Interactable(ItemEnum id, String name, String description, Location location, Requirement requirement){
        super(id, name, description, location);
        this.requirement = requirement;
    }

    public Requirement getRequirements() {
        return requirement;
    }


}
