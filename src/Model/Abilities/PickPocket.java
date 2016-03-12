package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Requirement;

/**
 * Created by mazumderm on 3/12/2016.
 */
public class PickPocket extends ExecutableAbility {

    //constructor
    public PickPocket(String name, String description, Effect effects, Requirement requirement, Effect cost, int skillLevel) {
        super(name, description, effects, requirement, cost, skillLevel, 1);
    }

    //operations

    public void execute(MobileObject targeter, MobileObject targeted){
        if(getSkillLevel() == 0){
            //can't take any object
        }
        else if(getSkillLevel() == 1){
            int moneyTaken = (int)(.25 * ((Character)targeted).getPack().getMoney());
        }
        else if(getSkillLevel() == 2){
            int moneyTaken = (int)(.5 * ((Character)targeted).getPack().getMoney());
        }
        else {
            int moneyTaken = ((Character)targeted).getPack().getMoney();
            ((Character)targeted).getPack().setMoney(0);
        }
    }
}
