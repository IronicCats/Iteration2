package Model;

import Model.GameObject.Item.Item;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Requirement {

    int requiredLevel; // used to tell if player is high enough level
    Item requiredItem; // used to tell if player has required item

    public Requirement(int requiredLevel, Item requiredItem){
        this.requiredLevel = requiredLevel;
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
        for(int i = 0; i < inventory.size(); i++) {
            if (inventory.pack.items[i] == requiredItem) {
                return true;
            }
        }
        return false;
    }*/
}
