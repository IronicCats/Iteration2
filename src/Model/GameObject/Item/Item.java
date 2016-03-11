package Model.GameObject.Item;

import Model.GameObject.GameObject;
import Model.Location;
import Model.Requirement;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.Observer;
import Utilities.Subject;
import View.Views.ItemView;

import java.util.ArrayList;


/**
 * Created by Wimberley on 2/25/16.
 */

// Parent class of all items. Holds attributes common to ALL items
public abstract class Item extends GameObject implements Subject {

    private int id; // used to determine type of item
    private String name;
    private String description;
    private int value;
    private ArrayList<Observer> observers;
    private ItemsEnum type;

    public Item() {
        super();
        id = 0;
        name = "";
        description = "";
        value = 0;
        type = ItemsEnum.values()[id];
    } // end default constructor

    public Item(int id, String name, String description, int value, Location location) {
        super(location);
        observers = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        type = ItemsEnum.values()[id];
    } // end constructor

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public ItemsEnum getItemType() { return type; }
    public int getValue() { return value; }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void alert() {
        for(int i = 0; i < observers.size(); ++i) {
            observers.get(i).update();
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
