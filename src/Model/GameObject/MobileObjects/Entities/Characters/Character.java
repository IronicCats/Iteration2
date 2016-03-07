package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;

/**
 * Created by broskj on 3/6/16.
 */
public class Character extends Entity {
    private Inventory inventory;
    private CharacterStats stats;

    public Character() {
        super();
        this.stats = new CharacterStats();
        this.inventory = new Inventory();
    } // end default constructor

    public Character(Location location, CharacterStats stats, Occupation occupation, Inventory inventory) {
        super(location, stats, occupation);
        this.inventory = inventory;
    } // end constructor
} // end class Character
