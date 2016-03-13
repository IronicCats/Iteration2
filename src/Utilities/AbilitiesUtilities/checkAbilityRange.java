package Utilities.AbilitiesUtilities;

import Model.Abilities.Abilities;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/12/2016.
 */
public class checkAbilityRange {

    public static Abilities check(ArrayList<Abilities> abilities){

        Abilities maxAbility = abilities.get(0);
        int range = abilities.get(0).getRange();

        for(Abilities ability: abilities){
            if(ability.getRange() > range){
                range = ability.getRange();
                maxAbility = ability;
            }
        }
        return maxAbility;
    }

}
