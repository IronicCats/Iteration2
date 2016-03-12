package Model.GameObject.Item.Items.Takables.Equippable;

import Model.Effects.EquipmentModification;
import Model.Inventory.EquipmentTypeEnum;
import Model.Location;
import Model.Requirement;
import Model.Stats.StatsEnum;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */

/* This item adds attack rating to the player. The value of the attack rating added is held within the statStruc
   in the effect attribute.
 */
public class Weapon extends Equippable {
    public Weapon() {
        super();
    } // end default constructor

    public Weapon(int id, String name, String description, int value, Location location, Requirement requirement, EquipmentTypeEnum type, EquipmentModification e) {
        super(id, name, description, value, location, requirement, type, e);
    } // end constructor

    public int getDamage() {
        return effect.getModification().getStat(StatsEnum.OFFENSIVE_RATING);
    }

    public void setDamage(int damage) {
        effect.getModification().modifyStat(StatsEnum.OFFENSIVE_RATING, damage);
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
