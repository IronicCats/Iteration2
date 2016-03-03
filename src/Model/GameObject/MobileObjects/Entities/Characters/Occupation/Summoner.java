package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Summoner extends Occupation {

    //constructor
    public Summoner()
    {
        super("Summoner", "Specializes in spell casting", new int [] {5,5,5,7,5,0,5});
    }

    //operations
    public  void modifyOccupationalSkills(SkillsEnum s, int value){

    }
}
