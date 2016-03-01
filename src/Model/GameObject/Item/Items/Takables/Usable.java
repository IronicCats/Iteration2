package Model.GameObject.Item.Items.Takables;

import Model.GameObject.Item.ItemEnum;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;
import Model.Effects.Effect;
import Utitlies.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Usable extends Takable {

    public Usable(ItemEnum id, String name, String description, Location location, Requirement requirement, Effect effect){
        super(id, name, description, location, requirement, effect);
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
