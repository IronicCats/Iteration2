package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Map.Map;

/**
 * Created by Aidan on 3/6/2016.
 */
public class PetController extends AIcontroller {

    public PetController(Map map) {
        super(map);
    }

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void tick() {

        if(targetinView() && this.player != null) {
            randomlyMoveinRange();
        }
        else if(player == null){
            randomlyMoveinRange();
        }
        else{
            follow();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}
