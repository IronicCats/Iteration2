package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Inventory.Pack;
import Model.Requirement;

/**
 * Created by mazumderm on 3/13/2016.
 */
public class BargainAbility extends Abilities{

    int skillValue;

    public BargainAbility(String name, String description, Effect effects, Requirement requirement, Effect cost, int range, int skillValue){
        super(name,description,effects, requirement,cost, range);
        this.skillValue = skillValue;
    }

    public int getSkillValue(){
        return this.skillValue;
    }

    public void setSkillValue(int value){
        this.skillValue = value;
    }
    public void bargain(Item item){

    }
}
