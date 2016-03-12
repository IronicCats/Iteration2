package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Map;
import Utilities.AIUtilities.CanFace;

/**
 * Created by Aidan on 3/6/2016.
 */
public class PetController extends AIcontroller {

    private MobileObject owner;

    public PetController(Map map) {
        super(map);
    }

   @Override
    public void tick() {
        if(targetinView()) {
            //follow();
            //goToObjInView();
            System.out.println(CanFace.find(AI,target,map));
            //System.out.println(targetinSight());
        }
        else{
            //randomlyMoveinRange();
            //moveTo(destination);
        }
    }

}
