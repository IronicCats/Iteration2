package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Abilities.Abilities;
import Model.Abilities.DirectAbility;
import Model.Effects.Effect;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.Requirement;
import Model.Stats.CharacterStats;
import Model.Stats.StatStructure;
import Model.Stats.Stats;
import Model.Stats.StatsEnum;

import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import Utilities.Observer;
import Utilities.Subject;

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
    private ArrayList<Abilities> basicSkillAbilities;
    private Abilities basicAttack;
    private int pastLevel;

    //constructor
    public Occupation(String name, String description, int[] val)
    {
       observers = new ArrayList<>();
        this.name = name;
        this.description = description;
        StatsEnum[] stats = new StatsEnum[]{StatsEnum.LIVES_LEFT, StatsEnum.STRENGTH,StatsEnum.AGILITY,
                StatsEnum.INTELLECT, StatsEnum.HARDINESS, StatsEnum.EXPERIENCE, StatsEnum.MOVEMENT};
        initialStats = new StatStructure(stats, val);
        playerStats = new CharacterStats(initialStats);
        playerStats.addObserver(this);
        pastLevel = playerStats.getLevel();
        this.basicSkills = new EnumMap(SkillsEnum.class);
        this.occupationalSkills = new EnumMap(SkillsEnum.class);
        occupationalAbilities = new ArrayList<>();
        basicSkillAbilities = new ArrayList<>();

        //basic skills
        basicSkills.put(SkillsEnum.BINDWOUNDS, 0);
        basicSkills.put(SkillsEnum.BARGAIN, 0);
        basicSkills.put(SkillsEnum.OBSERVATION, 0);

        //set basic abilities
        basicSkillAbilities.add(new DirectAbility(
                                "Bind Wound",
                                 "Uses a bind wound skill",
                                  new Effect(new StatStructure(StatsEnum.LIFE, this.getBasicSkillValue(SkillsEnum.BINDWOUNDS) + 1)),
                                  new Requirement(0),
                                  new Effect(new StatStructure(StatsEnum.MANA, 0))));

    }

    //accessor methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CharacterStats getStats() { return playerStats; }

    public Map<SkillsEnum, Integer> getBasicSkills(){
        return basicSkills;
    }

    public Map<SkillsEnum, Integer> getOccupationalSkills(){
        return occupationalSkills;
    }

    public void setOccupationalSkills(Map<SkillsEnum, Integer> m){
        this.occupationalSkills = m;
    }

    public void setBasicSkills(Map<SkillsEnum, Integer> m){
        this.basicSkills = m;
        alert();
    }

    //skill related methods

    public void modifyBasicSkills(SkillsEnum s, int value){
        if(s == SkillsEnum.BINDWOUNDS || s == SkillsEnum.BARGAIN || s == SkillsEnum.OBSERVATION){
            basicSkills.put(s, value);
        }
        else {
            //do nothing
        }
        alert();
    }

    public int getBasicSkillValue(SkillsEnum s){
        if(s.equals(s == SkillsEnum.BINDWOUNDS || s == SkillsEnum.BARGAIN || s == SkillsEnum.OBSERVATION)){
            return basicSkills.get(s);
        }
        else{
            return 0;
        }
    }

    public void setBasicAttack(Abilities basicAttack){
        this.basicAttack = basicAttack;
        alert();
    }

    public Abilities getBasicAttack(){
        return basicAttack;
    }

    public ArrayList<Abilities> getOccupationalAbilities(){
        return this.occupationalAbilities;
    }


    //different occupational skills for each occupation
    public abstract void modifyOccupationalSkills(SkillsEnum s, int value);

    public abstract int getOccupationalSkillsValue(SkillsEnum s);

    public abstract void recomputeOccupationalAbilities();
    /*
   implement subject methods
    */
    @Override
    public void addObserver(Utilities.Observer o) { observers.add(o); }

    @Override
    public void removeObserver(Utilities.Observer o) { observers.remove(o); }

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
        if(pastLevel != playerStats.getLevel()){
            pastLevel = playerStats.getLevel();
            /**
            //change the skills
            SkillsEnum s;
            modifyOccupationalSkills(s, getOccupationalSkillsValue(s) + 1);
             **/
        }

        //recompute abilities each time a stat changes
        /**
         * recompute all abilites
         * differs with each occupation
         */
        //recomputes basic abilities
        basicSkillAbilities.clear(); //clears old abilities
        //adding newly computed abilities
        basicSkillAbilities.add(new DirectAbility(
                "Bind Wound",
                "Uses a bind wound skill",
                new Effect(new StatStructure(StatsEnum.LIFE, this.getBasicSkillValue(SkillsEnum.BINDWOUNDS) + 1)),
                new Requirement(0),
                new Effect(new StatStructure(StatsEnum.MANA, 0))));
        //recomputes occupational abilities
        recomputeOccupationalAbilities();
    } // end update

    @Override
    public void remove() {

    } // end remove

}
