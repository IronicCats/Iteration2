package Model.GameObject;

import Model.Location;
import Model.Tickable;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 3/1/2016.
 */
public abstract class GameObject implements Subject, Tickable {
    private ArrayList<Observer> observers;
    protected Location location;
    public GameObject() {
        observers = new ArrayList<>();
        location = new Location(0,0);}
    public GameObject(Location location) {
        observers = new ArrayList<>();
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location l) {
        location = l;
    }

    public int getX() {
        return location.getX();
    }
    public int getY() {
        return location.getY();
    }

    public String toString() {
        return "GameObject with Location: " + location.toString();
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
        for(Observer o: observers){
            o.update();
        }
    }

    @Override
    public void tick() {
        
    }
}
