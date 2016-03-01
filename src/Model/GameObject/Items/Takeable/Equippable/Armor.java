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
