package Model.GameObject.Item.Items.Takables.Equippable;

import Model.Stats.StatsEnum;
import Model.GameObject.Item.ItemEnum;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;
import Model.Effects.Effect;
import Utilities.EquipmentModification;
import Utilities.Observer;

/**
 * Created by Wimberley on 2/25/16.
 */

/* This item adds attack rating to the player. The value of the attack rating added is held within the statStruc
   in the effect attribute.
 */
public class Weapon extends Takable {

    public Weapon(ItemEnum id, String name, String description, Location location, Requirement requirement, EquipmentModification e) {
        super(id, name, description, location, requirement, e);
    }

    // returns value in the effect's statStruc associated with life
    public int getDamage(){
        return effect.getModification().getStat(StatsEnum.LIFE);
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
