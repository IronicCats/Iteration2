package Model.GameObject.MobileObjects.Entities.Characters.Occupation;

/**
 * Created by mazumderm on 3/1/2016.
 */
public class Sneak extends Occupation{

    //constructor
    public Sneak()
    {
        super("Sneak", "Specializes in ranged weapons,evading detection, and removing traps ", new int [] {5,5,7,5,5,0,5});
    }

    //operations
    public void modifyOccupationalSkills(SkillsEnum s, int value){

    }
}

