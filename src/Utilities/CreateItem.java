package Utilities;

import Model.Effects.Effect;
import Model.GameObject.Item.ItemEnum;
import Model.Stats.StatStructure;
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

    public static OneShot addOneShot(String name, String description, int x, int y, StatStructure modify){
        return new OneShot(
                ItemEnum.ONESHOT,
                name,
                description,
                new Location(x,y),
                new Effect(modify)
        );
    }

    public static Obstacle addObstacle(String name, String description, int x, int y){
        return new Obstacle(
                ItemEnum.OBSTACLE,
                name,
                description,
                new Location(x,y)
        );
    }

    public  static Interactable addInteractable(String name, String description, int x, int y, int requiredLevel){
        return new Interactable(
                ItemEnum.INTERACTABLE,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredLevel)
        );
    }

    public static Interactable addInteractable(String name, String description, int x, int y, Quest requiredItem){
        return new Interactable(
                ItemEnum.INTERACTABLE,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredItem)
        );
    }

    public static Interactable addInteractable(String name, String description, int x, int y, int level, Quest requiredItem) {
        return new Interactable(
                ItemEnum.INTERACTABLE,
                name,
                description,
                new Location(x,y),
                new Requirement(level, requiredItem)
        );
    }

    public static Quest addQuest(String name, String description, int x, int y, int requiredLevel, StatStructure modify){
        return new Quest(
                ItemEnum.QUEST,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredLevel),
                new Effect(modify)
        );
    }

    public static Quest addQuest(String name, String description, int x, int y, Quest requiredItem, StatStructure modify){
        return new Quest(
                ItemEnum.QUEST,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredItem),
                new Effect(modify)
        );
    }

    public static Quest addQuest(String name, String description, int x, int y, int level, Quest requiredItem, StatStructure modify){
        return new Quest(
                ItemEnum.QUEST,
                name,
                description,
                new Location(x,y),
                new Requirement(level, requiredItem),
                new Effect(modify)
        );
    }

    public static Usable addUsable(String name, String description, int x, int y, int requiredLevel, StatStructure modify){
        return new Usable(
                ItemEnum.USABLE,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredLevel),
                new Effect(modify)
        );
    }

    public static Usable addUsable(String name, String description, int x, int y, Quest requiredItem, StatStructure modify){
        return new Usable(
                ItemEnum.USABLE,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredItem),
                new Effect(modify));
    }

    public static Usable addUsable(String name, String description, int x, int y, int requiredLevel, Quest requiredItem, StatStructure modify){
        return new Usable(
                ItemEnum.USABLE,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredLevel, requiredItem),
                new Effect(modify)
        );
    }

    public static Weapon addWeapon(ItemEnum id, String name, String description, int x, int y, int requiredLevel, StatStructure modify){
        return new Weapon(
                id,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredLevel),
                new Effect(modify)
        );
    }

    public static Weapon addWeapon(ItemEnum id, String name, String description, int x, int y, Quest requiredItem, StatStructure modify){
        return new Weapon(
                id,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredItem),
                new Effect(modify)
        );
    }

    public static Weapon addWeapon(ItemEnum id, String name, String description, int x, int y, int requiredLevel, Quest requiredItem, StatStructure modify){
        return new Weapon(
                id,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredLevel, requiredItem),
                new Effect(modify)
        );
    }

    public static Armor addArmor(ItemEnum id, String name, String description, int x, int y, int requiredLevel, StatStructure modify){
        return new Armor(
                id,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredLevel),
                new Effect(modify)
        );
    }

    public static Armor addArmor(ItemEnum id, String name, String description, int x, int y, Quest requiredItem, StatStructure modify){
        return new Armor(
                id,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredItem),
                new Effect(modify)
        );
    }

    public static Armor addArmor(ItemEnum id, String name, String description, int x, int y, int requiredLevel, Quest requiredItem, StatStructure modify){
        return new Armor(
                id,
                name,
                description,
                new Location(x,y),
                new Requirement(requiredLevel, requiredItem),
                new Effect(modify)
        );
    }
}
