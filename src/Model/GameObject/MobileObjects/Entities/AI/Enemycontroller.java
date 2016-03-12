package Model.GameObject.MobileObjects.Entities.AI;

import Model.Abilities.Abilities;
import Model.Abilities.CommandsEnum;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.Map.Map;
import Utilities.AIUtilities.DirectionofTarget;
import Utilities.AIUtilities.DistanceFromFaceableTarget;
import Utilities.AbilitiesUtilities.checkAbilityRange;
import Utilities.MapUtilities.RangeofTilesinSight;

/**
 * Created by Aidan on 3/7/2016.
 */
public class Enemycontroller extends AIcontroller {

    public Enemycontroller(Map map) {
        super(map);
    }

    HostileNPC enemy =  (HostileNPC)AI;

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

