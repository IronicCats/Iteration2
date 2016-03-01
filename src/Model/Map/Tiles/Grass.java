package Model.Map.Tiles;

import Model.Location;
import Utitlies.Observer;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Grass extends Tile{
    public Grass(Location location){
        super(location, false);
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
