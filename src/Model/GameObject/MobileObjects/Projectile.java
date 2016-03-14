package Model.GameObject.MobileObjects;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Location;
import Model.Map.Tile;
import Model.Stats.CharacterStats;
import Model.Stats.Stats;
import Utilities.AIUtilities.TilegivenBase;

/**
 * Created by Wimberley on 3/3/16.
 */

// class for projectiles
public class Projectile extends MobileObject {

    private Effect effects;
    int range;

    //constructor
    public Projectile(Location location, int id, Stats stats, Effect effects, int range) {
        super(location, id, stats);
        this.effects = effects;
        this.range = range;
    }

    //accessor methods
    public Effect getEffect() {
        return this.effects;
    }

    public void setEffects(Effect e) {
        this.effects = e;
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int r) {
        this.range = r;
    }

    public void execute(Location base) {
        Tile start = map.getTile(Location.newLocation(base.getDir(),base));
        System.out.println(start.getLocation().getX() + " " + start.getLocation().getY()) ;
        System.out.println((start.hasObject()));
        if(start.hasObject() && (!(start.getObject() instanceof  Projectile))){
            System.out.println("executing");
            start.receiveProjectileAttack(this);
        }
        else{
            //this.location = start.getLocation();
            //move(start.getLocation().getDir());
            //move(start.getLocation().getDir());
        }
    }

    public void applyEffect() {
        ((CharacterStats)getStats()).applyEffect(effects);
    } // end applyEffect

}
