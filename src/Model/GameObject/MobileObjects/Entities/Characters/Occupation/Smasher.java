package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Abilities.DirectAbility;
import Model.Effects.Effect;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import Utilities.ItemUtilities.ItemsEnum;

import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Smasher extends Occupation {

    //constructor
    public Smasher() {

        super("Smasher", "Specializes in hand-to-hand combat", new int[]{5, 7, 5, 5, 5, 0, 5});

        //set occupational skills
        modifyOccupationalSkills(SkillsEnum.ONEHANDWEAP, 0);
        modifyOccupationalSkills(SkillsEnum.TWOHANDWEAP, 0);
        modifyOccupationalSkills(SkillsEnum.BRAWL, 0);


        //basic attack for smasher is a direct ability that involves hitting another entity
        //basic attack is calculated based on strength and brawl skill level
        // distinct from Sneak in that the Smasher will have different stats involved in the calculation of the effect and have the brawl skill
        setBasicAttack(new DirectAbility(
                            "Attack",
                            "Basic attack of smasher",
                            new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.BRAWL) + getStats().getOffensiveRating())/3))),
                            new Requirement(0),
                            new Effect(new StatStructure(StatsEnum.MANA, 0))
                        ));

        //initialize all available occupational abilities
        getOccupationalAbilities().add(new DirectAbility(
                                            "Stick Sword",
                                            "Attack the person in front of you with a stick sword",
                                            new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))),
                                            new Requirement(ItemsEnum.STICK_SWORD),
                                            new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                                            "Stick Greatsword",
                                            "Attack the person in front of you with a stick greatsword",
                                            new Effect(new StatStructure(StatsEnum.LIFE,-1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/3))),
                                            new Requirement(ItemsEnum.STICK_GREATSWORD),
                                            new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                                           "Swordfish Dagger",
                                           "Attack the person in front of you with a swordfish dagger",
                                            new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))),
                                            new Requirement(ItemsEnum.SWORDFISH_DAGGER),
                                            new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                                           "Swordfish Lance",
                                           "Attack the person in front of you with a swordfish lance",
                                            new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/3))),
                                            new Requirement(ItemsEnum.SWORDFISH_LANCE),
                                            new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                                          "Puffer Fish Mace",
                                          "Attack the person in front of you with a pufferfish mace",
                                           new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))),
                                           new Requirement(ItemsEnum.PUFFER_FISH_MACE),
                                           new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                                           "Puffer Fish Flail",
                                           "Attack the person in front of you with a pufferfish flail",
                                            new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/3))),
                                            new Requirement(ItemsEnum.PUFFER_FISH_FLAIL),
                                            new Effect(new StatStructure(StatsEnum.MANA, 0))));

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

    public void recomputeOccupationalAbilities() {
        //basic attack
        setBasicAttack(new DirectAbility(
                "Attack",
                "Basic attack of smasher",
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.BRAWL) + getStats().getOffensiveRating())/3))),
                new Requirement(0),
                new Effect(new StatStructure(StatsEnum.MANA, 0))
        ));
        //initialize all available occupational abilities
        getOccupationalAbilities().clear();
        getOccupationalAbilities().add(new DirectAbility(
                "Stick Sword",
                "Attack the person in front of you with a stick sword",
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))),
                new Requirement(ItemsEnum.STICK_SWORD),
                new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                "Stick Greatsword",
                "Attack the person in front of you with a stick greatsword",
                new Effect(new StatStructure(StatsEnum.LIFE,-1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/3))),
                new Requirement(ItemsEnum.STICK_GREATSWORD),
                new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                "Swordfish Dagger",
                "Attack the person in front of you with a swordfish dagger",
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))),
                new Requirement(ItemsEnum.SWORDFISH_DAGGER),
                new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                "Swordfish Lance",
                "Attack the person in front of you with a swordfish lance",
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/3))),
                new Requirement(ItemsEnum.SWORDFISH_LANCE),
                new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                "Puffer Fish Mace",
                "Attack the person in front of you with a pufferfish mace",
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))),
                new Requirement(ItemsEnum.PUFFER_FISH_MACE),
                new Effect(new StatStructure(StatsEnum.MANA, 0))));
        getOccupationalAbilities().add(new DirectAbility(
                "Puffer Fish Flail",
                "Attack the person in front of you with a pufferfish flail",
                new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/3))),
                new Requirement(ItemsEnum.PUFFER_FISH_FLAIL),
                new Effect(new StatStructure(StatsEnum.MANA, 0))));
    }
}
