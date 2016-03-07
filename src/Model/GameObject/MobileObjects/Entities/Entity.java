package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Stats.*;
import Model.Location;

/**
 * Created by Wimberley on 3/3/16.
 *
 * This class is the parent class for NPC and player. It holds all common
 *  attributes for the aforementioned enitites.
 *  It also inherits location from MobileObject
 */

public abstract class Entity extends MobileObject {

    protected Stats stats;
    protected Occupation occupation;

    public Entity() {
        super();
        stats = new Stats();
        occupation = new Smasher();
    }

    public Entity(Location location, Stats stats, Occupation occupation){
        super(location);
        this.stats = stats;
        this.occupation = occupation;
    }

    public Stats getStats() {
        return stats;
    }
    public int getMovement() { return stats.getMovement(); }

    public Occupation getOccupation() {
        return occupation;
    }
}
