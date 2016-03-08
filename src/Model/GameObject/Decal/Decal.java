package Model.GameObject.Decal;

import Model.GameObject.GameObject;
import Model.Location;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Decal extends GameObject implements Subject {
    protected Location location; // location of decal
    private String name;
    private String description;
    private DecalEnum type;
    private ArrayList<Observer> observers;

    //constructor
    public Decal(Location location, DecalEnum type )
    {
        super(location);
        this.type = type;
    }

    //accessor methods

    public DecalEnum getType() {return type;}



}
