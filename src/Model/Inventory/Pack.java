package Model.Inventory;

import Model.Effects.Effect;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Money;
import Model.GameObject.Item.Items.Takables.Usable;
import Model.Location;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;

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

    public ArrayList<Item> dump(Location location) {
        ArrayList<Item> tempItems = new ArrayList<>();
        for (int i = 0; i < items.length; ++i) {
            if (items[i] != null) {
                tempItems.add(items[i]);
                items[i] = null;
                --count;
            }
        }

        Money moneyToDrop = getMoneyAsItem(location);
        if(moneyToDrop != null) {
            tempItems.add(getMoneyAsItem(location));
            money = 0;
        }
        return tempItems;
    } // end dump

    public Money getMoneyAsItem(Location location) {
        Money moneyToGet;
        if(money == 0) {
            return null;
        }
        if(money < 10) {
            moneyToGet = (Money)ItemFactory.makeItem(ItemsEnum.SMALL_COIN_STACK, location);
        } else if (money < 25) {
            moneyToGet = (Money)ItemFactory.makeItem(ItemsEnum.MEDIUM_COIN_STACK, location);
        } else {
            moneyToGet = (Money)ItemFactory.makeItem(ItemsEnum.LARGE_COIN_STACK, location);
        }
        moneyToGet.setQuantity(money);
        return moneyToGet;
    } // end getMoneyAsItem

    public void setNull(int i){
        items[i]=null;
    }


    public boolean contains(ItemsEnum itemsEnum) {
        if(itemsEnum == null) {
            return true;
        }
        for(int i = 0; i < cap; i++) {
            if(items[i] != null) {
                if(items[i].getItemType() == itemsEnum) {
                    return true;
                }
            }
        }
        return false;
    } // end contains

    public void remove(ItemsEnum itemsEnum) {
        if(itemsEnum == null) {
        }
        for(int i = 0; i < cap; i++) {
            if(items[i] != null) {
                if(items[i].getItemType() == itemsEnum) {
                    remove(i);
                }
            }
        }
    } // end contains

    public int getCount() { return count; }
    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }
    public void modifyMoney(int money) { this.money += money; }
} // end class Pack
