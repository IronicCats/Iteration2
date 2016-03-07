package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.Map.Tile;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class DirectAbility extends Abilities {
    Weapon weapon;
    //constructor
    public DirectAbility(Effect[] e){
        super(e);

    }

    public DirectAbility(Effect[] e, Weapon w){
        super(e);
    }

    public Weapon getWeapon(){
        return this.getWeapon();
    }

    public void setWeapon(Weapon w){
        this.weapon = w;
    }

    public void execute(Tile t){

    }
}
