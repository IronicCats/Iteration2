package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.Location;

/**
 * Created by Wimberley on 3/3/16.
 */

// class for projectiles
public class Projectile extends MobileObject {

    private Effect effect;

    public Projectile(Location location) {
        super(location);
    }

    public Effect getEffect() {
        return effect;
    }

    @Override
    public void move(){
        // some code
    }

}
