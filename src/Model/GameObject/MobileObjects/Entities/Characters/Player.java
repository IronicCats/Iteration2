package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Stats.PlayerStats;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;

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

    public void move(){
        // still to be determined
    }
}
