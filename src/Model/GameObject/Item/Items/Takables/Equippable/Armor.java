package Model.GameObject.Item.Items.Takables.Equippable;

import Model.GameObject.Item.Items.Takable;
import Model.Inventory.EquipmentTypeEnum;
import Model.Location;
import Model.Requirement;
import Model.Effects.EquipmentModification;
import Utilities.Observer;
import Model.Stats.StatsEnum;

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

    public Armor(int id, String name, String description, Location location, Requirement requirements, EquipmentTypeEnum type, EquipmentModification e) {
        super(id, name, description, location, requirements, type, e);
    } // end constructor

    public int getDefense(){
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
