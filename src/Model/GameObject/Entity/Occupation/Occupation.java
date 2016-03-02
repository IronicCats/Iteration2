package Model.GameObject.Entity.Occupation;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Occupation {
    private String name;
    private String description;
    // private StatStructure initialStats;

    public Occupation(String name, String description, int[] val)
    {
        this.name = name;
        this.description = description;
        /**
         StatsEnum[] stats = new StatsEnum[]{StatsEnum.LIVES_LEFT, StatsEnum.STRENGTH,StatsEnum.AGILITY,
         StatsEnum.INTELLECT, StatsEnum.HARDINESS, StatsEnum.EXPERIENCE, StatsEnum.MOVEMENT};
         initialStats = new StatStructure(stats, val);
         */
    }

    public String getName() {return name;}

    public String getDescription() {return description;}

    //public StatStructure getInitialStats() {return initialStats;}

}
