package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Requirement;

/**
 * Created by mazumderm on 3/12/2016.
 */
public class RemoveTrap extends ExecutableAbility{

    //constructor
    public RemoveTrap(String name, String description, Effect effects, Requirement requirement, Effect cost, int skillLevel) {
        super(name, description, effects, requirement, cost, skillLevel);
    }

    //operations
    public int getSkillLevel(){
        return this.getSkillLevel();
    }
    public void execute(MobileObject targeted){

    }
}