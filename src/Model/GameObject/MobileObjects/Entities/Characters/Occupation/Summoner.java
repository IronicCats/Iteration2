package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Abilities.*;
import Model.Effects.Effect;
import Model.Effects.ModificationEnum;
import Model.GameObject.MobileObjects.Projectile;
import Model.Inventory.EquipmentTypeEnum;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.Stats;
import Model.Stats.StatsEnum;
import Utilities.ItemUtilities.ItemsEnum;

import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Summoner extends Occupation {

    //constructor
    public Summoner() {

        super("Summoner", "Specializes in spell casting", new int[]{5, 5, 5, 7, 5, 0, 10, 12});

        //set occupational skills
        modifyOccupationalSkills(SkillsEnum.ENCHANT, 0);
        modifyOccupationalSkills(SkillsEnum.BOON, 0);
        modifyOccupationalSkills(SkillsEnum.BANE, 1);
        modifyOccupationalSkills(SkillsEnum.STAFF, 0);


        //basic attack of just hitting another entity
        setBasicAttack(new DirectAbility(
                //basic attack for summoner is hitting the opposing person with a staff
                //basic attack is calculated based on strength
                "Slap",
                "whacks the enemy ",
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * (getStats().getOffensiveRating()))),
                new Requirement(0),
                0,
                2
        ));

        //enchantments
        //boon
        setOccupationalAbilities(new SelfAbility("Superheal",
                "A better version of binding wounds",
                new Effect(new StatStructure(StatsEnum.LIFE, getOccupationalSkillsValue(SkillsEnum.BOON) + 2)),
                new Requirement(0),
                -4,
                5
        ));
        setOccupationalAbilities(new SelfAbility("Damage Bonus",
                "Offensive bonus for 5 seconds",
                new Effect(new StatStructure(StatsEnum.OFFENSIVE_RATING, getOccupationalSkillsValue(SkillsEnum.BOON) + 3 * 100), ModificationEnum.PERCENT, 5000),
                new Requirement(0),
                -7,
                7
        ));
        setOccupationalAbilities(new SelfAbility("Speed Bonus",
                "Speed Bonus for 7 seconds",
                new Effect(new StatStructure(StatsEnum.MOVEMENT, getOccupationalSkillsValue(SkillsEnum.BOON) + 5 * 100), ModificationEnum.PERCENT, 7000),
                new Requirement(0),
                -11,
                5
        ));
        //bane
        setOccupationalAbilities(new ProjectileAbility("Hairball",
                "Fling hairballs at enemies",
                new Stats(20),
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * (getOccupationalSkillsValue(SkillsEnum.BANE)))),
                new Requirement(0),
                -4,
                3
        ));
        setOccupationalAbilities(new AOEAbility("Water Sprinkler",
                "Fling water at enemies",
                60,
                1,
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * (getOccupationalSkillsValue(SkillsEnum.BANE) + 2))),
                new Requirement(0),
                -7,
                5
        ));

        setOccupationalAbilities(new EnchantmentAbility("Make sleep",
                "Puts hostileNPCs to sleep",
                null, new Requirement(0),
                5, 1, 2, 3));

    }

        /*
        setOccupationalAbilities(new AOEAbility("Circle of Flames",
                "Everyone within a two tile radius is set on fire",
                360,
                1,
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * (this.getOccupationalSkillsValue(SkillsEnum.BANE)) + 4))),
                new Requirement(0),
                -11,
                7
        ));
        */



        //operations

    public void modifyOccupationalSkills(SkillsEnum s, int value) {
        //checking for summoner related skill change
        if (s == SkillsEnum.ENCHANT || s == SkillsEnum.BOON || s == SkillsEnum.BANE || s == SkillsEnum.STAFF) {
            Map<SkillsEnum, Integer> m = this.getOccupationalSkills(); //attaining the occupational skills
            m.put(s, value); //changing the occupational skills
            this.setOccupationalSkills(m); //setting the occupational skills
        } else {
            //do nothing
        }
        alert();
    }

    public int getOccupationalSkillsValue(SkillsEnum s) {
        if (s == SkillsEnum.ENCHANT || s == SkillsEnum.BOON || s == SkillsEnum.BANE || s == SkillsEnum.STAFF) {
            return getOccupationalSkills().get(s);
        } else {
            System.out.println("This skill is not available to you");
            return 0;
        }
    }


    public void recomputeOccupationalAbilities() {
        getBasicAttack().setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * (getStats().getOffensiveRating()))));
        getAbilityAt(0).setEffects( new Effect(new StatStructure(StatsEnum.LIFE, getOccupationalSkillsValue(SkillsEnum.BOON) + 2)));
        getAbilityAt(1).setEffects( new Effect(new StatStructure(StatsEnum.OFFENSIVE_RATING, getOccupationalSkillsValue(SkillsEnum.BOON) + 3 * 100), ModificationEnum.PERCENT, 5000));
        getAbilityAt(2).setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * (getOccupationalSkillsValue(SkillsEnum.BANE)))));
        getAbilityAt(3).setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * (getOccupationalSkillsValue(SkillsEnum.BANE)))));
        getAbilityAt(4).setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * (getOccupationalSkillsValue(SkillsEnum.BANE) + 2))));


    }
}



