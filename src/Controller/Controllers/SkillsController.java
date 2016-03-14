package Controller.Controllers;

import Controller.Controller;
import Model.Abilities.CommandsEnum;
import State.State;
import State.States.GameState.SettingState;
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

        if(e.getKeyCode() == Settings.UP||e.getKeyCode() == KeyEvent.VK_UP) {
            ((SkillsState)state).up();
        } else if (e.getKeyCode() == Settings.DOWN ||e.getKeyCode() == KeyEvent.VK_DOWN) {           /* cursor down */
            ((SkillsState)state).down();
        } else if (e.getKeyCode() == Settings.DOWN_LEFT ||      /* cursor left */
                e.getKeyCode() == Settings.UP_LEFT||e.getKeyCode() == KeyEvent.VK_LEFT) {
            ((SkillsState)state).left();
        } else if (e.getKeyCode() == Settings.DOWN_RIGHT ||     /* cursor right */
                e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ((SkillsState)state).right();
        } else if (e.getKeyCode() == Settings.INTERACT||e.getKeyCode()==Settings.ENTER) {       /* assign skill point */
            ((SkillsState)state).executePlayerCommand(CommandsEnum.interact);
        } else if (e.getKeyCode() == Settings.SKILLS) {         /* exit to gamestate */
            state.switchState(State.GAMESTATE);
        } else if (e.getKeyCode() == Settings.ESC) {            /* open pausestate */
            state.switchState(State.PAUSESTATE);
        }
        else if (e.getKeyCode() == Settings.ONE) {            /* open pausestate */
            ((SkillsState) state).set(1);
        }
        else if (e.getKeyCode() == Settings.TWO) {            /* open pausestate */
            ((SkillsState) state).set(2);
        }
        else if (e.getKeyCode() == Settings.THREE) {            /* open pausestate */
            ((SkillsState) state).set(3);
        }
        /**
         * add code to reassign hotkeys for abilities
         */
    } // end keyPressed

} // end class SkillsController
