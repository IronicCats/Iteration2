package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;
import Model.Tickable;

/**
 * Created by Wimberley on 3/3/16.
 */

public class NPC extends Character implements Tickable {
    NPCController controller;
    public NPC(Location location, CharacterStats stats, Occupation occupation, Inventory inventory, NPCController controller) {
        super(location, stats, occupation, inventory);
        controller.setAI(this);
        this.controller = controller;
    }

    @Override
    public void tick() {
        if (controller != null){
            controller.tick();
        }

    }

    // inventory = NPCinventory
}
