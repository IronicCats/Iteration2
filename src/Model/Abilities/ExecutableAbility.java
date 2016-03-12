package Model.Abilities;

import Model.Effects.Effect;
import Model.Requirement;

/**
 * Created by mazumderm on 3/11/2016.
 */
public abstract class ExecutableAbility extends Abilities {

    //constructor
    public ExecutableAbility(String name, String description, Effect effects, Requirement requirement, Effect cost) {
        super(name, description, effects, requirement, cost);
    }

    //operations
    public abstract void execute();
}
