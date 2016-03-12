package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.EnemyController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;

/**
 * Created by broskj on 3/9/16.
 */
public class HostileNPC extends NPC {

    EnemyController enemyController;

    public HostileNPC(Location location, int id, Occupation occupation, Inventory inventory, EnemyController enemyController) {
        super(location, id, occupation, inventory);
        this.enemyController = enemyController;
        enemyController.setAI(this);
    } // end constructor

    @Override
    public void tick() {
        if (controller != null) {
            controller.tick();
        }
    } // end tick

    public EnemyController getController(){
        return enemyController;
    }
} // end class HostileNPC
