package Model.GameObject.Item.Items.Takables.Equippable;

import Model.GameObject.Item.ItemEnum;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;
import Model.Effects.Effect;
import Utilities.EquipmentModification;
import Utilities.Observer;
import Model.Stats.StatsEnum;

/**
 * Created by Wimberley on 2/25/16.
 */

/* This item adds defense rating to the player. The value of the defense rating added is held within the statStruc
   in the effect attribute.
 */
public class Armor extends Takable {

    public Armor(ItemEnum id, String name, String description, Location location, Requirement requirements, EquipmentModification e) {
        super(id, name, description, location, requirements, e);
    }

    // returns value in effect's statStruc associated with defensive rating
    public int getDefense(){
        return effect.getModification().getStat(StatsEnum.DEFENSIVE_RATING);
    }

    // used if armor is worn out to adjust defensive rating
    public void setDefense(int defense) {
        effect.getModification().modifyStat(StatsEnum.DEFENSIVE_RATING, defense);
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
