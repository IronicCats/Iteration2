package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Map.Tile;
import Model.Stats.Stats;
import State.State;
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

    public Vehicle(Location location, int id, Stats stats) {
        super(location, id, stats);
    }

    public void interact(MobileObject mo) {
        if (mo instanceof Player) {
            System.out.println("Mounting Car");
            setDriver((Entity)mo);
            mount();
        }
    }

    public void mount(){
        State.switchState(new VehicleState(this)); // switch to vehicle state
    }

    public void unmount(){
    }

    public void setDriver(Entity mo){
        this.driver = mo;
    }

    public Entity getDriver() {
        return driver;
    }
}
