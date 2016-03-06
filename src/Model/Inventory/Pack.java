package Model.Inventory;

import Model.GameObject.Item.Item;
import Utilities.Observer;

import java.util.ArrayList;

/**
 * Created by broskj on 3/6/16.
 */
public class Pack {
    private Item[] items;
    private int count;
    private final int cap;

    public Pack() {
        items = new Item[]{};
        count = 0;
        cap = 16;
    } // end default constructor

    public Pack(Item[] items) {
        this.items = items;
        count = items.length;
        cap = 16;
    } // end constructor

    public void place(Item item) {
        /*
        place an item at the first open position in the items array
         */
        for(int i = 0; i < cap; i++) {
            if(items[i].equals(null)) {
                items[i] = item;
                return;
            }
        }
    } // end place

    public Item remove(int index) {
        Item anItem = items[index];
        items[index] = null;
        return anItem;
    } // end remove

    public Item get(int index) {
        return items[index];
    } // end get
} // end class Pack
