package Controller.Controllers;

import Controller.Controller;
import State.States.MenuState;
import State.StatesEnum;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class MenuController extends Controller {
    int currentState;
    public MenuController(MenuState state) {
        super(state);
        currentState=1;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) { return; }

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
        if(e.getKeyCode() == 10) {
            if(currentState==1)state.switchState(StatesEnum.GameState);
            else if(currentState==2)System.out.println("this is the load state");
            else if(currentState==3)System.exit(0);
        }
    }

}
