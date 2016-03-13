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
            System.out.println("target is in view");
            followThenAttackinRange();
        }
        else{
           randomlyMoveinRange();
        }
    }

    @Override
    public void follow(){
        moveTo(target.getLocation());
    }


    public void followThenAttackinRange() {
        System.out.println(canFace());
        if (canFace()) {
            enemy.face(DirectionofTarget.getDir(enemy.getLocation(), target.getLocation()));
            if (enemy.getOccupation() instanceof Smasher) {
                System.out.println("distance: " + DistanceFromFaceableTarget.calculate(enemy, target));
                if (DistanceFromFaceableTarget.calculate(enemy, target) == 1) {
                    System.out.println("attacking");
                    //enemy.attack(enemy.getAbilities().get(0));
                }
                else if(DistanceFromFaceableTarget.calculate(enemy, target) > 1){
                    System.out.println("facing but need to be closer");
                    follow();
                }
            }
            else {
                Abilities a = checkAbilityRange.check(enemy.getAbilities());
                if (a.getRange() >= DistanceFromFaceableTarget.calculate(enemy, target)) {
                    //enemy.attack(a);
                }
            }
        }
        else{
            System.out.println("In view but need to move to face");
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

