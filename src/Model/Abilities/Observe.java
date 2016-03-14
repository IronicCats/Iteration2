package Model.Abilities;

import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Tile;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by TARIT on 3/14/2016.
 */
public class Observe extends PassiveAbility {

    public Observe(int skillLevel){
        super(skillLevel);
    }

    public void checkSurroundingTiles(ArrayList<Tile> tiles) {

        Iterator<Tile> i = tiles.iterator();
        while(i.hasNext()){
            Tile ti = i.next();
            int offensiveRating = (((Player)(ti.getObject())).getStats().getOffensiveRating());
            Occupation occupation = (((Player)(ti.getObject())).getOccupation());
            int defensiveRating = (((Player)(ti.getObject())).getStats().getDefensiveRating());
            if(skillLevel == 0){
                if (ti.hasObject()){
                   DisplayMessage.addMessage(new GameMessage(" Defensive Rating " + (defensiveRating - 3), 10));
                }
            }
            else if(skillLevel == 1 ){
                if (ti.hasObject()){
                    DisplayMessage.addMessage(new GameMessage(" Defensive Rating " + defensiveRating + "\n" + " Occupation" + occupation, 10));
                }
            }
            else{
                if (ti.hasObject()){
                    DisplayMessage.addMessage(new GameMessage(" Offensive Rating " + offensiveRating , 10));
                }
            }
        }
    }
}
