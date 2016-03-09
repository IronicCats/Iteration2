package Controller.Controllers;

import Controller.Controller;
import State.State;
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
    private boolean cameraMoves;

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) { return; }
        //System.out.println("Game: " + e.getKeyCode());

        //If camera movement is true then move camera instead of player
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!cameraMoves) {
                cameraMoves = true;
                waitingTime = 45;
                ((GameState) state).SetCameramoving(cameraMoves);
            }
            else{
                cameraMoves = false;
                waitingTime = 500;
                ((GameState) state).SetCameramoving(cameraMoves);
            }
        }

            if (e.getKeyCode() == Settings.UP || e.getKeyCode() == 38) {
                ((GameState) state).move(90);
            } else if (e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == 33) {
                ((GameState) state).move(135);
            } else if (e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == 37) {
                ((GameState) state).move(225);
            } else if (e.getKeyCode() == Settings.DOWN || e.getKeyCode() == 40) {
                ((GameState) state).move(270);
            } else if (e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == 39) {
                ((GameState) state).move(315);
            } else if (e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == 34) {
                ((GameState) state).move(45);
            }

        //if(e.getKeyCode() == KeyEvent.VK_)state.switchState(StatesEnum.GameState);
        if(e.getKeyCode() == KeyEvent.VK_I)state.switchState(State.INVENTORYSTATE);
        if(e.getKeyCode() == KeyEvent.VK_E)state.switchState(State.EQUIPMENTSTATE);

    }
}
