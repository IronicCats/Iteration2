package Model.Map;

import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Utilities.Observer;
import Utilities.Subject;

import javax.xml.stream.events.EntityReference;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Aidan on 3/1/2016.
 */
public abstract class Tile implements Subject {

    private Location location;
    protected Observer observer;
    public boolean IsWalkable;
    private ArrayList<Item> items;
    private Entity entity;

    public Tile(Location location, boolean IsWalkable){
        items = new ArrayList<>();
        entity = null;
        this.location = location;
        this.IsWalkable = IsWalkable;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Entity getEntity() {
        return entity;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean hasEntity() {
        return entity != null;
    }

    public boolean hasItems() {
        return items.size() != 0;
    }

    public Location getLocation(){
        return location;
    }

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void alert() {

    }

}
