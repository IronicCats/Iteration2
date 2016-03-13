package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Location;
import Model.Stats.Stats;

/**
 * Created by Wimberley on 3/3/16.
 */

// Vehicle class!
public class Vehicle extends MobileObject {

    private Effect effect; // used to hold added defensive rating from vehicle. Also attack rating if hitting another entity

    public Vehicle(Location location, int id, Stats stats) {
        super(location, id, stats);
    }

    public void interact(MobileObject mo) {
        if (mo instanceof Player) {
            System.out.println("Mounting Car");
        }
    }

    @Override
    public void move(int degrees) {
        // some code
    }
}
