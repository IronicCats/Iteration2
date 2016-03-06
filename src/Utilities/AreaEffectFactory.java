package Utilities;


import Model.Location;
import Model.Map.AreaEffect.AreaEffect;
import Model.Map.AreaEffect.AreaEffectEnum;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class AreaEffectFactory {
    public static AreaEffect makeAreaEffect(AreaEffectEnum a, Location location){
        switch(a){
            case DAMAGE:
                return CreateAreaEffect.addDamage(
                        "Damage",
                        "A tile that will take away player health",
                        location
                );
            case HEAL:
                return CreateAreaEffect.addHeal(
                        "Heal",
                        "A tile that will give the player a health boost",
                        location
                );
            case DEATH:
                return CreateAreaEffect.addHeal(
                        "Death",
                        "A tile that will cause the player to die",
                        location
                );
            case LEVELUP:
                return CreateAreaEffect.addLevelUp(
                        "Level Up",
                        "A tile that will cause the player to level up",
                        location
                );
            case TELEPORT:
                return CreateAreaEffect.addTeleport(
                        "Teleport",
                        "A tile that will cause the player to be transported to another area",
                        location
                );
            case TRAP:
                return CreateAreaEffect.addTrap(
                        "Teleport",
                        "A tile that will cause the player to be transported to another area",
                        location
                );
            default:
                return null;
        }
    }
}
