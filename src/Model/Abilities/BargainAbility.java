package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Inventory.Pack;
import Model.Requirement;

import java.util.Random;

/**
 * Created by mazumderm on 3/13/2016.
 */
public class BargainAbility extends Abilities{

    int skillValue;

    public BargainAbility(String name, String description, Effect effects, Requirement requirement, Effect cost,  int skillValue){
        super(name,description,effects, requirement,cost, 1);
        this.skillValue = skillValue;
    }

    public int getSkillValue(){
        return this.skillValue;
    }

    public void setSkillValue(int value){
        this.skillValue = value;
    }
    public int bargain(int itemValue, boolean isSelling) {
        if (isSelling) {
            switch (skillValue) {
                case 0:
                    System.out.println("Price stayed the same");
                    return itemValue;
                case 1:
                    System.out.println("You got a bit more money");
                    return (int) (itemValue * 1.3);
                case 2:
                    System.out.println("You got a lot more money");
                    return (int) (itemValue * 1.5);
                case 3:
                    System.out.println("You just ripped that poor AI off");
                    return (int) (itemValue * 1.7);
            }
        } else {
            switch (skillValue) {
                case 0:
                    System.out.println("Price stayed the same");
                    return itemValue;
                case 1:
                    System.out.println("You got a discount");
                    return (int) (itemValue * .7);
                case 2:
                    System.out.println("You got a HUGE discount");
                    return (int) (itemValue * .5);
                case 3:
                    System.out.println("This was basically free");
                    return (int) (itemValue * .3);
            }
        }
        return 0;
    }
}
