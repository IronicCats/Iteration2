package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Utilities.Observer;

import java.util.ArrayList;

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
        alert();
    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}
