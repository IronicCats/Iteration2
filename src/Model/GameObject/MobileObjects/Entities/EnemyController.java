package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.MobileObjects.Entities.Characters.AIController;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.Map.Map;
import Utilities.AIUtilities.DirectionofTarget;

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
            System.out.println(DirectionofTarget.getDir(AI.getLocation(), target.getLocation()));
            if (DirectionofTarget.getDir(AI.getLocation(), target.getLocation()) != 0) {
                AI.face(DirectionofTarget.getDir(AI.getLocation(), target.getLocation()));
                //enemy.attack(checkAbilityRange.check(enemy.getOccupation().getOccupationalAbilities(),DistanceFromFaceableTarget.calculate(enemy,target)).get(0));
            }
            //follow();
        }
    }

    @Override
    public void update(){

    }

    @Override
    public void remove() {

    }
}

