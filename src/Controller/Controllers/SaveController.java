package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.GameState.SaveState;
import Utilities.SaveLoad;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class SaveController extends Controller {
    private int currentState;

    public SaveController(State state) {
        super(state);
        currentState = 1;
    } // end constructor


    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if (e.getKeyCode() == Settings.ESC) {
            state.switchState(State.GAMESTATE);
            System.out.println("Game was Paused, returning from saveState.");
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && currentState > 1) {
            currentState--;
            ((SaveState) state).moveUp();
            //System.out.println("state = " + currentState);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && currentState < 4) {
            currentState++;
            ((SaveState) state).moveDown();
            //System.out.println("state =" + currentState);
        }

        if (e.getKeyCode() == 10) {
            switch (currentState) {
                case 1:
                    //saveFile1
                    SaveLoad.getInstance();
                    SaveLoad.setCurrFileName("SaveFile1");
                    SaveLoad.setMobileObjects(State.GAMESTATE.getMobileObjects());
                    SaveLoad.setGameMap(State.GAMESTATE.getMap());
                    SaveLoad.setPlayer(State.GAMESTATE.getPlayer());
                    SaveLoad.save();
                    System.out.println("SaveFile1 should be saving.");
                    break;
                case 2:
                    //saveFile2
                    SaveLoad.getInstance();
                    SaveLoad.setCurrFileName("SaveFile2");
                    SaveLoad.setMobileObjects(State.GAMESTATE.getMobileObjects());
                    SaveLoad.setGameMap(State.GAMESTATE.getMap());
                    SaveLoad.setPlayer(State.GAMESTATE.getPlayer());
                    SaveLoad.save();
                    System.out.println("SaveFile2 should be saving");
                    break;
                case 3:
                    //saveFile3
                    SaveLoad.getInstance();
                    SaveLoad.setCurrFileName("SaveFile3");
                    SaveLoad.setMobileObjects(State.GAMESTATE.getMobileObjects());
                    SaveLoad.setGameMap(State.GAMESTATE.getMap());
                    SaveLoad.setPlayer(State.GAMESTATE.getPlayer());
                    SaveLoad.save();
                    System.out.println("SaveFile3 should be saving.");
                    break;
                case 4:
                    System.out.println("Switching to pauseState");
                    state.switchState(State.PAUSESTATE);
                    break;
                default:
                    break;
            }

        }
    }
}
