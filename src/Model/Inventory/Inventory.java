package Model.Inventory;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by broskj on 3/6/16.
 *
 * Contains a pack and worn equipment.
 */
public class Inventory implements Subject{
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

    public void equip(Weapon weapon) {
        equipment.equip(weapon);
        alert();
    } // end equip

    public void equip(Armor armor) {
        equipment.equip(armor);
        alert();
    } // end equip

    public void unequip(EquipmentSlotEnum slot) {
        pack.place(equipment.unequip(slot));
        alert();
    } // end unequip

    public Item get(int index) {
        return pack.get(index);
    } // end get

    public void place(Item item) {
        pack.place(item);
        System.out.println(item.getName() + " added to inventory");
        alert();
    } // end place

    public Item remove(int index) {
        Item item = pack.remove(index);
        alert();
        return item;
    } // end remove

    public void examine() {
        pack.examine();
    } // end examine

    public void emptyPack() {
        pack.empty();
    } // end emptyPack

    public Takable getSlot(EquipmentSlotEnum slot) {
        return equipment.getSlot(slot);
    } // end getSlot

    public int getPackSpaceLeft() {
        return pack.getSizeLeft();
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
} // end class Inventory
