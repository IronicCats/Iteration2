package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.GameState.InventoryState;
import State.States.GameState.SettingState;
import Utilities.HotKeys;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/9/2016.
 */
public class SettingController extends Controller {

    private boolean newKey = false;

    public SettingController(State state) {
        super(state);
    } // end constructor

    public boolean getStatus(){
        return newKey;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if (!newKey) {
            if (e.getKeyCode() == Settings.ESC) {
                state.switchState(State.PAUSESTATE);
                System.out.println("Game was in settings and is at pause menu");
            } else if (e.getKeyCode() == Settings.UP || e.getKeyCode() == KeyEvent.VK_UP) {          /* move cursor up */
                ((SettingState) state).up();
            } else if (e.getKeyCode() == Settings.DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) {        /* move cursor down */
                ((SettingState) state).down();
            } else if (e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) {   /* move cursor down */
                ((SettingState) state).left();
            } else if (e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {  /* move cursor down */
                ((SettingState) state).right();
            } else if (e.getKeyCode() == Settings.ENTER) {
                newKey = true;
            }
        }
        else{
            switch(((SettingState)state).getSelection()){
                case 0:
                    HotKeys.adjustKey(Settings.UP, e.getKeyCode());
                    break;
                case 1:
                    HotKeys.adjustKey(Settings.UP_RIGHT, e.getKeyCode());
                    break;
                case 2:
                    HotKeys.adjustKey(Settings.DOWN_RIGHT, e.getKeyCode());
                    break;
                case 3:
                    HotKeys.adjustKey(Settings.DOWN, e.getKeyCode());
                    break;
                case 4:
                    HotKeys.adjustKey(Settings.DOWN_LEFT, e.getKeyCode());
                    break;
                case 5:
                    HotKeys.adjustKey(Settings.UP_LEFT, e.getKeyCode());
                    break;
                case 6:
                    HotKeys.adjustKey(Settings.ATTACK, e.getKeyCode());
                    break;
                case 7:
                    HotKeys.adjustKey(Settings.INTERACT, e.getKeyCode());
                    break;
                case 8:
                    HotKeys.adjustKey(Settings.INVENTORY, e.getKeyCode());
                    break;
                case 9:
                    HotKeys.adjustKey(Settings.EQUIP, e.getKeyCode());
                    break;
            }
            newKey = false;
        }
    }
}
