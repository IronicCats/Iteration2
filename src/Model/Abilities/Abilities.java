package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.SkillsEnum;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;

/**
 * Created by mazumderm on 3/5/2016.
 */
public abstract class Abilities {
    String name;
    String description;
    Effect effects;
    Requirement requirement;
    Effect cost;
    int manaCost;
    int range;
    int cooldown;
    //constructor
    public Abilities(String name, String description, Effect effects, Requirement requirement, int manaCost, int range, int cooldown) {

        this.name = name;
        this.description = description;
        this.effects = effects;
        this.requirement = requirement;
        this.cost = new Effect(new StatStructure(StatsEnum.MANA, manaCost));
        this.range = range;
        this.cooldown = cooldown;
        this.manaCost = manaCost;
    }

    //accessor
    public String getName(){
        return this.name;
    }
    public Effect getEffects(){
        return this.effects;
    }

    public void setEffects(Effect effects){
        this.effects = effects;
    }

    public Requirement getRequirement(){
        return this.requirement;
    }

    public void setRequirement(Requirement r){
        this.requirement = r;
    }

    public Effect getCost(){
        return this.cost;
    }

    public void setCost(Effect cost){
        this.cost = cost;
    }

    public int getRange(){
        return this.range;
    }

    public void setRange(int range){
        this.range = range;
    }

    public int getCooldown(){
        return this.cooldown;
    }

    public int getManaCost(){
        return this.manaCost;
    }

    public void setManaCost(int manaCost){
        this.manaCost = manaCost;
    }

}
