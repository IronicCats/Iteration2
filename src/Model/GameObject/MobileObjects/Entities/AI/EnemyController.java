package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.Entities.AI.AIController;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.Map.Map;
import Utilities.AIUtilities.DirectionofTarget;
import Utilities.AIUtilities.DistanceFromFaceableTarget;
import Utilities.AbilitiesUtilities.checkAbilityRange;

/**
 * Created by Aidan on 3/7/2016.
 */
public class EnemyController extends AIController {

    HostileNPC enemy;

    public EnemyController(Map map) {
        super(map);
    }

    public void setEnemy(HostileNPC enemy) {
        this.enemy = enemy;
    }

    @Override
    public void tick() {
        if(targetinView()) {
            followThenAttackinRange();
        }
        else{
           randomlyMoveinRange();
        }
    }


    public void followThenAttackinRange() {
        if (canFace()) {
            if (DirectionofTarget.getDir(enemy.getLocation(), target.getLocation()) != 0) {
                enemy.face(DirectionofTarget.getDir(enemy.getLocation(), target.getLocation()));
//                enemy.attack(checkAbilityRange.check(enemy.getOccupation().getOccupationalAbilities(), DistanceFromFaceableTarget.calculate(enemy,target)).get(0));
            }
            follow();
        }
    }

    @Override
    public void update(){

    }

    @Override
    public void remove() {

    }
}

