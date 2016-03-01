package Model;

import Model.GameObject.Item.Items.Takables.Quest;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Requirement {

    private int requiredLevel = 0; // used to tell if player is high enough level
    private Quest requiredItem = null; // used to tell if player has required item

    // constructor for requirement needing both item level and specific item
    public Requirement(int requiredLevel, Quest requiredItem){
        this.requiredLevel = requiredLevel;
        this.requiredItem = requiredItem;
    }

    // constructor for requirement needing only player level
    public Requirement(int requiredLevel){
        this.requiredLevel = requiredLevel;
    }

    // constructor for requirement needing only specific item
    public Requirement(Quest requiredItem){
        this.requiredItem = requiredItem;
    }

    public boolean meetsLevel(int playerLevel){ // pass in player level, returns true if player meets required level
        if(playerLevel >= requiredLevel){
           return true;
        }
        else return false;
    }


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
}
