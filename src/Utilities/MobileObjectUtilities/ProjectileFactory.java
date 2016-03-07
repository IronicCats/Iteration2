package Utilities.MobileObjectUtilities;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.GameObject.MobileObjects.Projectile;
import View.Views.MobileObjectView;

/**
 * Created by Wimberley on 3/6/16.
 */
public class ProjectileFactory {
    public static void makeProjectile(MobileObjectEnum projectileEnum) {
        switch (projectileEnum) {
            case LASER:
            case BOOMERANG:
            default:
                break; /* return null; */
        }
    } // end makeNPC

    public static MobileObjectView makeAsset(MobileObjectEnum projectileEnum, Projectile projectile) {
        switch (projectileEnum) {
            case LASER:
            case BOOMERANG:
            default:
                break;
        }
        return null;
    } // end makeAsset
}
