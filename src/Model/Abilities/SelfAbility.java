package Model.Abilities;

import Model.Effects.Effect;
import Model.Requirement;

/**
 * Created by mazumderm on 3/12/2016.
 */
public class SelfAbility extends Abilities {

    public SelfAbility(String name, String description, Effect effects, Requirement requirement, Effect cost) {

        super(name, description, effects, requirement, cost, 1);
    }
}
