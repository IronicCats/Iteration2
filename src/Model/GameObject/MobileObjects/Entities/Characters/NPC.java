package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;

/**
 * Created by Wimberley on 3/3/16.
 */

public class NPC extends Character {
    public NPC(Location location, CharacterStats stats, Occupation occupation, Inventory inventory) {
        super(location, stats, occupation, inventory);
    }
    // inventory = NPCinventory
}
