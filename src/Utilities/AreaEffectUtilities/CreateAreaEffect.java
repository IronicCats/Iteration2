package Utilities.AreaEffectUtilities;

import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.Location;

/**
 * Created by mazumderm on 3/3/2016.
 */
public class CreateAreaEffect {

    public static AreaEffect addDamage(String name, String description, Location location) {
        return new AreaEffect(
                name,
                description,
                AreaEffectEnum.DAMAGE,
                location
        );
    }

    public static AreaEffect addHeal(String name, String description, Location location) {
        return new AreaEffect(
                name,
                description,
                AreaEffectEnum.HEAL,
                location
        );
    }

    public static AreaEffect addDeath(String name, String description, Location location) {
        return new AreaEffect(
                name,
                description,
                AreaEffectEnum.DEATH,
                location
        );
    }

    public static AreaEffect addLevelUp(String name, String description, Location location) {
        return new AreaEffect(
                name,
                description,
                AreaEffectEnum.LEVELUP,
                location
        );
    }

    public static AreaEffect addTeleport(String name, String description, Location location) {
        return new AreaEffect(
                name,
                description,
                AreaEffectEnum.TELEPORT,
                location
        );
    }

    public static AreaEffect addTrap(String name, String description, Location location) {
        return new AreaEffect(
                name,
                description,
                AreaEffectEnum.TRAP,
                location
        );
    }
}
