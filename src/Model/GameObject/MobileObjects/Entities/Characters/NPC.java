package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;

/**
 * Created by Wimberley on 3/3/16.
 */

public class NPC extends Character {
    public NPC(Location location, Occupation occupation, Inventory inventory) {
        super(location, occupation, inventory);
    }
    // inventory = NPCinventory
}
