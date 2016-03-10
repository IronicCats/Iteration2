package Model.Abilities;

import Model.Effects.Effect;
import Model.Location;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;

/**
 * Created by mazumderm on 3/7/2016.
 */

/**
 * The purpose of thic class is to provide the skeleton for basic direct abilities. 
 */
public class DirectAbility extends Abilities {

    public DirectAbility(Effect effects, Requirement requirement, Effect cost, int range) {

        super(effects, requirement, cost);
    }

    public void execute(Location targeterLocation){
        //get the tile that the targeter can target

        //for all entities on the tile apply the effect
    }
}
