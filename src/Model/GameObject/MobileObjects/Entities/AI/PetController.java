package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Map;

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
        if(targetinSight()) {
            //follow(mobileObject);
            goToObjInView();
        }
        else{
            //randomlyMoveinRange();
            //moveTo(destination);
        }
    }

}
