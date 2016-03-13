package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Location;
import Model.Stats.Stats;
import State.State;
import State.States.GameState.TradeState;
import State.States.GameState.VehicleState;

/**
 * Created by Wimberley on 3/3/16.
 */

// Vehicle class!
public class Vehicle extends MobileObject {

    private Effect effect; // used to hold added defensive rating from vehicle. Also attack rating if hitting another entity
    private MobileObject driver;


    public Vehicle(Location location, int id, Stats stats) {
        super(location, id, stats);
    }

    public void interact(MobileObject mo) {
        if (mo instanceof Player) {
            System.out.println("Mounting Car");
            setDriver(mo);
            getMounted(mo);
        }
    }

    public void getMounted(MobileObject mo){
        VehicleState vehicleState = new VehicleState(this);
        State.GAMESTATE.switchState(vehicleState);
        mo.getStats().setMovement(this.getMovement());
    }

    public void getUnmounted(){
        System.out.println();
    }

    public void setDriver(MobileObject mo){
        this.driver = mo;
    }

}
