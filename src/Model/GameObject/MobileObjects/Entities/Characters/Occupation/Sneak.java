package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Abilities.CreepAbility;
import Model.Abilities.DirectAbility;
import Model.Abilities.PickPocket;
import Model.Abilities.RemoveTrap;
import Model.Effects.Effect;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;

import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Sneak extends Occupation {

    //constructor
    public Sneak() {
        super("Sneak", "Specializes in ranged weapons,evading detection, and removing traps ", new int[]{5, 5, 7, 5, 5, 0, 12, 12});
        //set basic occupational skills
        //set occupational skills
        modifyOccupationalSkills(SkillsEnum.PICKPOCK, 0);
        modifyOccupationalSkills(SkillsEnum.DRTRAP, 0);
        modifyOccupationalSkills(SkillsEnum.CREEP, 0);
        modifyOccupationalSkills(SkillsEnum.RANGEWEAP, 0);


        setBasicAttack(new DirectAbility(
                //basic attack for sneak is a direct ability that involves hitting another entity
                //basic attack is calculated based on strength
                //distinct from Sneak in that the Smasher will have different stats involved in the calculation of the effect and have the brawl skill
                "Attack",
                "Basic attack of sneak",
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * (getStats().getOffensiveRating()))),
                new Requirement(0),
                0,
                2
        ));

        //computes occupational abilities
        getOccupationalAbilities().add(new PickPocket("Pick Pocket",
                "Helps you steal another entity's money",
                new Effect(new StatStructure(StatsEnum.LIFE, 0)),
                new Requirement(0),
                0,
                getOccupationalSkillsValue(SkillsEnum.PICKPOCK)));

        getOccupationalAbilities().add(new RemoveTrap("Remove trap",
                "you can attempt to remove a trap",
                new Effect(new StatStructure(StatsEnum.LIFE, 0)),
                new Requirement(0),
                0,
                0,
                0,
                getOccupationalSkillsValue(SkillsEnum.PICKPOCK)));

        getOccupationalAbilities().add(new CreepAbility("Creep around",
                "you can sneak past HostilNPCs",
                null,
                new Requirement(0),0,0,0));

    }

    //operations
    public void modifyOccupationalSkills(SkillsEnum s, int value) {
        //checking for sneak related skill change
        if (s == SkillsEnum.PICKPOCK || s == SkillsEnum.DRTRAP || s == SkillsEnum.CREEP || s == SkillsEnum.RANGEWEAP) {
            Map<SkillsEnum, Integer> m = this.getOccupationalSkills(); //attaining the occupational skills
            m.put(s, value); //changing the occupational skills
            this.setOccupationalSkills(m); //setting the occupational skills
        } else {
            //do nothing
        }
        alert();
    }

    public int getOccupationalSkillsValue(SkillsEnum s) {
        if (s == SkillsEnum.PICKPOCK || s == SkillsEnum.DRTRAP || s == SkillsEnum.CREEP || s == SkillsEnum.RANGEWEAP) {
            return getOccupationalSkills().get(s);
        } else {
            System.out.println("This skill is not available to you");
            return 0;
        }
    }

    public void recomputeOccupationalAbilities() {
        //basic attack
       getBasicAttack().setEffects( new Effect(new StatStructure(StatsEnum.LIFE, getStats().getStrength() + getStats().getOffensiveRating())));
        getAbilityAt(0).setSkillLevel(getOccupationalSkillsValue(SkillsEnum.PICKPOCK));
        getAbilityAt(1).setSkillLevel(getOccupationalSkillsValue(SkillsEnum.DRTRAP));
    }

}


