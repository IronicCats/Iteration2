package Model.GameObject.Entity.Occupation;

import Model.GameObject.Entity.Stats.StatStruc;
import Model.GameObject.Entity.StatsEnum;

/**
 * Created by mazumderm on 3/1/2016.
 */
public abstract class Occupation {
    private String name;
    private String description;
    private StatStruc initialStats;

    public Occupation(String name, String description, int[] val)
    {
        this.name = name;
        this.description = description;
        StatsEnum[] stats = new StatsEnum[]{StatsEnum.LIVES_LEFT, StatsEnum.STRENGTH,StatsEnum.AGILITY,
                StatsEnum.INTELLECT, StatsEnum.HARDINESS, StatsEnum.EXPERIENCE, StatsEnum.MOVEMENT};
        initialStats = new StatStruc(stats, val);
    }

    //accessor methods
    public String getName() {return name;}

    public String getDescription() {return description;}

    public StatStruc getInitialStats() {return initialStats;}

}
