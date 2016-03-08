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
    int range;

    public AOEAbility(int degreeMovement, int range, Effect [] effects, Requirement requirements, StatStructure cost){
        super(effects, requirements, cost);
        this.degreeMovement = degreeMovement;
        this.range = range;
    }

    public int getDegreeMovement(){
        return this.degreeMovement;
    }

    public void setDegreeMovement(int degreeMovement){
        this.degreeMovement = degreeMovement;
    }

    public int getRange(){
        return this.range;
    }

    public void setRange(int range){
        this.range = range;
    }

    public void execute(Location location){
        ArrayList<Location> affectedAreas;
        if(degreeMovement == 60){
            switch(location.getDir()){
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

        }
    }
}
