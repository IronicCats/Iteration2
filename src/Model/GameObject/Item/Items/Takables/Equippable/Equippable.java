package Model.GameObject.Item.Items.Takables.Equippable;

import Model.Effects.EquipmentModification;
import Model.GameObject.Item.Items.Takable;
import Model.Inventory.EquipmentTypeEnum;
import Model.Location;
import Model.Requirement;

/**
 * Created by broskj on 3/8/16.
 */
public abstract class Equippable extends Takable{
    private EquipmentTypeEnum type;

    public Equippable() {
        super();
        this.type = null;
    } // end default constructor

    public Equippable(int id, String name, String description, int value, Location location, Requirement requirements, EquipmentTypeEnum type, EquipmentModification e) {
        super(id, name, description, value, location, requirements, e);
        this.type = type;
    } // end constructor

    public EquipmentTypeEnum getType() { return type; }
    public EquipmentModification getEquipmentModification() { return (EquipmentModification) effect; }
} // end class Equippable
