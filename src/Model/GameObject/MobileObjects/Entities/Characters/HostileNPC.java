package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.EnemyController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;
import Utilities.MobileObjectUtilities.RespawnQueue;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by broskj on 3/9/16.
 */
public class HostileNPC extends NPC {

    EnemyController enemyController;
    boolean sleeping;
    int hostilityRating;

    public HostileNPC(Location location, int id, Occupation occupation, Inventory inventory, EnemyController enemyController,int hostilityRating) {
        super(location, id, occupation, inventory);
        this.enemyController = enemyController;
        enemyController.setAI(this);
        sleeping = false;
        this.hostilityRating = hostilityRating;
    } // end constructor

    @Override
    public void tick() {
        if (isDead()) {
            //respawn eventually
            die();
            moveToRespawnQueue();
        }else if(!isDead()) {
            getStats().tick();
            if (enemyController != null ) {
                enemyController.tick();
            }
        }
    } // end tick

    public EnemyController getController(){
        return enemyController;
    }

    @Override
    public String toString() {
        return "HostileNPC{" +
                "enemyController=" + enemyController +
                '}';
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }
} // end class HostileNPC
