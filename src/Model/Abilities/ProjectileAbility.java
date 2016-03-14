package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Projectile;
import Model.Location;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.Stats;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class ProjectileAbility extends Abilities {
    Projectile projectile;
    Stats projectileStats;

    public ProjectileAbility(String name, String description, Stats stats, Effect effect, Requirement requirements, int cost, int cooldown){
        super(name, description, effect, requirements, cost,2, cooldown);
        this.projectileStats = stats;

    }

    //accessor methods

    public Projectile getProjectile(){
        return this.projectile;
    }

    public void setProjectile(Projectile p){
        this.projectile = p;
    }

    public Stats getProjectileStats(){
        return this.projectileStats;
    }

    public void setProjectileStats(Stats stats){
        this.projectileStats = stats;
    }

}
