package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Stats.PlayerStats;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;

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
    private PlayerStats playerStats;
    private Map<SkillsEnum, Integer> basicSkills;
    private Map<SkillsEnum, Integer> occupationalSkills;
    private ArrayList<Observer> observers;

    //constructor
    public Occupation(String name, String description, int[] val)
    {
        this.name = name;
        this.description = description;
        StatsEnum[] stats = new StatsEnum[]{StatsEnum.LIVES_LEFT, StatsEnum.STRENGTH,StatsEnum.AGILITY,
                StatsEnum.INTELLECT, StatsEnum.HARDINESS, StatsEnum.EXPERIENCE, StatsEnum.MOVEMENT};
        initialStats = new StatStructure(stats, val);
        playerStats = new PlayerStats(initialStats);
        this.basicSkills = new EnumMap(SkillsEnum.class);
        this.occupationalSkills = new EnumMap(SkillsEnum.class);

    }

    //accessor methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StatStructure getInitialStats() {
        return initialStats;
    }

    public Map<SkillsEnum, Integer> getBasicSkills(){
        return basicSkills;
    }

    public Map<SkillsEnum, Integer> getOccupationalSkills(){
        return occupationalSkills;
    }

    public void setOccupationalSkills(Map<SkillsEnum, Integer> m){
        this.occupationalSkills = m;
        alert();
    }

    public void setBasicSkills(Map<SkillsEnum, Integer> m){
        this.basicSkills = m;
        alert();
    }

    //skill related methods

    public void modifyBasicSkills(SkillsEnum s, int value){
        if(s.equals(SkillsEnum.BINDWOUNDS) || s.equals(SkillsEnum.BARGAIN) || s.equals(SkillsEnum.OBSERVATION)){
            basicSkills.put(s, value);
        }
        else {
            //do nothing
        }
        alert();
    }

    //different occupational skills for each occupation
    public abstract void modifyOccupationalSkills(SkillsEnum s, int value);

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

    } // end update

    @Override
    public void remove() {

    } // end remove

}
