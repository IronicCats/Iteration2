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
    int cursor;
    public CreateController(CreateState state) {
        super(state);
        cursor = 0;
    } // end constructor

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) {
            return;
        }

        if(e.getKeyCode() == Settings.ESC) {
            ((CreateState)state).switchState(State.INITIALSTATE);
        } else if (e.getKeyCode() == Settings.ENTER) {
            ((CreateState)state).makeSelection(cursor);
        } else if (e.getKeyCode() == KeyEvent.VK_UP && cursor > 0) {
            cursor--;
            ((CreateState)state).moveUp();
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN && cursor < 2) {
            cursor++;
            ((CreateState)state).moveDown();
        }
    } // end keyPressed
} // end class CreateController
