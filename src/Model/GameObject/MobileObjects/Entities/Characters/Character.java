package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Stats.CharacterStats;

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

    public ArrayList<Item> takeItems(ArrayList<Item> items) {
        ArrayList<Item> tempItems = new ArrayList<>(items);
        Iterator<Item> it = tempItems.iterator();
        while(it.hasNext()){
            Item i = it.next();
            if(i instanceof Takable && inventory.getPackSpaceLeft() > 0) {
                pickup(i);
                items.remove(i);
            }
        }
        return items;
    }

    public void pickup(Item item) {
        inventory.place(item);
    } // end pickup
} // end class Character
