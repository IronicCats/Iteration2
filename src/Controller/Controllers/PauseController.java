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
                    System.out.println("Switching to gameState");
                    state.switchState(State.GAMESTATE);
                    break;
                case 2:
                    System.out.println("Switching to saveState");
                    break;
                case 3:
                    System.out.println("Switching to loadState");
                    break;
                case 4:
                    System.out.println("Switching to settingState");
                    state.switchState(State.SETTINGSTATE);
                    break;
                case 5:
                    System.out.println("leaving");
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }


    }

}
