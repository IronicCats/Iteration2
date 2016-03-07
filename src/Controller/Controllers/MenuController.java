package Controller.Controllers;

import Controller.Controller;
import State.States.MenuState;
import State.StatesEnum;

import java.awt.event.KeyEvent;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class MenuController extends Controller {

    public MenuController(MenuState state) {
        super(state);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) { return; }


        if(e.getKeyCode() == 10) {
            state.switchState(StatesEnum.GameState);
        }
    }

}
