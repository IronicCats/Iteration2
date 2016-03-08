package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.MobileObjects.Entities.AI.PetController;
import Model.Inventory.Pack;
import Model.Stats.PetStats;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Tickable;

import java.util.Random;

/**
 * Created by Wimberley on 3/3/16.
 */

/* Setting up packages

 */
public class Pet extends MobileObject implements Tickable{
    PetController controller;
    PetStats stats;
    Pack pack;
    Random random;
    boolean owned;

    public Pet(PetController controller) {
        super(new Location(0, 0));
        this.controller = controller;
        stats = new PetStats();
        pack = new Pack();
        random = new Random(System.currentTimeMillis());
        owned = false;

        controller.setAI(this);
    } // end default constructor

    public Pet(PetController controller, Location location, PetStats stats, Pack pack, boolean owned) {
        super(location);
        this.controller = controller;
        this.stats = stats;
        this.pack = pack;
        random = new Random(System.currentTimeMillis());
        this.owned = owned;

        controller.setAI(this);
    } // end constructor

    @Override
    public void tick() {
        if(controller != null) {
            int temp = random.nextInt(450);
            if(temp == 1) { // arbitrary number; 60 ticks/second means one movement per 10 seconds on average
                controller.setDestination(computeRandomLocation());
            }
            controller.tick();
        }
    } // end tick

    public Location computeRandomLocation() {
        System.out.println("computeRandomLocation called");
        int x = random.nextInt(5);
        int y = random.nextInt(5);

        return new Location(x, y);
    } // end computeRandomLocation

    public PetStats getStats() { return stats; }
    public Pack getPack() { return pack; }
    public boolean getOwned() { return owned; }
} // end
