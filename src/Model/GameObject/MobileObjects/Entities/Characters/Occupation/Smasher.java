package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Abilities.DirectAbility;
import Model.Effects.Effect;
import Model.Inventory.EquipmentTypeEnum;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import Utilities.ItemUtilities.ItemsEnum;

import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Smasher extends Occupation {

    EquipmentTypeEnum equipmentType;
    //constructor
    public Smasher() {

        super("Smasher", "Specializes in hand-to-hand combat", new int[]{5, 7, 5, 5, 5, 0, 8, 12});

        //set occupational skills
        modifyOccupationalSkills(SkillsEnum.ONEHANDWEAP, 0);
        modifyOccupationalSkills(SkillsEnum.TWOHANDWEAP, 0);
        modifyOccupationalSkills(SkillsEnum.BRAWL, 0);

        //basic attack for smasher is a direct ability that involves hitting another entity
        //basic attack is calculated based on strength and brawl skill level
        // distinct from Sneak in that the Smasher will have different stats involved in the calculation of the effect and have the brawl skill
        if(equipmentType == null){
            setBasicAttack(new DirectAbility(
                    "Basic Fist Attack",
                    "Attack using just the fists",
                    new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.BRAWL) + getStats().getOffensiveRating())/3))),
                    new Requirement(0),
                    new Effect(new StatStructure(StatsEnum.MANA, 0)),
                    2
            ));
        }
        else if(equipmentType == EquipmentTypeEnum.ONE_HANDED){
            setBasicAttack(new DirectAbility(
                    "One Handed Weapon Attack",
                    "Attack using whatever one-handed weapon is equipped",
                    new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))),
                    new Requirement(0),
                    new Effect(new StatStructure(StatsEnum.MANA, 0)),
                    2
            ));
        }
        else if(equipmentType == EquipmentTypeEnum.TWO_HANDED){
            setBasicAttack(new DirectAbility(
                    "Attack",
                    "Attack using whatever two-handed weapon is equipped",
                    new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/3))),
                    new Requirement(0),
                    new Effect(new StatStructure(StatsEnum.MANA, 0)),
                    2
            ));
        }


    }

    //operations
    public void modifyOccupationalSkills(SkillsEnum s, int value) {
        //checking for smasher related skill change
        if (s == SkillsEnum.ONEHANDWEAP || s == SkillsEnum.TWOHANDWEAP || s == SkillsEnum.BRAWL) {
            Map<SkillsEnum, Integer> m = this.getOccupationalSkills(); //attaining the occupational skills
            m.put(s, value); //changing the occupational skills
            this.setOccupationalSkills(m); //setting the occupational skills
        } else {
            //do nothing
        }
        alert();
    }

    public int getOccupationalSkillsValue(SkillsEnum s) {
        if (s == SkillsEnum.ONEHANDWEAP || s == SkillsEnum.TWOHANDWEAP || s == SkillsEnum.BRAWL) {
            return getOccupationalSkills().get(s);
        } else {
            System.out.println("This skill is not available to you");
            return 0;
        }
    }

    public void setWeaponType(EquipmentTypeEnum e){
        this.equipmentType = e;
    }

    public void recomputeOccupationalAbilities() {
        //basic attack
        if(equipmentType == null){
           getBasicAttack().setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.BRAWL) + getStats().getOffensiveRating())/3))));
        }
        else if(equipmentType == EquipmentTypeEnum.ONE_HANDED){
           getBasicAttack().setEffects( new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))));
        }
        else if(equipmentType == EquipmentTypeEnum.TWO_HANDED){
           getBasicAttack().setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/3))));
        }
    }
}
