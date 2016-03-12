package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Requirement;

/**
 * Created by mazumderm on 3/11/2016.
 */
public abstract class ExecutableAbility extends Abilities {

    private int skillLevel;

    //constructor
    public ExecutableAbility(String name, String description, Effect effects, Requirement requirement, Effect cost, int skilLevel, int range) {
        super(name, description, effects, requirement, cost, range);
        this.skillLevel = skilLevel;
    }

    //operations
    public abstract void execute(MobileObject targeted);
}
