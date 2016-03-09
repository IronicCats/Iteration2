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
        if(e.getKeyCode() == Settings.ENTER){
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
            } else if (e.getKeyCode() == Settings.ESC) {
                // open menu
                System.out.println("escape pressed");
            } else if (e.getKeyCode() == Settings.SPACE) {
                // execute attack?
                System.out.println("space pressed");
            } else if (e.getKeyCode() == Settings.E) {
                state.switchState(State.EQUIPMENTSTATE);
            } else if (e.getKeyCode() == Settings.I) {
                state.switchState(State.INVENTORYSTATE);
            } else if (e.getKeyCode() == Settings.M) {
                // open map
                System.out.println("m pressed");
            } else if (e.getKeyCode() == Settings.Q) {
                ((GameState) state).playerInteract();
            } else if (e.getKeyCode() == Settings.D) {
                ((GameState) state).playerExamineInventory();
            }

        //if(e.getKeyCode() == KeyEvent.VK_)state.switchState(StatesEnum.GameState);

    }
}
