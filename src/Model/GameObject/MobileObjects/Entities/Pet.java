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
    Location base;
    Pack pack;
    Random random;
    boolean owned;

    public Pet(PetController controller) {
        super(new Location(0, 0), new PetStats());
        base = new Location(0, 0);
        this.controller = controller;
        stats = new PetStats();
        pack = new Pack();
        random = new Random(System.currentTimeMillis());
        owned = false;

        controller.setAI(this);
    } // end default constructor

    public Pet(PetController controller, Location location, PetStats stats, Pack pack, boolean owned) {
        super(location, stats);
        base = location;
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
        int temp = random.nextInt(2) - 1; // random number between -1 and 1
        int x = random.nextInt(base.getX() + temp * stats.getMovement());
        int y = random.nextInt(base.getY() + temp * stats.getMovement());

        return new Location(x, y);
    } // end computeRandomLocation

    public PetStats getStats() { return stats; }
    public Pack getPack() { return pack; }
    public boolean getOwned() { return owned; }
} // end
