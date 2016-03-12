package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.EnemyController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;

/**
 * Created by broskj on 3/9/16.
 */
public class HostileNPC extends NPC {

    public HostileNPC(Location location, int id, Occupation occupation, Inventory inventory, EnemyController controller) {
        super(location, id, occupation, inventory, controller);
    } // end constructor

    @Override
    public void tick() {
        if (controller != null) {
            controller.tick();
        }
    } // end tick
} // end class HostileNPC
