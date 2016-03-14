package Model.Abilities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Tile;

import java.util.ArrayList;

/**
 * Created by TARIT on 3/14/2016.
 */
public class Observe extends PassiveAbility {

    public Observe(int skillLevel){
        super(skillLevel);
    }

    public void checkSurroundingTiles(ArrayList<Tile> tiles) {

    }
}
