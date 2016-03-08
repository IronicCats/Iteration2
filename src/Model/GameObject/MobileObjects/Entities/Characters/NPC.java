package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.NPCcontroller;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;
import Model.Tickable;
import Utilities.Subject;

/**
 * Created by Wimberley on 3/3/16.
 */

public class NPC extends Character implements Tickable {
    NPCcontroller controller;
    public NPC(Location location, CharacterStats stats, Occupation occupation, Inventory inventory, NPCcontroller controller) {
        super(location, stats, occupation, inventory);
        this.controller = controller;
    }

    @Override
    public void tick() {
        System.out.println("in NPC");
        controller.tick();
    }

    // inventory = NPCinventory
}
