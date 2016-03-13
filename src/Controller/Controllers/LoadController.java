package Controller.Controllers;

import Controller.Controller;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import State.State;
import State.States.GameState.LoadState;
import Utilities.SaveLoad;
import Utilities.Settings;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class LoadController extends Controller{
    private int currentState;
    public LoadController(State state) {
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
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && currentState > 1) {
            currentState--;
            ((LoadState) state).moveUp();
            //System.out.println("state = " + currentState);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && currentState < 4) {
            currentState++;
            ((LoadState) state).moveDown();
            //System.out.println("state =" + currentState);
        }

        if (e.getKeyCode() == 10) {
            switch (currentState) {
                case 1:
                    //saveFile1
                    SaveLoad.getInstance();
                    SaveLoad.load("SaveFile1.sav");
                    System.out.println("SaveFile1 should be loading.");
                    State.GAMESTATE = SaveLoad.getGameState();//need to set it properly
                    State.GAMESTATE.setPlayer((Player)SaveLoad.getPlayer());
                    ((Player) SaveLoad.getPlayer()).update();
                    //SaveLoad.getGameMap().
                    if(State.LOADSTATE.getLastState() == 0){
                        state.switchState(State.GAMESTATE);
                    }

                    break;
                case 2:
                    //saveFile2
                    SaveLoad.getInstance();
                    SaveLoad.load("SaveFile2.sav");
                    System.out.println("SaveFile2 should be loading");
                    State.GAMESTATE = SaveLoad.getGameState();//need to set it properly
                    if(State.LOADSTATE.getLastState() == 0){
                        state.switchState(State.GAMESTATE);
                    }
                    break;
                case 3:
                    //saveFile3
                    SaveLoad.getInstance();
                    SaveLoad.load("SaveFile3.sav");
                    System.out.println("SaveFile3 should be loading.");
                    State.GAMESTATE = SaveLoad.getGameState();//need to set it properly
                    if(State.LOADSTATE.getLastState() == 0){
                        state.switchState(State.GAMESTATE);
                    }
                    break;
                case 4:
                    if(State.LOADSTATE.getLastState() == 0){//menu
                        state.switchState(State.MENUSTATE);
                        System.out.println("Switching to loadState");
                    }
                    if(State.LOADSTATE.getLastState() == 1){
                        System.out.println("Switching to pauseState");
                        state.switchState(State.PAUSESTATE);
                    }

                    break;
                default:
                    break;
            }
        }
    }
}
