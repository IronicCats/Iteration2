package Model.GameObject;

import Model.Location;

/**
 * Created by Wimberley on 2/25/16.
 */
public abstract class Item {

    protected ItemEnum id; // used to determine type of item
    protected String name;
    protected String description;
    protected Location location; // location of item on map
    protected int imageId; // used so view can assign image to item

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

    public int getImageId() {
        return imageId;
    }
}
