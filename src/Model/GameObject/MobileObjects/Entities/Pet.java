package Model.GameObject.MobileObjects.Entities;

import Model.Inventory.Pack;
import Model.Stats.PetStats;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Utilities.Subject;

/**
 * Created by Wimberley on 3/3/16.
 */

/* Setting up packages

 */
public class Pet extends MobileObject {
    PetStats stats;
    Pack pack;
    boolean owned;

    public Pet() {
        super(new Location(0, 0));
        stats = new PetStats();
        pack = new Pack();
        owned = false;
    } // end default constructor

    public Pet(Location location) {
        super(location);
        stats = new PetStats();
        pack = new Pack();
        owned = false;
    } // end constructor

    public Pet(Location location, PetStats stats, Pack pack, boolean owned) {
        super(location);
        this.stats = stats;
        this.pack = pack;
        this.owned = owned;
    } // end constructor

    

    public PetStats getStats() { return stats; }
    public Pack getPack() { return pack; }
    public boolean getOwned() { return owned; }
} // end
