package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Requirement;

/**
 * Created by mazumderm on 3/12/2016.
 */
public class PickPocket extends ExecutableAbility {

    //constructor
    public PickPocket(String name, String description, Effect effects, Requirement requirement, Effect cost, int skillLevel) {
        super(name, description, effects, requirement, cost, skillLevel);
    }

    //operations
    public int getSkillLevel(){
        return this.getSkillLevel();
    }
    public void execute(MobileObject targeted){
        if(getSkillLevel() == 0){
            //can't get anything
        }
        else if(getSkillLevel() == 1){

        }
        else if(getSkillLevel() == 2){

        }
        else {

        }
    }
}
