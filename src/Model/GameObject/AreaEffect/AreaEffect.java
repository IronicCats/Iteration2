package Model.GameObject.AreaEffect;

import Model.Effects.Effect;
import Model.Effects.ModificationEnum;
import Model.GameObject.GameObject;
import Model.Location;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class AreaEffect extends GameObject {

    String name;
    String description;
    Effect effect;
    AreaEffectEnum areaEffect;

    //constructor
    public AreaEffect(String name, String description, AreaEffectEnum areaEffect, Location location) {
        super(location);
        this.name = name;
        this.description = description;
        this.areaEffect = areaEffect;

        //modifications will be made based on changes to effects, stats, and rendering

        if (areaEffect == AreaEffectEnum.DAMAGE)// if its damaging area effect
        {
            StatStructure modification = new StatStructure(StatsEnum.LIFE, -5);
            effect = new Effect(modification);
        } else if (areaEffect == AreaEffectEnum.HEAL)// if its healing area effect
        {
            StatStructure modification = new StatStructure(StatsEnum.LIFE, 5);
            effect = new Effect(modification);
        } else if (areaEffect == AreaEffectEnum.DEATH)// if its a death effect
        {
            StatStructure modification = new StatStructure(StatsEnum.LIVES_LEFT, -1);
            effect = new Effect(modification);
        } else if (areaEffect == AreaEffectEnum.LEVELUP)// if its healing area effect
        {
            StatStructure modification = new StatStructure(StatsEnum.LEVEL, 1);
            effect = new Effect(modification);
        } else if (areaEffect == AreaEffectEnum.TRAP) {
            StatStructure modification = new StatStructure(StatsEnum.MOVEMENT, -100);
            effect = new Effect(modification, ModificationEnum.PERCENT, 5);
        }

    }

    //accesor methods
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public AreaEffectEnum getAreaEffect() {
        return areaEffect;
    }

    public Effect getEffect() {
        return effect;
    }

}
