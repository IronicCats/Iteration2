package Model.GameObject.Item.Items.Takables.Equippable;

import Model.GameObject.Item.ItemEnum;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;
import Model.Effects.Effect;
import Utilities.Observer;
import Model.GameObject.Entity.StatsEnum;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Armor extends Takable {

    public Armor(ItemEnum id, String name, String description, Location location, Requirement requirements, Effect effect) {
        super(id, name, description, location, requirements, effect);
    }

    // used if armor is worn out to adjust defensive rating
    public void setDefense(int defense) {
        effect.getModification().modifyStat(StatsEnum.DEFENSIVE_RATING, defense);
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
