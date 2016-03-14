package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.CreateState;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by broskj on 3/13/16.
 */
public class CreateController extends Controller {
    public CreateController(State state) {
        super(state);
    } // end constructor

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) {
            return;
        }

        if(e.getKeyCode() == Settings.ESC) {
            ((CreateState)state).switchState(State.INITIALSTATE);
        } else if (e.getKeyCode() == Settings.ENTER) {
            ((CreateState)state).makeSelection();
        } else if (e.getKeyCode() == Settings.UP) {
            ((CreateState)state).up();
        } else if(e.getKeyCode() == Settings.DOWN) {
            ((CreateState)state).down();
        }
    } // end keyPressed
} // end class CreateController
