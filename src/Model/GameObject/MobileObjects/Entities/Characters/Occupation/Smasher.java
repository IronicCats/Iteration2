
package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Abilities.DirectAbility;
import Model.Effects.Effect;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;

import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Smasher extends Occupation {

    //constructor
    public Smasher() {

        super("Smasher", "Specializes in hand-to-hand combat", new int[]{5, 7, 5, 5, 5, 0, 5});

        //basic attack for smasher is a direct ability that involves hitting another entity
        //basic attack is calculated based on strength and brawl skill level
        // distinct from Sneak in that the Smasher will have different stats involved in the calculation of the effect and have the brawl skill
        setBasicAttack(new DirectAbility(
                            "Attack",
                            "Basic attack of smasher",
                            new Effect(new StatStructure(StatsEnum.LIFE, getStats().getStrength() + this.getOccupationalSkillsValue(SkillsEnum.BRAWL))),
                            new Requirement(0),
                            new Effect(new StatStructure(StatsEnum.MANA, 0))
                        ));
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
        alert();
    }

    public int getOccupationalSkillsValue(SkillsEnum s){
        if(s.equals(SkillsEnum.ONEHANDWEAP) || s.equals(SkillsEnum.TWOHANDWEAP) || s.equals(SkillsEnum.BRAWL)){
                return getOccupationalSkills().get(s);
        }
        else {
            System.out.println("This skill is not available to you");
            return 0;
        }
    }
}
