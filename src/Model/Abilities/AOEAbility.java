package Model.Abilities;

import Model.Effects.Effect;
import Model.Map.Tile;

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

    public void execute(Tile t){

    }
}
