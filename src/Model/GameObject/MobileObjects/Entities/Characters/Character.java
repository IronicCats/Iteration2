package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;

/**
 * Created by broskj on 3/6/16.
 */
public abstract class Character extends Entity {
    protected Inventory inventory;

    public Character() {
        super();
        this.inventory = new Inventory();
    } // end default constructor

    public Character(Location location, Occupation occupation, Inventory inventory) {
        super(location, occupation.getStats(), occupation);
        this.inventory = inventory;
    } // end constructor
} // end class Character
