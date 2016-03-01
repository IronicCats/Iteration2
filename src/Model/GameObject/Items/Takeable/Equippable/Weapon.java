package Model.GameObject.Items.Takeable.Equippable;

import Model.GameObject.ItemEnum;
import Model.GameObject.Items.Takeable.Takeable;
import Model.Location;
import Model.Requirement;
import Model.Effect;
import Utitlies.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Weapon extends Takeable {

    int damage;

    Weapon(ItemEnum id, String name, String description, Location location, int imageId, Requirement requirements, Effect[] effects, int damage) {
        super(id, name, description, location, imageId, requirements, effects);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
