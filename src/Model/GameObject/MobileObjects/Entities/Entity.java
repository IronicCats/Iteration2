package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Stats.*;
import Model.Location;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This class is the parent class for NPC and player. It holds all common
attributes for the aforementioned enitites.
It also inherits location from MobileObject
*/

public abstract class Entity extends MobileObject {

    private PlayerStats stats;
    private Occupation occupation;
    //private Inventory inventory;
    //private Nav nav;

    public Entity(Location location, PlayerStats stats, Occupation occupation){
        super(location);
        this.stats = stats;
        this.occupation = occupation;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public Occupation getOccupation() {
        return occupation;
    }
}
