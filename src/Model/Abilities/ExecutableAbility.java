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
    public ExecutableAbility(String name, String description, Effect effects, Requirement requirement, int cost, int skilLevel, int range, int cooldown) {
        super(name, description, effects, requirement, cost, range, cooldown);
        this.skillLevel = skilLevel;
    }

    //operations
    public int getSkillLevel(){
        return this.getSkillLevel();
    }

    public abstract void execute(MobileObject targeter, MobileObject targeted);
}
