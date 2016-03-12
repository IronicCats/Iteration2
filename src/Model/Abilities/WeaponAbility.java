package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class WeaponAbility extends Abilities {
    Weapon weapon;
    //constructor

    public WeaponAbility(String name, String description, Effect e, Weapon w, Requirement requirements, Effect cost){
        super(name, description, e, requirements, cost);
        this.weapon = w;
    }

    public Weapon getWeapon(){
        return this.getWeapon();
    }

    public void setWeapon(Weapon w){
        this.weapon = w;
    }

    public void execute(MobileObject m, Location targeterLocation){

    }
}
