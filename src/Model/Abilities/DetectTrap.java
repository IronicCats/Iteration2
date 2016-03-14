package Model.Abilities;

import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Tile;
import Utilities.AIUtilities.FindTilesAround;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mazumderm on 3/14/2016.
 */
public class DetectTrap extends PassiveAbility {

    public DetectTrap(int skillLevel){
        super(skillLevel);
    }

    public void checkSurroundingTiles(ArrayList<Tile> t ) {
        Iterator<Tile> i = t.iterator();
        while(i.hasNext()){
            Tile ti = i.next();
            if(skillLevel == 0){
                if(ti.getAreaEffectEnum() == AreaEffectEnum.TRAP){
                    DisplayMessage.addMessage(new GameMessage("Trap in the vicinity", 5));
                    break;
                }
            }
            else if(skillLevel == 1 ){
                if(ti.getAreaEffectEnum() == AreaEffectEnum.TRAP){
                    DisplayMessage.addMessage(new GameMessage("Trap within two tiles", 5));
                    break;
                }
            }
            else{
                if(ti.getAreaEffectEnum() == AreaEffectEnum.TRAP){
                    DisplayMessage.addMessage(new GameMessage("Trap is at tile " + ti.getLocation().getX() + " , " + ti.getLocation().getY(), 5));
                    break;
                }
            }
        }
    }
}
