package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Abilities.DirectAbility;
import Model.Abilities.WeaponAbility;
import Model.Effects.Effect;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import Utilities.ItemUtilities.ItemFactory;

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
        //basic attack of just hitting another entity
        /**
         // basic attack for Summoner is a weapon ability involving the whacking of an enemy with a staff
        setBasicAttack(new WeaponAbility(
                             "Attack",
                             "Basic attack of smasher",
                              new Effect(new StatStructure(StatsEnum.LIFE, getStats().getStrength())),
                              ItemFactory.makeItem()  //should be staff weapon
                              new Requirement(0),
                              new Effect(new StatStructure(StatsEnum.MANA, 0))
        ));
         **/
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
        alert();
    }

    public int getOccupationalSkillsValue(SkillsEnum s){
        if(s.equals(SkillsEnum.ENCHANT) || s.equals(SkillsEnum.BOON) || s.equals(SkillsEnum.BANE) || s.equals(SkillsEnum.BANE)){
            return getOccupationalSkills().get(s);
        }
        else {
            System.out.println("This skill is not available to you");
            return 0;
        }
    }
}
