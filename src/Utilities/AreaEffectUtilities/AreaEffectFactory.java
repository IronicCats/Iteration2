package Utilities.AreaEffectUtilities;


import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.AreaEffect.TeleportAreaEffect;
import Model.Location;
import View.ViewUtilities.Graphics.Assets;
import View.Views.DecalView;

import java.util.ArrayList;


/**
 * Created by mazumderm on 3/6/2016.
 */
public class AreaEffectFactory {

    public static ArrayList<AreaEffect> init(){
        ArrayList<AreaEffect> initial = new ArrayList<>();

        initial.add(AreaEffectFactory.makeAreaEffect(AreaEffectEnum.LEVELUP, new Location(10, 5)));
        initial.add(AreaEffectFactory.makeAreaEffect(AreaEffectEnum.HEAL, new Location(10, 12)));
        initial.add(AreaEffectFactory.makeAreaEffect(AreaEffectEnum.DAMAGE, new Location(10, 6)));
        initial.add(AreaEffectFactory.makeAreaEffect(AreaEffectEnum.DEATH, new Location(10, 8)));
        initial.add(AreaEffectFactory.makeAreaEffect(AreaEffectEnum.TRAP, new Location(8, 9)));

        return initial;
    }

    public static AreaEffect makeAreaEffect(AreaEffectEnum a, Location location) {
        switch (a) {
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
    }// end

    public static TeleportAreaEffect makeTeleportAreaEffect(Location beginning, Location ending) {
        return new TeleportAreaEffect(beginning, ending);
    }// end

    public static DecalView makeAsset(AreaEffectEnum areaEffectEnum, AreaEffect areaEffect) {
        switch (areaEffectEnum) {
            case DAMAGE:
                return new DecalView(areaEffect, Assets.FIRE);
            case LEVELUP:
                return new DecalView(areaEffect, Assets.STAR);
            case DEATH:
                return new DecalView(areaEffect, Assets.SKULL);
            case HEAL:
                return new DecalView(areaEffect, Assets.REDCROSS);
            case TELEPORT:
                return null;
            case TRAP:
                return null;
            default:
                return null;
        }
    } // end makeAsset

    public static DecalView makeAsset(TeleportAreaEffect a){
            return new DecalView(a, Assets.PORTAL);
    }
}
