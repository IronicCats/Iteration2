package Model.GameObject.Items.Takeable;

import Model.GameObject.ItemEnum;
import Model.Location;
import Model.Requirement;
import Model.Effect;
import Utitlies.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Quest extends Takeable{

    Quest(ItemEnum id, String name, String description, Location location, int imageId, Requirement requirements, Effect []effects){
        super(id, name, description, location, imageId, requirements, effects);
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
