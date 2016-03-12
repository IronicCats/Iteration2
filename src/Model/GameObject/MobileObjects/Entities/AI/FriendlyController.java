package Model.GameObject.MobileObjects.Entities.AI;

import Model.Map.Map;

/**
 * Created by Wimberley on 3/12/16.
 */
public class FriendlyController extends AIController {


    public FriendlyController(Map map) {
        super(map);
    }

    boolean beingAttacked;

    @Override
    public void tick() {
        if(beingAttacked){
            runawayWheNearDeath(AI.getRange());
        }
        else{
            randomlyMoveinRange();
        }

    }



}
