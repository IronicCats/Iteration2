package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.GameState.PauseState;
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
        }

        if(e.getKeyCode() == KeyEvent.VK_UP && currentState>1){
            currentState--;
            ((PauseState)state).moveUp();
            //System.out.println("state = " + currentState);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN&& currentState<5){
            currentState++;
            ((PauseState)state).moveDown();
            //System.out.println("state =" + currentState);
        }
        if(e.getKeyCode() == 10) {
            switch(currentState){
                case 1:
                    state.switchState(State.GAMESTATE);
                    break;
                case 2:
                    state.switchState(State.SAVESTATE);
                    break;
                case 3:
                    State.LOADSTATE.setLastState(1);
                    state.switchState(State.LOADSTATE);
                    break;
                case 4:
                    state.switchState(State.SETTINGSTATE);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
    }

}
