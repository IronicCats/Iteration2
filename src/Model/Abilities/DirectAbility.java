package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.MobileObject;
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

    public DirectAbility(String name, String description, Effect effects, Requirement requirement, Effect cost) {

        super(name, description, effects, requirement, cost, 1);
    }


}
