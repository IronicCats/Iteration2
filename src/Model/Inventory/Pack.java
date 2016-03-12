package Model.Inventory;

import Model.Effects.Effect;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Money;
import Model.GameObject.Item.Items.Takables.Usable;

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
        for (int i = 0; i < cap; i++) {
            items[i] = null;
        }
        count = 0;
        money = 0;
    } // end default constructor

    public Pack(Item[] items, int money) {
        this.items = new Item[cap];
        count = items.length;
        for (int i = 0; i < count; i++) {
            this.items[i] = items[i];
        }
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
        for (int i = 0; i < cap; i++) {
            if (items[i] == null) {
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

    public Item[] getItems() {
        return items;
    }

    public Effect use(int i) {
        Effect temp = ((Usable) items[i]).getEffect();
        items[i] = null;
        return temp;
    }

    public void examine() {
        if (count == 0) {
            System.out.println("Pack empty.");
        } else {
            for (int i = 0; i < cap; i++) {
                if (items[i] != null) {
                    System.out.println(i + ": " + items[i].getName());
                }
            }
        }
        System.out.println(getMoney());
    } // end examine

    public int getSizeLeft() {
        return cap - count;
    }

    public ArrayList<Item> dump() {
        ArrayList<Item> tempItems = new ArrayList<>();
        for (int i = 0; i < items.length; ++i) {
            if (items[i] != null) {
                tempItems.add(items[i]);
                items[i] = null;
            }
        }
        tempItems.add(new Money(money));
        return tempItems;
    } // end dump

    public int getCount() {
        return count;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void modifyMoney(int money) {
        this.money += money;
    }
} // end class Pack
