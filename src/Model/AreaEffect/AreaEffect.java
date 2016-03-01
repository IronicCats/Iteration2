package Model.AreaEffect;

import Model.Location;
import Model.Effects.*;
import Model.GameObject.Decal.*;
/**
 * Created by mazumderm on 3/1/2016.
 */
public class AreaEffect {

    String name;
    String description;
    Effect effect;
    //animation?
    Location location;
    AreaEffectEnum areaEffect;
    Decal decal;

    //constructor
    public AreaEffect(String name, String description, AreaEffectEnum areaEffect)
    {
        this.name = name;
        this.description = description;
        this.areaEffect = areaEffect;


        //modifications will be made based on changes to effects, stats, and rendering
        /**
        if(areaEffect == AreaEffectEnum.DAMAGE)// if its damaging area effect
        {
            StatStructure modification = new StatStructure(StatsEnum.LIFE, -5);
            effect = new Effect(modification,0, this.description);
            decal = new Decal(Assets.damage, this.location, DecalEnum.SKULLANDCROSSBONES);
        }
        else if(areaEffect == AreaEffectEnum.HEAL)// if its healing area effect
        {
            StatStructure modification = new StatStructure(StatsEnum.LIFE, 5);
            effect = new Effect(modification,0, this.description);
            decal = new Decal(Assets.redCross, this.location, DecalEnum.REDCROSS);
        }
        else if(areaEffect == AreaEffectEnum.DEATH)// if its a death effect
        {
            StatStructure modification = new StatStructure(StatsEnum.LIVES_LEFT, -1);
            effect = new Effect(modification,0, this.description);
            decal = new Decal(Assets.skullAndBones, this.location, DecalEnum.SKULLANDCROSSBONES);
        }
        else if(areaEffect == AreaEffectEnum.LEVELUP)// if its healing area effect
        {
            StatStructure modification = new StatStructure(StatsEnum.LEVEL, 1);
            effect = new Effect(modification,0, this.description);
            decal = new Decal(Assets.goldStar, this.location, DecalEnum.GOLDSTAR);
        }
         */
    }

    //accesor methods
    public String getDescription() {return description;}

    public String getName() {return name;}

    public AreaEffectEnum getAreaEffect() {return areaEffect;}

    public Location getLocation() {return location;}

    public Effect getEffect() {return effect;}

    public Decal getDecal() {return decal;}
}
