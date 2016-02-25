package Model.GameObject.Item.Items.Takeable.Equippable;

import Model.GameObject.Item.ItemEnum;
import Model.GameObject.Item.Items.Takeable.Takeable;
import Model.Location;
import Model.Requirement;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Armor extends Takeable {

    int defense;

    public Armor(ItemEnum id, String name, String description, Location location, int imageId, Requirement requirements, Effect[] effects, int defense) {
        super(id, name, description, location, imageId, requirements, effects);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
