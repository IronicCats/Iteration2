package Controller.Controllers;

import Controller.Controller;
import State.StatesEnum;
import Utilities.Settings;
import State.States.GameState.GameState;

import java.awt.event.KeyEvent;


/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class GameController extends Controller {

    public GameController(GameState state) {
        super(state);
    }
    private boolean CameraMoves;

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) { return; }
        //System.out.println("Game: " + e.getKeyCode());

        //If camera movement is true then move camera instead of player
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("Enter key pressed");
            if(!CameraMoves) {
                CameraMoves = true;
                ((GameState) state).saveCameraOffsets();
            }
            else{
                CameraMoves = false;
                ((GameState) state).BackToOriginalOffset();
            }
        }

        if(!CameraMoves) {
            if (e.getKeyCode() == Settings.UP || e.getKeyCode() == 38) {
                ((GameState) state).movePlayer(90);
            } else if (e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == 33) {
                ((GameState) state).movePlayer(135);
            } else if (e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == 37) {
                ((GameState) state).movePlayer(225);
            } else if (e.getKeyCode() == Settings.DOWN || e.getKeyCode() == 40) {
                ((GameState) state).movePlayer(270);
            } else if (e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == 39) {
                ((GameState) state).movePlayer(315);
            } else if (e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == 34) {
                ((GameState) state).movePlayer(45);
            }
        }
        else {
                if (e.getKeyCode() == Settings.UP || e.getKeyCode() == 38) {
                    ((GameState) state).moveCamera(90);
                } else if (e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == 33) {
                    ((GameState) state).moveCamera(135);
                } else if (e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == 37) {
                    ((GameState) state).moveCamera(225);
                } else if (e.getKeyCode() == Settings.DOWN || e.getKeyCode() == 40) {
                    ((GameState) state).moveCamera(270);
                } else if (e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == 39) {
                    ((GameState) state).moveCamera(315);
                } else if (e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == 34) {
                    ((GameState) state).moveCamera(45);
                }
            }

        //if(e.getKeyCode() == KeyEvent.VK_)state.switchState(StatesEnum.GameState);
        if(e.getKeyCode() == KeyEvent.VK_I)state.switchState(StatesEnum.InventoryState);
        if(e.getKeyCode() == KeyEvent.VK_E)state.switchState(StatesEnum.EquipmentState);

    }
}
