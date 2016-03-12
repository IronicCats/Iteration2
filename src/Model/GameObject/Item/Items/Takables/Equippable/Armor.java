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

/* This item adds defense rating to the player. The value of the defense rating added is held within the statStruc
   in the effect attribute.
 */
public class Armor extends Equippable {
    public Armor() {
        super();
    } // end default constructor

    public Armor(int id, String name, String description, int value, Location location, Requirement requirements, EquipmentTypeEnum type, EquipmentModification e) {
        super(id, name, description, value, location, requirements, type, e);
    } // end constructor

    public int getDefense() {
        return effect.getModification().getStat(StatsEnum.ARMOR_RATING);
    }

    public void setDefense(int defense) {
        effect.getModification().modifyStat(StatsEnum.ARMOR_RATING, defense);
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
