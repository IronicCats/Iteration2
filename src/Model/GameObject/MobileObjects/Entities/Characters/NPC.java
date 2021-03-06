package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.AIController;
import Model.GameObject.MobileObjects.Entities.AI.EnemyController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Tickable;

import java.util.Random;

/**
 * Created by Wimberley on 3/3/16.
 */

public class NPC extends Character implements Tickable {

    Random random;
    Location base;

    public NPC(Location location, int id, Occupation occupation, Inventory inventory) {
        super(location, id, occupation, inventory);
        base = location;
        random = new Random(System.currentTimeMillis());
        stats.setMovement(7);
    }
}
