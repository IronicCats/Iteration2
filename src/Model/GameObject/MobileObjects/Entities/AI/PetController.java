package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Map;
import Model.Requirement;

/**
 * Created by Aidan on 3/6/2016.
 */
public class PetController extends AIcontroller {

    public PetController(Map map) {
        super(map);
    }

    //new Requirement requirement = new Requirement();

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void tick() {
        if(targetinSight() && this.player != null) {
            randomlyMoveinRange();
        }
        else if(player == null){
            randomlyMoveinRange();
        }
        else{
            follow();
        }
    }
}