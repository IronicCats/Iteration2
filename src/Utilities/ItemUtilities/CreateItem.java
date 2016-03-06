package Utilities.ItemUtilities;

import Model.Effects.Effect;
import Model.Effects.EquipmentModification;
import Model.Location;
import Model.Requirement;
import Model.GameObject.Item.Items.*;
import Model.GameObject.Item.Items.Takables.Quest;
import Model.GameObject.Item.Items.Takables.Usable;
import Model.GameObject.Item.Items.Takables.Equippable.*;

/**
 * Created by Wimberley on 3/1/16.
 */

/* This class creates every type of item in the game. A few important notes:
    1.) Location is created in this class by passing an x and y coordinate as parameters
        to the respective items creation function.
    2.) Requirements for certain items are created by either passing an int for required level,
        a Quest item for required item, or both, into the creation function for said item.
    3.) Effect is created for each item that needs it by passing in a StatStructure with neccesary
        stat adjustments held within the passed StatStructure
 */

public class CreateItem {

    public static OneShot addOneShot(int id, String name, String description, Location location, Effect effect){
        return new OneShot(
                id,
                name,
                description,
                location,
                effect
        );
    }

    public static Obstacle addObstacle(int id, String name, String description, Requirement requirement, Location location){
        return new Obstacle(
                id,
                name,
                description,
                requirement,
                location
        );
    }

    public  static Interactable addInteractable(int id, String name, String description, Location location, Requirement requirement){
        return new Interactable(
                id,
                name,
                description,
                location,
                requirement
        );
    }

    public static Quest addQuest(int id, String name, String description, Location location, Requirement requirement, Effect effect){
        return new Quest(
                id,
                name,
                description,
                location,
                requirement,
                effect
        );
    }

    public static Usable addUsable(int id, String name, String description, Location location, Requirement requirement, Effect effect){
        return new Usable(
                id,
                name,
                description,
                location,
                requirement,
                effect
        );
    }

    public static Weapon addWeapon(int id, String name, String description, Location location, Requirement requirement, EquipmentModification modification, int hands){
        return new Weapon(
                id,
                name,
                description,
                location,
                requirement,
                modification,
                hands
        );
    }

    public static Armor addArmor(int id, String name, String description, Location location, Requirement requirement, EquipmentModification modification){
        return new Armor(
                id,
                name,
                description,
                location,
                requirement,
                modification
        );
    }
}
