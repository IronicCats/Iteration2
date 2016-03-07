package Model.GameObject.MobileObjects.Entities.Characters;

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

    public Player() {
        super();
    } // end default constructor

    public Player(Location location, CharacterStats stats, Occupation occupation, Inventory inventory){
        super(location, stats, occupation, inventory);
        inventory.addObserver(this);
    } // end constructor

    // takes in the degrees associated with key press and updates player location
    public void move(int degrees){
        location = Location.newLocation(degrees, location);
        location.setDir(degrees);
        alert();
    } // end move

    @Override
    public void update() {
        stats.update();
    } // end update

    @Override
    public void remove() {

    } // end remove
} // end class Player
