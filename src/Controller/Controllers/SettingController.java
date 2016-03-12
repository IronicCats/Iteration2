package Controller.Controllers;

import Controller.Controller;
import State.State;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/9/2016.
 */
public class SettingController extends Controller {
    public SettingController(State state) {
        super(state);
    } // end constructor

    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if (e.getKeyCode() == Settings.ESC) {
            state.switchState(State.PAUSESTATE);
            System.out.println("Game was in settings and is at pause menu");
        }

    }
}
