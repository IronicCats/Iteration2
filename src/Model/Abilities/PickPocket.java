package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Requirement;

/**
 * Created by mazumderm on 3/12/2016.
 */
public class PickPocket extends ExecutableAbility {

    //constructor
    public PickPocket(String name, String description, Effect effects, Requirement requirement, Effect cost) {
        super(name, description, effects, requirement, cost);
    }

    //operations
    public void execute(MobileObject targeted, in){

    }
}
