package Model.Effects;

import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;

/**
 * Created by broskj on 3/5/16.
 *
 * This class is designed such that every equippable item will have an EquipmentModification object.  It serves as the
 *  container for its armor/weapon value as well as any effects it has, e.g. a sword can exist that has a damage of 5
 *  that also increases movement by 1.  This type of sword would have its StatStructure created with OFFENSIVE_RATING
 *  set to 5 and MOVEMENT set to 1.  Then, when equipped, those modifications will be applied to the player's stats and
 *  removed when unequipped.
 *
 * By design, no piece of equipment should have a duration other than 0.
 */
public class EquipmentModification extends Effect {
    private StatStructure stats;
    private ModificationEnum type;
    private boolean weapon, armor;

    public EquipmentModification() {
        super();
    } // end default constructor

    public EquipmentModification(StatStructure stats) {
        super(stats, ModificationEnum.REAL, 0);
        weapon = false;
        armor = false;
        if(this.stats.getKeySet().contains(StatsEnum.OFFENSIVE_RATING)) {
            weapon = true;
        }
        if(this.stats.getKeySet().contains(StatsEnum.ARMOR_RATING)) {
            armor = true;
        }
    } // end constructor

    public EquipmentModification(StatStructure stats, ModificationEnum m) {
        super(stats, m, 0);
        weapon = false;
        armor = false;
        if(this.stats.getKeySet().contains(StatsEnum.OFFENSIVE_RATING)) {
            weapon = true;
        }
        if(this.stats.getKeySet().contains(StatsEnum.ARMOR_RATING)) {
            armor = true;
        }
    } // end constructor

    public StatStructure getStats() { return stats; }
    public ModificationEnum getType() { return type; }
    public int getWeaponRating() { return stats.getStat(StatsEnum.OFFENSIVE_RATING); }
    public int getArmorRating() { return stats.getStat(StatsEnum.ARMOR_RATING); }
    public boolean hasWeaponValue() { return weapon; }
    public boolean hasArmorValue() { return armor; }
} // end class
