package Model.Abilities;

import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;
import Utilities.MapUtilities.Neighbors;

import java.util.ArrayList;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class AOEAbility extends Abilities{
    int degreeMovement;
    int radius;

    public AOEAbility(String name, String description, int degreeMovement, int range, Effect effects, Requirement requirements, Effect cost){
        super(name, description, effects, requirements, cost);
        this.degreeMovement = degreeMovement;
    }

    public int getDegreeMovement(){
        return this.degreeMovement;
    }

    public void setDegreeMovement(int degreeMovement){
        this.degreeMovement = degreeMovement;
    }

}
