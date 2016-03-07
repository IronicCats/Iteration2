package Model.Abilities;

import Model.Effects.Effect;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class AOEAbility extends Abilities{
    int degreeMovement;
    int range;

    public AOEAbility(int degreeMovement, int range, Effect [] effects){
        super(effects);
        this.degreeMovement = degreeMovement;
        this.range = range;
    }
}
