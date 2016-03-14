package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Requirement;

/**
 * Created by mazumderm on 3/12/2016.
 */
public class RemoveTrap extends Abilities {

    public RemoveTrap(String name, String description, Effect effects, Requirement requirement, int manaCost, int range, int cooldown, int skillLevel){
        super(name, description, effects, requirement, manaCost, range, cooldown, skillLevel);
    }
}
