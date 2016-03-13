package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.GameState.InventoryState;
import State.States.GameState.SettingState;
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
        else if(e.getKeyCode() == Settings.UP || e.getKeyCode() == KeyEvent.VK_UP) {          /* move cursor up */
            ((SettingState)state).up();
        }
        else if(e.getKeyCode() == Settings.DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) {        /* move cursor down */
            ((SettingState)state).down();
        }
        else if(e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) {   /* move cursor down */
            ((SettingState)state).left();
        }
        else if(e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {  /* move cursor down */
            ((SettingState)state).right();
        }

    }
}
