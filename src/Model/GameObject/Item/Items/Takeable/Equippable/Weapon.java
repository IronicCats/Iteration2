package Model.GameObject.Item.Items.Takeable.Equippable;

import Model.GameObject.Item.ItemEnum;
import Model.GameObject.Item.Items.Takeable.Takeable;
import Model.Location;
import Model.Requirement;
import Model.Effect;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Weapon extends Takeable{

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
}
