package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Summoner extends Occupation {

    //constructor
    public Summoner()
    {
        super("Summoner", "Specializes in spell casting", new int [] {5,5,5,7,5,0,5});
    }

    //operations
    public  void modifyOccupationalSkills(SkillsEnum s, int value){
        //checking for summoner related skill change
        if(s.equals(SkillsEnum.ENCHANT) || s.equals(SkillsEnum.BOON) || s.equals(SkillsEnum.BANE) || s.equals(SkillsEnum.BANE)){
            Map<SkillsEnum, Integer> m  = this.getOccupationalSkills(); //attaining the occupational skills
            m.put(s, value); //changing the occupational skills
            this.setOccupationalSkills(m); //setting the occupational skills
        }
        else {
            //do nothing
        }
    }
}
