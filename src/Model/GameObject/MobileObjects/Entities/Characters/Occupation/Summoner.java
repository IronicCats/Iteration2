package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Summoner extends Occupation {

    //constructor
    public Summoner()
    {

        super("Summoner", "Specializes in spell casting", new int [] {5,5,5,7,5,0,5});

        //set occupational skills
        modifyOccupationalSkills(SkillsEnum.ENCHANT, 0);
        modifyOccupationalSkills(SkillsEnum.BOON, 0);
        modifyOccupationalSkills(SkillsEnum.BANE, 0);
        modifyOccupationalSkills(SkillsEnum.STAFF, 0);


        //basic attack of just hitting another entity


    }

    //operations
    public  void modifyOccupationalSkills(SkillsEnum s, int value){
        //checking for summoner related skill change
        if(s == SkillsEnum.ENCHANT || s == SkillsEnum.BOON || s == SkillsEnum.BANE || s == SkillsEnum.STAFF){
            Map<SkillsEnum, Integer> m  = this.getOccupationalSkills(); //attaining the occupational skills
            m.put(s, value); //changing the occupational skills
            this.setOccupationalSkills(m); //setting the occupational skills
        }
        else {
            //do nothing
        }
        alert();
    }

    public int getOccupationalSkillsValue(SkillsEnum s){
        if(s == SkillsEnum.ENCHANT || s == SkillsEnum.BOON || s == SkillsEnum.BANE || s == SkillsEnum.STAFF){
            return getOccupationalSkills().get(s);
        }
        else {
            System.out.println("This skill is not available to you");
            return 0;
        }
    }

    public void recomputeOccupationalAbilities(){

    }
}
