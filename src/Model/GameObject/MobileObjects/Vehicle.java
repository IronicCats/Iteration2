package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Location;
import Model.Stats.Stats;
import State.State;
import State.States.GameState.VehicleState;
import Utilities.MobileObjectUtilities.MobileObjectEnum;

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
            mount();
        }
    }

    public void mount(){
        driver.setLocation(this.getLocation()); // players location is vehicle location
        State.switchState(new VehicleState(this)); // switch to vehicle state
        driver.setView(this.getView()); // sets view of vehicle to player
    }

    public void unmount(){
        driver.setLocation(new Location(this.getLocation().getX(), this.getLocation().getY() + 1));
        driver.setView(MobileObjectEnum.PLAYER.ordinal());
    }

    public void setDriver(MobileObject mo){
        this.driver = mo;
    }

}
