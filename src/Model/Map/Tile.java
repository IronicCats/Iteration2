package Model.Map;

import Model.Location;
import Utilities.Observer;
import Utilities.Subject;

/**
 * Created by Aidan on 3/1/2016.
 */
public abstract class Tile implements Subject {

    public static final int TILEWIDTH = 64, TILEHEIGHT = 56;
    private Location location;
    protected Observer observer;
    public boolean IsWalkable;

    public Tile(Location location, boolean IsWalkable){
        this.location = location;
        this.IsWalkable = IsWalkable;
    }


}
