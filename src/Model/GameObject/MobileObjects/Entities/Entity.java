package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Tile;
import Model.Stats.Stats;

/**
 * Created by Wimberley on 3/3/16.
 * <p>
 * This class is the parent class for NPC and player. It holds all common
 * attributes for the aforementioned enitites.
 * It also inherits location from MobileObject
 */

public abstract class Entity extends MobileObject {

    //protected Stats stats;
    protected Occupation occupation;

    Pet pet;

    public Entity() {
        super();
        //stats = new Stats();
        occupation = new Smasher();
    }

    public void setPet(Pet pet){
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

    public Entity(Location location, int id, Stats stats, Occupation occupation) {
        super(location, id, stats);
        //this.stats = stats;
        this.occupation = occupation;
    }

    public Tile dismountOntoTile(Location location) {
        tile = map.register(this);
        return tile;
    }

    /*
        public Stats getStats() {
            return stats;
        }
        public int getMovement() { return stats.getMovement(); }
    */
    public Occupation getOccupation() {
        return occupation;
    }

}
