package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Map.Tile;

/**
 * Created by mazumderm on 3/5/2016.
 */
public class Abilities {
    Effect[] effects;

    //constructor
    public Abilities(Effect[] effects) {
        this.effects = effects;
    }

    //accessor
    public Effect [] getEffects(){
        return this.effects;
    }

    public void setEffects(Effect [] effects){
        this.effects = effects;
    }

    //operations
    public void execute(Tile t){

    }
}
