package Controller.Controllers;

import Controller.Controller;
import StatesEnum.State;
import StatesEnum.States.MenuState;
import StatesEnum.StatesEnum;

import java.awt.event.KeyEvent;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class MenuController extends Controller {

    public MenuController(MenuState state) {
        super(state);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Menu: " + e.getKeyCode());

        if(e.getKeyCode() == 10) {
            state.switchState(StatesEnum.GameState);
        }
    }

}
