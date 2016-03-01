package Model.GameObject.Item.Items.Takables.Equippable;

import Model.GameObject.Entity.StatsEnum;
import Model.GameObject.Item.ItemEnum;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;
import Model.Effects.Effect;
import Utitlies.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Weapon extends Takable {

    public Weapon(ItemEnum id, String name, String description, Location location, Requirement requirement, Effect effect) {
        super(id, name, description, location, requirement, effect);
    }

    // used if weapon is worn out to lessen damage it inflicts
    public void setDamage(int damage) {
        effect.getModification().modifyStat(StatsEnum.LIFE, damage);
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
