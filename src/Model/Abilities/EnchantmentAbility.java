package Model.Abilities;

import Model.Effects.Effect;
import Model.Requirement;

/**
 * Created by Aidan on 3/14/2016.
 */
public class EnchantmentAbility extends Abilities{

    private int skillLevel;

    public EnchantmentAbility(String name, String description, Effect effects, Requirement requirement, Effect cost, int skilLevel, int range, int cooldown) {
        super(name, description, effects, requirement, cost, range, cooldown);

        this.skillLevel = skilLevel;
    }

    public void execute(){



    }


}
