package Model.GameObject.AreaEffect;

import Model.GameObject.GameObject;
import Model.Location;
import Model.Effects.*;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class AreaEffect extends GameObject {

    String name;
    String description;
    Effect effect;
    //animation?
    Location location;
    AreaEffectEnum areaEffect;

    //constructor
    public AreaEffect(String name, String description, AreaEffectEnum areaEffect, Location location)
    {
        super(location);
        this.name = name;
        this.description = description;
        this.areaEffect = areaEffect;

        //modifications will be made based on changes to effects, stats, and rendering

         if(areaEffect == AreaEffectEnum.DAMAGE)// if its damaging area effect
         {
         StatStructure modification = new StatStructure(StatsEnum.LIFE, -5);
         effect = new Effect(modification);
         }
         else if(areaEffect == AreaEffectEnum.HEAL)// if its healing area effect
         {
         StatStructure modification = new StatStructure(StatsEnum.LIFE, 5);
         effect = new Effect(modification);
         }
         else if(areaEffect == AreaEffectEnum.DEATH)// if its a death effect
         {
         StatStructure modification = new StatStructure(StatsEnum.LIVES_LEFT, -1);
         effect = new Effect(modification);
         }
         else if(areaEffect == AreaEffectEnum.LEVELUP)// if its healing area effect
         {
         StatStructure modification = new StatStructure(StatsEnum.LEVEL, 1);
         effect = new Effect(modification);
         }
         else if(areaEffect == AreaEffectEnum.TELEPORT)
         {
         //code depends on changes made to Stats and Effect
         }
         else if(areaEffect == AreaEffectEnum.TRAP)
         {
         //code depends on changes made to Stats and Effect
         }

    }

    //accesor methods
    public String getDescription() {return description;}

    public String getName() {return name;}

    public AreaEffectEnum getAreaEffect() {return areaEffect;}

    public Location getLocation() {return location;}

    public Effect getEffect() {return effect;}

}