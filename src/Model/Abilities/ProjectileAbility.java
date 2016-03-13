package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Projectile;
import Model.Location;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class ProjectileAbility extends Abilities {
    Projectile projectile;


    public ProjectileAbility(String name, String description, Projectile projectile, Requirement requirements, Effect cost){
        super(name, description, projectile.getEffect(), requirements, cost, projectile.getRange());
        this.projectile = projectile;

    }

    //accessor methods

    public Projectile getProjectile(){
        return this.projectile;
    }

    public void setProjectile(Projectile p){
        this.projectile = p;
    }

}
