package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Abilities.Abilities;
import Model.Abilities.DirectAbility;
import Model.Abilities.SelfAbility;
import Model.Effects.Effect;
import Model.Inventory.EquipmentTypeEnum;
import Model.Requirement;
import Model.Stats.CharacterStats;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import Utilities.Observer;
import Utilities.Subject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public abstract class Occupation implements Subject, Observer {
    private String name;
    private String description;
    private StatStructure initialStats;
    private CharacterStats playerStats;
    private Map<SkillsEnum, Integer> basicSkills;
    private Map<SkillsEnum, Integer> occupationalSkills;
    private ArrayList<Observer> observers;
    private ArrayList<Abilities> occupationalAbilities;
    private Map<SkillsEnum, Abilities> basicSkillAbilities;
    private Abilities basicAttack;
    private int pastLevel;
    private int unassignedSkillPoints;
    private EquipmentTypeEnum weaponType;

    //constructor
    public Occupation(String name, String description, int[] val) {
        observers = new ArrayList<>();
        this.name = name;
        this.description = description;
        StatsEnum[] stats = new StatsEnum[]{StatsEnum.LIVES_LEFT, StatsEnum.STRENGTH, StatsEnum.AGILITY,
                StatsEnum.INTELLECT, StatsEnum.HARDINESS, StatsEnum.EXPERIENCE, StatsEnum.MOVEMENT};
        initialStats = new StatStructure(stats, val);
        playerStats = new CharacterStats(initialStats);
        playerStats.addObserver(this);
        pastLevel = playerStats.getLevel();
        unassignedSkillPoints = 0;
        this.basicSkills = new EnumMap(SkillsEnum.class);
        this.occupationalSkills = new EnumMap(SkillsEnum.class);
        occupationalAbilities = new ArrayList<>();
        basicSkillAbilities = new EnumMap(SkillsEnum.class);

        //basic skills
        basicSkills.put(SkillsEnum.BINDWOUNDS, 1);
        basicSkills.put(SkillsEnum.BARGAIN, 1);
        basicSkills.put(SkillsEnum.OBSERVATION, 1);

        //set basic abilities
        basicSkillAbilities.put(SkillsEnum.BINDWOUNDS, new SelfAbility(
                "Bind Wound",
                "Uses a bind wound skill",
                new Effect(new StatStructure(StatsEnum.LIFE, this.getBasicSkillValue(SkillsEnum.BINDWOUNDS) + 1)),
                new Requirement(0),
                new Effect(new StatStructure(StatsEnum.MANA, 0)),
                4));

    }

    //accessor methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CharacterStats getStats() {
        return playerStats;
    }

    public int getUnassignedSkillPoints() { return unassignedSkillPoints; }

    public Map<SkillsEnum, Integer> getBasicSkills() {
        return basicSkills;
    }

    public Map<SkillsEnum, Integer> getOccupationalSkills() {
        return occupationalSkills;
    }

    public void setOccupationalSkills(Map<SkillsEnum, Integer> m) {
        this.occupationalSkills = m;
    }

    public void setBasicSkills(Map<SkillsEnum, Integer> m) {
        this.basicSkills = m;
        alert();
    }

    //skill related methods

    public void incrementSkill(SkillsEnum skill) {
        if(unassignedSkillPoints > 0) {
            switch (skill) {
                case BINDWOUNDS:
                case BARGAIN:
                case OBSERVATION:
                    modifyBasicSkills(skill, getBasicSkillValue(skill) + 1);
                    break;
                case ONEHANDWEAP:
                case TWOHANDWEAP:
                case BRAWL:
                case ENCHANT:
                case BOON:
                case BANE:
                case STAFF:
                case PICKPOCK:
                case DRTRAP:
                case CREEP:
                case RANGEWEAP:
                    modifyOccupationalSkills(skill, getOccupationalSkillsValue(skill) + 1);
                    break;
                default:
                    System.out.println("What skill are you even trying to modify?");
                    break;
            }
        }
    } // end incrementSkill

    public void modifyBasicSkills(SkillsEnum s, int value) {
        if (s == SkillsEnum.BINDWOUNDS || s == SkillsEnum.BARGAIN || s == SkillsEnum.OBSERVATION) {
            basicSkills.put(s, value);
            unassignedSkillPoints--;
            System.out.println("Skill point assigned, now " + unassignedSkillPoints);
        }
        alert();
    }

    public int getBasicSkillValue(SkillsEnum s) {
        if (s.equals(s == SkillsEnum.BINDWOUNDS || s == SkillsEnum.BARGAIN || s == SkillsEnum.OBSERVATION)) {
            return basicSkills.get(s);
        } else {
            return 0;
        }
    }

    public void setBasicAttack(Abilities basicAttack) {
        this.basicAttack = basicAttack;
        alert();
    }

    public Abilities getBasicAttack() {
        return basicAttack;
    }

    public ArrayList<Abilities> getOccupationalAbilities() {
        return occupationalAbilities;
    }

    public void setOccupationalAbilities(Abilities a){getOccupationalAbilities().add(a);}

    public Abilities getAbilityAt(int i){
        return getOccupationalAbilities().get(i);
    }

    public Abilities getBindWounds(){
        return basicSkillAbilities.get(SkillsEnum.BINDWOUNDS);
    }
    //different occupational skills for each occupation
    public abstract void modifyOccupationalSkills(SkillsEnum s, int value);

    public abstract int getOccupationalSkillsValue(SkillsEnum s);

    public abstract void recomputeOccupationalAbilities();

    public void setWeaponType(EquipmentTypeEnum e){
        if(this instanceof Smasher){
            if(e == null || e == EquipmentTypeEnum.DOUBLE_HANDED ){
                getBasicAttack().setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.BRAWL) + getStats().getOffensiveRating())/4))));
            }
            else if(e == EquipmentTypeEnum.ONE_HANDED){
                getBasicAttack().setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.ONEHANDWEAP) + getStats().getOffensiveRating())/3))));
            }
            else if(e == EquipmentTypeEnum.TWO_HANDED){
                getBasicAttack().setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.TWOHANDWEAP) + getStats().getOffensiveRating())/2))));
            }
        }
        else if(this instanceof  Summoner){
            if(e == EquipmentTypeEnum.TWO_HANDED){
                getBasicAttack().setEffects(new Effect(new StatStructure(StatsEnum.LIFE, -1 * ((int)(this.getOccupationalSkillsValue(SkillsEnum.STAFF) + getStats().getOffensiveRating())/2))));
            }
        }

    }

    /*
   implement subject methods
    */
    @Override
    public void addObserver(Utilities.Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Utilities.Observer o) {
        observers.remove(o);
    }

    @Override
    public void alert() {
        for (Observer observer : observers) {
            observer.update();
        }
    } // end alert

    /*
    implement observer methods
     */
    @Override
    public void update() {
        //leveling up triggers adding a counter to some skill
        if (pastLevel != playerStats.getLevel()) {
            pastLevel = playerStats.getLevel();
            unassignedSkillPoints++;
            System.out.println("Skill point assigned, now " + unassignedSkillPoints);
            /**
             //change the skills
             SkillsEnum s;
             modifyOccupationalSkills(s, getOccupationalSkillsValue(s) + 1);
             **/
        }

        //recompute abilities each time a stat changes
        //recomputes basic abilities
        basicSkillAbilities.get(SkillsEnum.BINDWOUNDS).setEffects(new Effect(new StatStructure(StatsEnum.LIFE, this.getBasicSkillValue(SkillsEnum.BINDWOUNDS) + 1)));

        //recomputes occupational abilities
        recomputeOccupationalAbilities();

    } // end update

    @Override
    public void remove() {

    } // end remove

}
