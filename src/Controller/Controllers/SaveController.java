package Controller.Controllers;

import Controller.Controller;
import State.State;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class SaveController extends Controller {
    public SaveController(State state) {
        super(state);
    } // end constructor

    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if (e.getKeyCode() == Settings.ESC) {
            state.switchState(State.GAMESTATE);
            System.out.println("Game was Paused");
        }

    }
}
