package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Stats.*;
import Model.Location;
import com.sun.org.glassfish.external.statistics.Stats;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This class is the parent class for NPC and player. It holds all common
attributes for the aforementioned enitites.
It also inherits location from MobileObject
*/

public abstract class Entity extends MobileObject {

    protected PlayerStats stats;
    protected Occupation occupation;
    //private Inventory inventory;
    //private Nav nav;

    public Entity() {
        super();
        stats = new PlayerStats();
        occupation = new Smasher();
    }

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
