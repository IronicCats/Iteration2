package Model.GameObject.Item;

import Model.Location;
import Utitlies.Observer;
import Utitlies.Subject;


/**
 * Created by Wimberley on 2/25/16.
 */
public abstract class Item implements Subject {

    protected ItemEnum id; // used to determine type of item
    protected String name;
    protected String description;
    protected Location location; // location of item on map
    protected Observer observer;

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

}
