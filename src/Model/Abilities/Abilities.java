package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;

/**
 * Created by mazumderm on 3/5/2016.
 */
public class Abilities {
    Effect[] effects;
    Requirement requirement;
    StatStructure cost;
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

    public Requirement getRequirement(){
        return this.requirement;
    }

    public void setRequirement(Requirement r){
        this.requirement = r;
    }

    //operations
    public void execute(Tile t){

    }
}
