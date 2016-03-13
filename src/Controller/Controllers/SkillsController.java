package Controller.Controllers;

import Controller.Controller;
import Model.Abilities.CommandsEnum;
import State.State;
import State.States.GameState.SkillsState;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by broskj on 3/13/16.
 */
public class SkillsController extends Controller {
    public SkillsController(State state) {
        super(state);
    } // end constructor

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) {
            return;
        }

        if(e.getKeyCode() == Settings.UP) {

        } else if (e.getKeyCode() == Settings.DOWN) {           /* cursor down */

        } else if (e.getKeyCode() == Settings.DOWN_LEFT ||      /* cursor left */
                e.getKeyCode() == Settings.UP_LEFT) {

        } else if (e.getKeyCode() == Settings.DOWN_RIGHT ||     /* cursor right */
                e.getKeyCode() == Settings.UP_RIGHT) {

        } else if (e.getKeyCode() == Settings.INTERACT) {       /* assign skill point */
            ((SkillsState)state).executePlayerCommand(CommandsEnum.interact);
        } else if (e.getKeyCode() == Settings.SKILLS) {         /* exit to gamestate */
            state.switchState(State.GAMESTATE);
        } else if (e.getKeyCode() == Settings.ESC) {            /* open pausestate */
            state.switchState(State.PAUSESTATE);
        }
        /**
         * add code to reassign hotkeys for abilities
         */
    } // end keyPressed

} // end class SkillsController
