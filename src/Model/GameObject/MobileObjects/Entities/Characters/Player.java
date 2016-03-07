package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Utilities.Observer;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This is the player class. This class contains everything the player controlled
character will need.
 */
public class Player extends Character implements Observer{

    // Player inventory needed
    public Player() {
        super();
    }
    public Player(Location location, CharacterStats stats, Occupation occupation, Inventory inventory){
        super(location, stats, occupation, inventory);
    }

    // takes in the degrees associated with key press and updates player location
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
        System.out.print(location);
        alert();
    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}
