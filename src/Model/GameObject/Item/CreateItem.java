package Model.GameObject.Item;

import Model.Effects.Effect;
import Model.StatStruc;
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
    3.) Effect is created for each item that needs it by passing in a StatStruc with neccesary
        stat adjustments held within the passed StatStruc
 */

public class CreateItem {

    private Location location;
    private Effect effect;
    private ItemEnum id;
    private Requirement requirement;

    /* The following functions are used to create various items with varying parameters */

    /*                  Begin OneShot creation operations                   */

    public OneShot addOneShot(String name, String description, int x, int y, StatStruc modify){
        id = ItemEnum.ONESHOT;
        location = new Location(x,y);
        effect = new Effect(modify);
        return new OneShot(id, name, description, location, effect);
    }
    /*                  End OneShot creation operations                     */

    /*                  Begin Obstacle creation operations                  */

    public Obstacle addObstacle(String name, String description, int x, int y){
        id = ItemEnum.OBSTACLE;
        location = new Location(x,y);
        return new Obstacle(id, name, description, location);
    }

    /*                  End Obstacle creation operations                    */

    /*                  Begin Interactable creation operations              */

    public Interactable addInteractable(String name, String description, int x, int y, int requiredLevel){
        id = ItemEnum.INTERACTABLE;
        location = new Location(x,y);
        requirement = new Requirement(requiredLevel);
        return new Interactable(id, name, description, location, requirement);
    }

    public Interactable addInteractable(String name, String description, int x, int y, Quest requiredItem){
        id = ItemEnum.INTERACTABLE;
        location = new Location(x,y);
        requirement = new Requirement(requiredItem);
        return new Interactable(id, name, description, location, requirement);
    }

    public Interactable addInteractable(String name, String description, int x, int y, int level, Quest requiredItem) {
        id = ItemEnum.INTERACTABLE;
        location = new Location(x, y);
        requirement = new Requirement(level, requiredItem);
        return new Interactable(id, name, description, location, requirement);
    }

    /*                          End Interactable creation operations                */

    /*                          Begin Quest creation operations                     */

    public Quest addQuest(String name, String description, int x, int y, int requiredLevel, StatStruc modify){
        id = ItemEnum.QUEST;
        location = new Location(x,y);
        requirement = new Requirement(requiredLevel);
        effect = new Effect(modify);
        return new Quest(id, name, description, location, requirement, effect);
    }

    public Quest addQuest(String name, String description, int x, int y, Quest requiredItem, StatStruc modify){
        id = ItemEnum.QUEST;
        location = new Location(x,y);
        requirement = new Requirement(requiredItem);
        effect = new Effect(modify);
        return new Quest(id, name, description, location, requirement, effect);
    }

    public Quest addQuest(String name, String description, int x, int y, int level, Quest requiredItem, StatStruc modify){
        id = ItemEnum.QUEST;
        location = new Location(x,y);
        requirement = new Requirement(level, requiredItem);
        effect = new Effect(modify);
        return new Quest(id, name, description, location, requirement, effect);
    }

    /*                              End Quest creation operations                   */

    /*                              Begin Usable creation operations                */

    public Usable addUsable(String name, String description, int x, int y, int requiredLevel, StatStruc modify){
        id = ItemEnum.USABLE;
        location = new Location(x,y);
        requirement = new Requirement(requiredLevel);
        effect = new Effect(modify);
        return new Usable(id, name, description, location, requirement, effect);
    }

    public Usable addUsable(String name, String description, int x, int y, Quest requiredItem, StatStruc modify){
        id = ItemEnum.USABLE;
        location = new Location(x,y);
        requirement = new Requirement(requiredItem);
        effect = new Effect(modify);
        return new Usable(id, name, description, location, requirement, effect);
    }

    public Usable addUsable(String name, String description, int x, int y, int requiredLevel, Quest requiredItem, StatStruc modify){
        id = ItemEnum.USABLE;
        location = new Location(x,y);
        requirement = new Requirement(requiredLevel, requiredItem);
        effect = new Effect(modify);
        return new Usable(id, name, description, location, requirement, effect);
    }

    /*                              End Usable creation operations                  */

    /*                              Begin Weapon creation operations                */

    public Weapon addWeapon(ItemEnum id, String name, String description, int x, int y, int requiredLevel, StatStruc modify){
        this.id = id;
        location = new Location(x,y);
        requirement = new Requirement(requiredLevel);
        effect = new Effect(modify);
        return new Weapon(id, name, description, location, requirement, effect);
    }

    public Weapon addWeapon(ItemEnum id, String name, String description, int x, int y, Quest requiredItem, StatStruc modify){
        this.id = id;
        location = new Location(x,y);
        requirement = new Requirement(requiredItem);
        effect = new Effect(modify);
        return new Weapon(id, name, description, location, requirement, effect);
    }

    public Weapon addWeapon(ItemEnum id, String name, String description, int x, int y, int requiredLevel, Quest requiredItem, StatStruc modify){
        this.id = id;
        location = new Location(x,y);
        requirement = new Requirement(requiredLevel, requiredItem);
        effect = new Effect(modify);
        return new Weapon(id, name, description, location, requirement, effect);
    }

    /*                              End Weapon creation operations                   */

    /*                              Begin Armor creation operations                 */

    public Armor addArmor(ItemEnum id, String name, String description, int x, int y, int requiredLevel, StatStruc modify){
        this.id = id;
        location = new Location(x,y);
        requirement = new Requirement(requiredLevel);
        effect = new Effect(modify);
        return new Armor(id, name, description, location, requirement, effect);
    }

    public Armor addArmor(ItemEnum id, String name, String description, int x, int y, Quest requiredItem, StatStruc modify){
        this.id = id;
        location = new Location(x,y);
        requirement = new Requirement(requiredItem);
        effect = new Effect(modify);
        return new Armor(id, name, description, location, requirement, effect);
    }

    public Armor addArmor(ItemEnum id, String name, String description, int x, int y, int requiredLevel, Quest requiredItem, StatStruc modify){
        this.id = id;
        location = new Location(x,y);
        requirement = new Requirement(requiredLevel, requiredItem);
        effect = new Effect(modify);
        return new Armor(id, name, description, location, requirement, effect);
    }

    /*                              End Armor creation operations                   */
}
