package Utilities;

import Model.Map.AreaEffect.AreaEffect;
import Model.Map.AreaEffect.AreaEffectEnum;

/**
 * Created by mazumderm on 3/3/2016.
 */
public class CreateAreaEffect {

    public static AreaEffect addDamage(String name, String description){
        return new AreaEffect(
                name,
                description,
                AreaEffectEnum.DAMAGE
        );
    }

    public static AreaEffect addHeal(String name, String description){
        return new AreaEffect(
                name,
                description,
                AreaEffectEnum.HEAL
        );
    }
}
