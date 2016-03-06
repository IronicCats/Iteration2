package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.Location;
import Model.Requirement;

/**
 * Created by Wimberley on 2/25/16.
 */

// Item that holds no effects. Simply is interactable with player e.g., treasure chest
public class Interactable extends Item {

    Requirement requirement; // Holds requirements player must meet in order to trigger interaction

    public Interactable() {
        super();
        this.requirement = new Requirement();
    }
    public Interactable(int id, String name, String description, Location location, Requirement requirement){
        super(id, name, description, location);
        this.requirement = requirement;
    }

    public Requirement getRequirements() {
        return requirement;
    }
}
