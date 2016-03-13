package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Inventory.Inventory;
import Model.Location;
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
        stats.setMovement(10);
        //State.INVENTORYSTATE.setConnect(this);
    } // end constructor
    

    public void examinePack() {
        inventory.examine();
        //emptyPack();
    } // end emptyPack
} // end class Player
