package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Requirement;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;

/**
 * Created by mazumderm on 3/12/2016.
 */
public class PickPocket extends ExecutableAbility {

    //constructor
    public PickPocket(String name, String description, Effect effects, Requirement requirement, int cost, int skillLevel) {
        super(name, description, effects, requirement, cost, skillLevel, 1, 0);
    }

    //operations

    public void execute(MobileObject targeter, MobileObject targeted){
        if(getSkillLevel() == 0){
            //can't take any object
        }
        else if(getSkillLevel() == 1){
            int moneyTaken = (int)(.25 * ((Character)targeted).getMoney());
            ((Character)targeted).modifyMoney(moneyTaken * -1);
            ((Character)targeter).modifyMoney(moneyTaken);
            DisplayMessage.addMessage(new GameMessage("Money taken",  10));
        }
        else if(getSkillLevel() == 2){
            int moneyTaken = (int)(.5 * ((Character)targeted).getMoney());
            ((Character)targeted).modifyMoney(moneyTaken * -1);
            ((Character)targeter).modifyMoney(moneyTaken);
            DisplayMessage.addMessage(new GameMessage("Money taken",  10));
        }
        else {
            int moneyTaken = ((Character)targeted).getMoney();
            ((Character)targeted).setMoney(0);
            ((Character)targeter).modifyMoney(moneyTaken);
            DisplayMessage.addMessage(new GameMessage("Money taken",  10));
        }
    }
}
