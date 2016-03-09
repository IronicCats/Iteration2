package Model.GameObject.MobileObjects.Entities.Characters;

import Model.Effects.EquipmentModification;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Equippable;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.Item.Items.Takables.Usable;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Inventory.EquipmentSlotEnum;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Utilities.Observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This is the player class. This class contains everything the player controlled
character will need.
 */
public class Player extends Character implements Observer{

    Pet pet;
    Vehicle vehicle;

    public Player() {
        super();
    } // end default constructor

    public Player(Location location, Occupation occupation, Inventory inventory){
        super(location, occupation, inventory);
        inventory.addObserver(this);
    } // end constructor

    // takes in the degrees associated with key press and updates player location
    public void move(int degrees){
        location = Location.newLocation(degrees, location);
        location.setDir(degrees);
        registerTile(location);
        alert();
    } // end move

    @Override
    public void update() {
        ((CharacterStats)getStats()).update();
    } // end update

    @Override
    public void remove() {

    } // end remove



    public void equip(Weapon weapon) {
        inventory.equip(weapon);
        ((CharacterStats)getStats()).applyEquipmentModification(weapon.getEquipmentModification());
    } // end equip

    public void equip(Armor armor) {
        inventory.equip(armor);
        ((CharacterStats)getStats()).applyEquipmentModification(armor.getEquipmentModification());
    } // end equip

    public void mount(Vehicle vehicle){
        ((CharacterStats)getStats()).setMovement(vehicle.getMovement());
        // change sprite
    } // end mount

    public void unequip(EquipmentSlotEnum slot) {
        inventory.unequip(slot);
        ((CharacterStats)getStats()).removeEquipmentModification((EquipmentModification) inventory.getSlot(slot).getEffect());
    } // end unequip

    public void interact(Item item) {
        if (item instanceof Weapon) {
            System.out.println("weapon");
            equip((Weapon)item);
        } else if (item instanceof Armor) {
            System.out.println("armor");
            equip((Armor)item);
        } else if (item instanceof Takable) {
            pickup(item);
        }
    } // end interact

    public void unmount(){
        ((CharacterStats)getStats()).resetMovement();
        // change sprite
    } // end unmount

    public void examinePack() {
        inventory.examine();
        //emptyPack();
    } // end emptyPack


    public void tick() {
        ((CharacterStats)getStats()).tick();
    }
} // end class Player
