package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.Enemycontroller;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Tickable;

import java.util.Random;

/**
 * Created by Wimberley on 3/3/16.
 */

public class NPC extends Character implements Tickable {

    Enemycontroller controller;
    Random random;
    Location base;

    public NPC(Location location, int id, Occupation occupation, Inventory inventory, Enemycontroller controller) {
        super(location, id, occupation, inventory);
        controller.setAI(this);
        this.controller = controller;
        base = location;
        random = new Random(System.currentTimeMillis());
        stats.setMovement(7);
    }

    @Override
    public void tick() {
        if (controller != null) {

            getStats().tick();
            controller.tick();
        }
    } // end tick

    public Enemycontroller getController() {
        return controller;
    }

    public void setController(Enemycontroller controller) {
        this.controller = controller;
    }


    // inventory = NPCinventory
}
