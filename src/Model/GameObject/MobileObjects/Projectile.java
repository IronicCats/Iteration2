package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.Location;
import Model.Stats.Stats;

/**
 * Created by Wimberley on 3/3/16.
 */

// class for projectiles
public class Projectile extends MobileObject {

    private Effect [] effects;
    int range;

    //constructor
    public Projectile(Location location, Stats stats, Effect [] effects, int range) {
        super(location, stats);
        this.effects = effects;
        this.range = range;
    }

    //accessor methods
    public Effect [] getEffect() {
        return this.effects;
    }

    public void setEffects(Effect [] e){
        this.effects = e;
    }

    public int getRange(){
        return this.range;
    }

    public void setRange(int r){
        this.range = r;
    }

    @Override
    public void move(int degrees){
        // some code
    }

}
