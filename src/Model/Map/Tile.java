package Model.Map;

import Model.Location;
import Utilities.Observer;
import Utilities.Subject;

/**
 * Created by Aidan on 3/1/2016.
 */
public abstract class Tile implements Subject {

    private Location location;
    protected Observer observer;
    public boolean IsWalkable;

    public Tile(Location location, boolean IsWalkable){
        this.location = location;
        this.IsWalkable = IsWalkable;
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
