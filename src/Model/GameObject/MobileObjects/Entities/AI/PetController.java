package Model.GameObject.MobileObjects.Entities.AI;

import Model.Map.Map;

/**
 * Created by Aidan on 3/6/2016.
 */
public class PetController extends AIcontroller {

    public PetController(Map map) {
        super(map);
    }

    public void follow(){
        moveTo(mobileObject.getLocation());
    }

}
