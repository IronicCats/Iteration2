package Model.Abilities;

import Model.Effects.Effect;
import Model.Requirement;

/**
 * Created by Aidan on 3/14/2016.
 */
public class CreepAbility extends Abilities{

    public CreepAbility(String name, String description, Effect effects, Requirement requirement, int cost, int range, int cooldown){
        super(name, description, effects, requirement, cost, 2, cooldown);

    }
}
