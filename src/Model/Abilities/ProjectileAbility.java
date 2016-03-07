package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Projectile;
import Model.Map.Tile;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class ProjectileAbility extends Abilities {
    int range;
    Projectile projectile;


    public ProjectileAbility(int range, Projectile projectile, Effect[] e){
        super(e);
        this.range = range;
        this.projectile = projectile;

    }

    //accessor methods
    public int getRange(){
        return this.range;
    }

    public void setRange(int range){
        this.range = range;
    }

    public Projectile getProjectile(){
        return this.projectile;
    }

    public void setProjectile(Projectile p){
        this.projectile = p;
    }

    public void execute(Tile t){

    }
}
