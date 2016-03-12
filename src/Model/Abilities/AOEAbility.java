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

    public void execute(MobileObject targeter, Location targeterLocation){
        /**
        Tile [] affectedTiles;
        Map map = targeter.getMap();
        if(degreeMovement == 60){
            switch(targeterLocation.getDir()){
                case 45:
                    break;
                case 90:
                    break;
                case 135:

                    break;
                case 225:

                    break;
                case 270:

                    break;
                case 315:

                    break;
                default:
                    break;
            }
        }
        else if(degreeMovement == 360){
            affectedTiles = Neighbors.neighbors(targeter.getTile(),map);
            for(int i = 0; i < affectedTiles.length; ++i){// for all tiles in affected range
                Tile t = affectedTiles[i];
                MobileObject m = t.getObject();   // get object from each tile
                ((Character)(targeter)).applyEffect(getEffects()); // apply ability affect
                }
            }
        }
         **/
    }


}
