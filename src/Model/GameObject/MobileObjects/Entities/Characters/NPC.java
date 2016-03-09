package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;
import Model.Tickable;

import java.util.Random;

/**
 * Created by Wimberley on 3/3/16.
 */

public class NPC extends Character implements Tickable {
    NPCController controller;
    Random random;
    public NPC(Location location, Occupation occupation, Inventory inventory, NPCController controller) {
        super(location, occupation, inventory);
        controller.setAI(this);
        this.controller = controller;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void tick() {
        if(controller != null) {
            int temp = random.nextInt(600);
            if(temp == 1) { // arbitrary number
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

    // inventory = NPCinventory
}
