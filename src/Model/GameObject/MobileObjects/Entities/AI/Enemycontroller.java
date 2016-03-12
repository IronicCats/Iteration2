package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.Map.Map;

/**
 * Created by Aidan on 3/7/2016.
 */
public class EnemyController extends AIController {

    public EnemyController(Map map) {
        super(map);
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

    public void followThenAttackinRange(){
       // if(canFace() && checkAbilityRange.check(enemy.getOccupation().getOccupationalAbilities())
        //if(targetinSight() && RangeofTilesinSight.find(AI.getLocation(),target.getLocation()) >= attackRange){
            //enemy.execute(CommandsEnum.ability1);
        //}
        //follow();

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}

