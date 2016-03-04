
package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Smasher extends Occupation {

    //constructor
    public Smasher() {
        super("Smasher", "Specializes in hand-to-hand combat", new int[]{5, 7, 5, 5, 5, 0, 5});
    }

    //operations
    public void modifyOccupationalSkills(SkillsEnum s, int value){
        //checking for smasher related skill change
        if(s.equals(SkillsEnum.ONEHANDWEAP) || s.equals(SkillsEnum.TWOHANDWEAP) || s.equals(SkillsEnum.BRAWL)){
            Map<SkillsEnum, Integer> m = this.getOccupationalSkills(); //attaining the occupational skills
            m.put(s, value); //changing the occupational skills
            this.setOccupationalSkills(m); //setting the occupational skills
        }
        else {
            //do nothing
        }
    }
}
