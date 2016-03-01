package Model.GameObject.Decal;

import Model.Location;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Decal {
    private int imageID; // image of decal
    protected Location location; // location of decal
    private String name;
    private String description;
    private DecalEnum type;

    //constructor
    public Decal(int image, Location location, DecalEnum type )
    {
        this.imageID = image;
        this.location = location;
        this.type = type;
    }

    //accessor methods

    public DecalEnum getType() {return type;}

    public Location getLocation() {return location;}

    public int getImageID(){return imageID;}
}
