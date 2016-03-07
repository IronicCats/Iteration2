package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Stats.PlayerStats;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;

import java.util.ArrayList;

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

    // takes in the degrees associated with key press and updates player location
    public void move(int degrees){
        location = Location.newLocation(degrees, location);
        alert();
    }
}
