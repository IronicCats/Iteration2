package Model.Inventory;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.Item.Items.Takables.Money;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by broskj on 3/6/16.
 * <p>
 * Contains a pack and worn equipment.
 */
public class Inventory implements Subject {

    private Pack pack;
    private Equipment equipment;
    private ArrayList<Observer> observers;

    public Inventory() {
        this.pack = new Pack();
        this.equipment = new Equipment();
        observers = new ArrayList<>();
    } // end default constructor

    public Inventory(Pack pack, Equipment equipment) {
        this.pack = pack;
        this.equipment = equipment;
        observers = new ArrayList<>();
    } // end constructor

    public boolean packFull() {
        return pack.full();
    } // end packFull

    public boolean packEmpty() {
        return pack.empty();
    } // end packEmpty

    public void equip(Weapon weapon) {
        System.out.println("Equipping " + weapon.getName());
        equipment.equip(weapon);
        alert();
    } // end equip

    public void equip(Armor armor) {
        System.out.println("Equipping " + armor.getName());
        equipment.equip(armor);
        alert();
    } // end equip

    public void unequip(EquipmentSlotEnum slot) {
        System.out.println("Unequipping item in " + slot + " slot.");
        pack.place(equipment.unequip(slot));
        alert();
    } // end unequip

    public Item get(int index) {
        return pack.get(index);
    } // end get

    public void place(Item item) {
        if (item instanceof Money) {                         /* item type is money, add quantity to money var */
            modifyMoney(((Money) item).getQuantity());

            return;
        }
        pack.place(item);
        System.out.println(item.getName() + " added to inventory");
    } // end place

    public boolean contains(ItemsEnum itemsEnum) {
        if(pack.contains(itemsEnum)) {
            return true;
        }
        return false;
    } // end contains

    public void modifyMoney(int amount) {
        pack.modifyMoney(amount);
        System.out.println("Money is " + pack.getMoney());
    } // end spendMoney

    public Item remove(int index) {
        Item item = pack.remove(index);
        return item;
    } // end remove

    public void examine() {
        pack.examine();
    } // end examine

    public ArrayList<Item> emptyPack() {
        return pack.dump();
    } // end emptyPack

    public Takable getSlot(EquipmentSlotEnum slot) {
        return equipment.getSlot(slot);
    } // end getSlot

    public int getPackSpaceLeft() {
        return pack.getSizeLeft();
    }

    public Equipment getEquipment() {
        return equipment;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    } // end addObserver

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    } // end removeObserver

    @Override
    public void alert() {
        for (Observer o : observers) {
            o.update();
        }
    } // end alert

    public Pack getPack() {
        return pack;
    }
} // end class Inventory
