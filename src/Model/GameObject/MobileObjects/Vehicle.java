package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.Location;
import Model.Map.Tile;
import Model.Stats.Stats;
import State.State;
import State.States.GameState.GameState;
import State.States.GameState.VehicleState;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import Utilities.Utilities;

/**
 * Created by Wimberley on 3/3/16.
 */

// Vehicle class!
public class Vehicle extends MobileObject {

    private Effect effect; // used to hold added defensive rating from vehicle. Also attack rating if hitting another entity
    private Entity driver;
    private Pet pet;

    public Vehicle(Location location, int id, Stats stats) {
        super(location, id, stats);
        pet = null;
    }

    public void interact(MobileObject mo) {
        if (mo instanceof Player) {
            System.out.println("Mounting Car");
            setRiders((Entity)mo);
            mount();
        }
    }

    public void mount(){
        State.switchState(new VehicleState(this)); // switch to vehicle state
    }

    public void unmount(){
    }

    public void setRiders(Entity mo){
        /*
        this.driver = mo;
        if(driver.getPet().getOwned()){
            pet = driver.getPet();
        }*/
        this.driver = State.GAMESTATE.getPlayer();
        if(driver.getPet() != null) {
            if (driver.getPet().getOwned()) {
                pet = driver.getPet();
            }
        }
    }

    public Pet getPet(){
        return pet;
    }

    public Entity getDriver() {
        return driver;
    }
}
