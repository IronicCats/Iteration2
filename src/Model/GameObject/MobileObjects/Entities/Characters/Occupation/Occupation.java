package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

import Model.Stats.StatStruc;
import Model.Stats.StatsEnum;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by mazumderm on 3/1/2016.
 */
public abstract class Occupation {
    private String name;
    private String description;
    private StatStruc initialStats;
    //player stats maybe
    private Map<SkillsEnum, Integer> basicSkills;
    private Map<SkillsEnum, Integer> occupationalSkills;

    //constructor
    public Occupation(String name, String description, int[] val)
    {
        this.name = name;
        this.description = description;
        StatsEnum[] stats = new StatsEnum[]{StatsEnum.LIVES_LEFT, StatsEnum.STRENGTH,StatsEnum.AGILITY,
                StatsEnum.INTELLECT, StatsEnum.HARDINESS, StatsEnum.EXPERIENCE, StatsEnum.MOVEMENT};
        initialStats = new StatStruc(stats, val);
        //player stats maybe?
        this.basicSkills = new EnumMap(SkillsEnum.class);
        this.occupationalSkills = new EnumMap(SkillsEnum.class);

    }

    //accessor methods
    public String getName() {return name;}

    public String getDescription() {return description;}

    public StatStruc getInitialStats() {return initialStats;}

    public Map<SkillsEnum, Integer> getBasicSkills(){return basicSkills;}

    public Map<SkillsEnum, Integer> getOccupationalSkills(){return occupationalSkills;}

    public void setOccupationalSkills(Map<SkillsEnum, Integer> m){this.occupationalSkills = m;}

    public void setBasicSkills(Map<SkillsEnum, Integer> m){this.basicSkills = m;}

    //skill related methods

    public void modifyBasicSkills(SkillsEnum s, int value){
        if(s.equals(SkillsEnum.BINDWOUNDS) || s.equals(SkillsEnum.BARGAIN) || s.equals(SkillsEnum.OBSERVATION)){
            basicSkills.put(s, value);
        }
        else {
            //do nothing
        }
    }

    //different occupational skills for each occupation
    public abstract void modifyOccupationalSkills(SkillsEnum s, int value);

}
