package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Stats.PlayerStats;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Location;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This is the player class. This class contains everything the player controlled
character will need.
 */
public class Player extends Entity{

    // Player inventory needed
    public Player() {
        super();

    }
    public Player(Location location, PlayerStats stats, Occupation occupation){
        super(location, stats, occupation);
    }

    public void move(int degrees){
        location = Location.newLocation(degrees, location);
        if (degrees == 45) {
            location.setDir(1);
        } else if (degrees == 90) {
            location.setDir(0);
        } else if (degrees == 135) {
            location.setDir(5);
        } else if (degrees == 225) {
            location.setDir(4);
        } else if (degrees == 270) {
            location.setDir(3);
        } else if (degrees == 315) {
            location.setDir(2);
        }
    }
}
