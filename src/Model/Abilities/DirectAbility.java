package Model.Abilities;

import Model.Effects.Effect;
import Model.Location;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;

/**
 * Created by mazumderm on 3/7/2016.
 */
public class DirectAbility extends Abilities {

    public DirectAbility(Effect effects, Requirement requirement, Effect cost) {

        super(effects, requirement, cost);
    }

    public void execute(Location targeterLocation){

    }
}
