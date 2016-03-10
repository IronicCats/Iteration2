package Model.Abilities;

import Model.Effects.Effect;
import Model.Location;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.StatStructure;

import java.util.ArrayList;

/**
 * Created by mazumderm on 3/6/2016.
 */
public class AOEAbility extends Abilities{
    int degreeMovement;

    public AOEAbility(int degreeMovement, int range, Effect effects, Requirement requirements, Effect cost){
        super(effects, requirements, cost);
        this.degreeMovement = degreeMovement;
    }

    public int getDegreeMovement(){
        return this.degreeMovement;
    }

    public void setDegreeMovement(int degreeMovement){
        this.degreeMovement = degreeMovement;
    }

    public void execute(Location targeterLocation){
        ArrayList<Tile> affectedTiles;
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
            for(int i = targeterLocation.getX() - 1; i < targeterLocation.getX() + 1; ++i){
                for(int j = targeterLocation.getY() - 1; j < targeterLocation.getY() + 1; ++j){

                }
            }
        }
    }
}
