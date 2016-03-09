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
    private final int cap = 16;
    private int money;

    public Pack() {
        items = new Item[cap];
        for(int i = 0; i < cap; i++) { items[i] = null; }
        count = 0;
        money = 0;
    } // end default constructor

    public Pack(Item[] items, int money) {
        items = new Item[cap];
        count = items.length;
        this.items = items;
        this.money = money;
    } // end constructor

    public boolean full() {
        return count == cap;
    } // end full

    public boolean empty() {
        return count == 0;
    } // end empty

    public void place(Item item) {
        /*
        place an item at the first open position in the items array
         */
        for(int i = 0; i < cap; i++) {
            if(items[i] == null) {
                items[i] = item;
                count++;
                return;
            }
        }
    } // end place

    public Item remove(int index) {
        Item anItem = items[index];
        items[index] = null;
        count--;
        return anItem;
    } // end remove

    public Item get(int index) {
        return items[index];
    } // end get

    public void examine() {
        if(count == 0) {
            System.out.println("Pack empty.");
        }
        for(int i = 0; i < cap; i++) {
            if(items[i] != null) {
                System.out.println(i + ": " + items[i].getName());
            }
        }
    } // end examine
    public int getSizeLeft() {
        return cap - count;
    }

    public ArrayList<Item> dump() {
        ArrayList<Item> tempItems = new ArrayList<>();
        for(int i = 0; i < items.length; ++i){
            if(items[i] != null) {
                tempItems.add(items[i]);
                items[i] = null;
            }
        }
        return tempItems;
    } // end dump

    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }
    public void modifyMoney(int money) { this.money += money; }
} // end class Pack
