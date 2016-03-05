package Model.GameObject.MobileObjects.Entities;

import Model.Stats.PetStats;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;

/**
 * Created by Wimberley on 3/3/16.
 */

/* Setting up packages

 */
public class Pet extends MobileObject {
    // inventory = pet Inventory();
    PetStats stats;

    public Pet(Location location) {
        super(location);
    }
}
