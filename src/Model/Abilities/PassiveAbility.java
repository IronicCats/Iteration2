package Model.Abilities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Tile;

import java.util.ArrayList;

/**
 * Created by mazumderm on 3/14/2016.
 */
public abstract class PassiveAbility {
    int skillLevel;

    public PassiveAbility(int skillLevel ){
        this.skillLevel = skillLevel;
    }

    public abstract void checkSurroundingTiles(ArrayList<Tile> t);

    public int getSkillLevel(){
        return this.skillLevel;
    }

    public void setSkillLevel(int s){
        this.skillLevel = s;
    }
}
