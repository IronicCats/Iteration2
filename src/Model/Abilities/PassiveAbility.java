package Model.Abilities;

import Model.GameObject.MobileObjects.MobileObject;

/**
 * Created by mazumderm on 3/14/2016.
 */
public abstract class PassiveAbility {
    int skillLevel;

    public PassiveAbility(int skillLevel ){
        this.skillLevel = skillLevel;
    }

    public abstract void checkSurroundingTiles(MobileObject o);

    public int getSkillLevel(){
        return this.skillLevel;
    }

    public void setSkillLevel(int s){
        this.skillLevel = s;
    }
}
