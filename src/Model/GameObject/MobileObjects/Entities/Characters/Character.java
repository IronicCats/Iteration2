package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by broskj on 3/6/16.
 */
public abstract class Character extends Entity {
    protected Inventory inventory;

    public Character() {
        super();
        this.inventory = new Inventory();
    } // end default constructor

    public Character(Location location, Occupation occupation, Inventory inventory) {
        super(location, occupation.getStats(), occupation);
        this.inventory = inventory;
    } // end constructor

    public Character(Location location, CharacterStats stats, Occupation occupation, Inventory inventory) {
         super(location, stats, occupation);
        this.inventory = inventory;
    } // end constructor

    public ArrayList<Item> takeItems(ArrayList<Item> items) {
        //System.out.println("Here");
        ArrayList<Item> tempItems = new ArrayList<>(items);
        Iterator<Item> it = tempItems.iterator();
        while(it.hasNext()){
            Item i = it.next();
            if(i instanceof Takable) {//if its takable
                if(pickup(i)) {//and i was able to pick it up
                    DisplayMessage.addMessage(new GameMessage("You picked up: " + i.getName(), 3));
                    items.remove(i); //remove it from the items
                }
            }
        }
        return items;
    }

    public boolean pickup(Item item) {

        if(inventory.getPackSpaceLeft() > 0){
            inventory.place(item);
            return true;
        }
        return false;
    } // end pickup
    public void emptyPack() {
        DisplayMessage.addMessage(new GameMessage("You emptied your Pack", 3));
        getTile().addItems(inventory.emptyPack());
    } // end emptyPack

} // end class Character
