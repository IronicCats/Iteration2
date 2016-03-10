package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;

/**
 * Created by broskj on 3/9/16.
 */
public class HostileNPC extends NPC {
    public HostileNPC(Location location, Occupation occupation, Inventory inventory, NPCController controller) {
        super(location, occupation, inventory, controller);
    } // end constructor
} // end class HostileNPC
