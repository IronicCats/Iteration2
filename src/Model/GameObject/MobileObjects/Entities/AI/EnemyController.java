package Model.GameObject.MobileObjects.Entities.AI;

import Model.Abilities.Abilities;
import Model.GameObject.MobileObjects.Entities.AI.AIController;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
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
            enemy.face(DirectionofTarget.getDir(enemy.getLocation(), target.getLocation()));
            /*if (enemy.getOccupation() instanceof Smasher) {
                if (DistanceFromFaceableTarget.calculate(enemy, target) == 1) {
                    enemy.attack(enemy.getAbilities().get(0));
                } else {
                    follow();
                }
            } else {
                Abilities a = checkAbilityRange.check(enemy.getOccupation().getOccupationalAbilities());
                if (a.getRange() >= DistanceFromFaceableTarget.calculate(enemy, target)) {
                    //enemy.attack(a);
                } else {
                    follow();
                }

            }*/
        }
    }

    @Override
    public void update(){

    }

    @Override
    public void remove() {

    }
}

