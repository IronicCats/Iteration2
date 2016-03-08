package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Location;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;

/**
 * Created by mazumderm on 3/5/2016.
 */
public abstract class Abilities {
    Effect[] effects;
    Requirement requirement;
    StatStructure cost;
    //constructor
    public Abilities(Effect[] effects, Requirement requirement, StatStructure cost) {

        this.effects = effects;
        this.requirement = requirement;
        this.cost = cost;
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

    public StatStructure getCost(){
        return this.cost;
    }

    public void setCost(){
        this.cost = cost;
    }

    //operations
    public abstract void execute(Location location);
}
