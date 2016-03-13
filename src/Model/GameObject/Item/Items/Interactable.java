package Model.GameObject.Item.Items;

import Model.GameObject.Item.Item;
import Model.Location;
import Model.Requirement;
import Utilities.ItemUtilities.ItemsEnum;

/**
 * Created by Wimberley on 2/25/16.
 * state refers to the item's state, which is false by default.  On interaction, toggle the state and alert the
 * observer
 */

// Item that holds no effects. Simply is interactable with player e.g., treasure chest
public class Interactable extends Item {

    private boolean state;

    Requirement requirement; // Holds requirements player must meet in order to trigger interaction

    public Interactable() {
        super();
        this.requirement = new Requirement();
        state = false;
    }

    public Interactable(int id, String name, String description, int value, Location location, Requirement requirement) {
        super(id, name, description, value, location);
        this.requirement = requirement;
        state = false;
    }

    public void setID(int id){
        this.id = id;
        this.type = ItemsEnum.values()[id];
    }

    public void toggleState(){
        state = !state;
        alert();
    }

    public Requirement getRequirements() {
        return requirement;
    }

    public boolean getState() {
        return state;
    }
}
