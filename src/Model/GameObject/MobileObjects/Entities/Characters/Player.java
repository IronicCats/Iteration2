package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Map.Tile;
import Model.Stats.CharacterStats;
import Utilities.Observer;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This is the player class. This class contains everything the player controlled
character will need.
 */

public class Player extends Character{

    Pet pet;
    Vehicle vehicle;

    public Player() {
        super();

    } // end default constructor

    public Player(Location location, int id, Occupation occupation, Inventory inventory) {
        super(location, id, occupation, inventory);
        inventory.addObserver(this);
    } // end constructor

    // takes in the degrees associated with key press and updates player location

/*    public void move(int degrees){
        location = Location.newLocation(degrees, location);
        registerTile(location);
        alert();
    } // end move
*/

    public void examinePack() {
        inventory.examine();
        //emptyPack();
    } // end emptyPack


    public void tick() {
        ((CharacterStats) getStats()).tick();
    }

} // end class Player
