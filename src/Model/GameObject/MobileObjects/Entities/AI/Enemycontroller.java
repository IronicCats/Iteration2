package Model.GameObject.MobileObjects.Entities.AI;

import Model.Abilities.Abilities;
import Model.Abilities.CommandsEnum;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.Map.Map;
import Utilities.AbilitiesUtilities.checkAbilityRange;
import Utilities.MapUtilities.RangeofTilesinSight;

/**
 * Created by Aidan on 3/7/2016.
 */
public class Enemycontroller extends AIcontroller {
    public Enemycontroller(Map map) {
        super(map);
    }

    HostileNPC enemy;

    @Override
    public void tick() {
        /*if(targetinView()) {
            followThenAttackinRange();
        }
        else{
           //randomlyMoveinRange();
        }*/
    }

    public void followThenAttackinRange(){
       // if(canFace() && checkAbilityRange.check(enemy.getOccupation().getOccupationalAbilities())
        //if(targetinSight() && RangeofTilesinSight.find(AI.getLocation(),target.getLocation()) >= attackRange){
            enemy.execute(CommandsEnum.ability1);
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

