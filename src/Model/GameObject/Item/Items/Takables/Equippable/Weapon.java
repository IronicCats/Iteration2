package Model.GameObject.Item.Items.Takables.Equippable;

import Model.Inventory.EquipmentTypeEnum;
import Model.Stats.StatsEnum;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;
import Model.Effects.EquipmentModification;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */

/* This item adds attack rating to the player. The value of the attack rating added is held within the statStruc
   in the effect attribute.
 */
public class Weapon extends Takable {
    EquipmentTypeEnum type;

    public Weapon() {
        super();
        type = null;
    } // end default constructor

    public Weapon(int id, String name, String description, Location location, Requirement requirement, EquipmentTypeEnum type, EquipmentModification e) {
        super(id, name, description, location, requirement, e);
        this.type = type;
    } // end constructor

    // returns value in the effect's statStruc associated with life
    public int getDamage(){
        return effect.getModification().getStat(StatsEnum.OFFENSIVE_RATING);
    }

    // used if weapon is worn out to lessen damage it inflicts
    public void setDamage(int damage) {
        effect.getModification().modifyStat(StatsEnum.OFFENSIVE_RATING, damage);
    }

    public EquipmentTypeEnum getType() { return type; }

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
