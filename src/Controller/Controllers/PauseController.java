package Controller.Controllers;

import State.State;
import State.States.GameState.PauseState;
import Controller.Controller;
import State.States.MenuState;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class PauseController extends Controller{

    private int currentState;

    public PauseController(PauseState state) {
        super(state);
        currentState = 1;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if (e.getKeyCode() == Settings.ESC) {
            state.switchState(State.GAMESTATE);
            System.out.println("Game was Paused");
        }

        if(e.getKeyCode() == KeyEvent.VK_UP && currentState>1){
            currentState--;
            ((MenuState)state).moveUp();
            //System.out.println("state = " + currentState);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN&& currentState<3){
            currentState++;
            ((MenuState)state).moveDown();
            //System.out.println("state =" + currentState);
        }
    }
}
