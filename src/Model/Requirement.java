package Model;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Quest;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Utilities.ItemUtilities.ItemsEnum;

/**
 * Created by Wimberley on 2/25/16.
 */

/* This class is used to tell if player is a high enough level or has the necessary
   quest item in order to trigger some type of interaction. The requirements are initally set
   through the constructor and will be held for the duration of the item's existence. Methods
   to check if player meets requirements are listed below.
 */
public class Requirement {

    private int requiredLevel; // used to tell if player is high enough level. Intialized to 0
    private ItemsEnum requiredItemEnum; // used to tell if player has required item
    private Occupation requiredOccupation;

    public Requirement() {
        this.requiredLevel = 0;
        this.requiredItemEnum = null;
        this.requiredOccupation = null;
    } // end default constructor

    public Requirement(int requiredLevel, Occupation requiredOccupation) {
        this.requiredLevel = requiredLevel;
        this.requiredItemEnum = null;
        this.requiredOccupation = requiredOccupation;
    } // end constructor

    public Requirement(ItemsEnum requiredItem, Occupation requiredOccupation) {
        this.requiredLevel = 0;
        this.requiredItemEnum = requiredItem;
        this.requiredOccupation = requiredOccupation;
    } // end constructor

    // constructor for requirement needing both item level and specific item
    public Requirement(int requiredLevel, ItemsEnum requiredItem){
        this.requiredLevel = requiredLevel;
        this.requiredItemEnum = requiredItem;
        this.requiredOccupation = null;
    } // end constructor

    // constructor for requirement needing only player level
    public Requirement(int requiredLevel){
        this.requiredLevel = requiredLevel;
        this.requiredItemEnum = null;
        this.requiredOccupation = null;
    } // end constructor

    // constructor for requirement needing only specific item
    public Requirement(ItemsEnum requiredItem){
        this.requiredLevel = 0;
        this.requiredItemEnum = requiredItem;
        this.requiredOccupation = null;
    } // end constructor

    public Requirement(Occupation requiredOccupation) {
        this.requiredLevel = 0;
        this.requiredItemEnum = null;
        this.requiredOccupation = requiredOccupation;
    } // end constructor

    /* takes in players inventory and iterates through pack to determine if player
       has required item*/
   /* public boolean hasRequiredItem(Inventory inventory){
        if(requiredItem != null){
            for(int i = 0; i < inventory.size(); i++) {
                if (inventory.pack.items[i] == requiredItem) {
                    return true;
                }
            }
            return false;
        }
        else{
            return true;
        }
    }*/

    /*public boolean meetsRequirements(int playerLevel, Inventory inventory){
        if(hasRequiredItem(inventory) && meetsLevel(playerLevel)){
            return true;
        }
        else{977\7\
            return false;
        }
     */
}
