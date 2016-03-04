package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Sneak extends Occupation{

    //constructor
    public Sneak()
    {
        super("Sneak", "Specializes in ranged weapons,evading detection, and removing traps ", new int [] {5,5,7,5,5,0,5});
    }

    //operations
    public void modifyOccupationalSkills(SkillsEnum s, int value){
        //checking for sneak related skill change
        if(s.equals(SkillsEnum.PICKPOCK) || s.equals(SkillsEnum.DRTRAP) || s.equals(SkillsEnum.CREEP) || s.equals(SkillsEnum.RANGEWEAP) ){
            Map<SkillsEnum, Integer> m = this.getOccupationalSkills(); //attaining the occupational skills
            m.put(s, value); //changing the occupational skills
            this.setOccupationalSkills(m); //setting the occupational skills
        }
        else {
            //do nothing
        }
    }
}

