package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Inventory.Pack;
import Model.Map.Map;
import Utilities.AIUtilities.FindTargetinTiles;

/**
 * Created by Aidan on 3/6/2016.
 */
public class PetController extends AIController {
    Pet pet;

    public PetController(Map map) {
        super(map);
    }

    MobileObject []target = new MobileObject[2];

    public void setPlayer(Player player) {
        target[0] = player;
    }

    public void setVehicle(Vehicle vehicle){
        target[1] = vehicle;
    }

    public void removeVehicle(){
        target[1] = null;
    }

    public boolean targetinView(MobileObject target) {
        return FindTargetinTiles.find(getTilesinView(), target);
    }

   @Override
    public void tick() {

        if(targetinView(target[0]) || target[0] == null) {
            randomlyMoveinRange();
        }
        else if(target[1] != null && targetinView(target[1])){
            randomlyMoveinRange();
        }
        else if(target[1] != null){
            follow(target[1]);
        }
        else{
            follow(target[0]);
        }

    }

    //Moves to location of a mobileobject
    public void follow(MobileObject target) {
        if (oldTargetLocation == null || AI.getLocation().equals(oldTargetLocation)) {
            oldTargetLocation = target.getLocation();
        }
        moveTo(oldTargetLocation);
        tryToPickUpRandomly(2);
    }

    public void tryToPickUpRandomly(int percentPickup) {
        int temp = random.nextInt(percentPickup);
        if (map.getTile(AI.getLocation().getX(), AI.getLocation().getY()).hasItems()) {
            if (temp == 1) {
                map.getTile(AI.getLocation().getX(), AI.getLocation().getY()).interact();            }

        }


    }



    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}
