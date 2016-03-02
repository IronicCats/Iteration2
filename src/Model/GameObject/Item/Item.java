package Model.GameObject.Item;

import Model.Location;
import Utilities.Observer;
import Utilities.Subject;
import View.Views.ItemView;

import java.util.ArrayList;


/**
 * Created by Wimberley on 2/25/16.
 */
public abstract class Item implements Subject {

    protected ItemEnum id; // used to determine type of item
    protected String name;
    protected String description;
    protected Location location; // location of item on map
    protected ArrayList<Observer> observers;

    public Item() {
        observers = new ArrayList<>();
    }

    public ItemEnum getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

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
}
