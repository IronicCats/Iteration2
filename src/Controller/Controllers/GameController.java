package Controller.Controllers;

import Controller.Controller;
import Model.Abilities.CommandsEnum;
import State.State;
import State.States.GameState.GameState;
import Utilities.Settings;

import java.awt.event.KeyEvent;


/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class GameController extends Controller {

    public GameController(GameState state) {
        super(state);
    }
    private boolean cameraMoves;

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) { return; }
        //System.out.println("Game: " + e.getKeyCode());

        //If camera movement is true then move camera instead of player
        if(e.getKeyCode() == Settings.ENTER){
            if(!cameraMoves) {
                cameraMoves = true;
                waitingTime = 45;
                ((GameState) state).setCameraMoving(cameraMoves);
            }
            else{
                cameraMoves = false;
                waitingTime = 500;
                ((GameState) state).setCameraMoving(cameraMoves);
            }
            System.out.println("camera moving");
        }

            if (e.getKeyCode() == Settings.UP || e.getKeyCode() == 38) {
                ((GameState) state).move(Settings.NORTH);
            } else if (e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == 33) {
                ((GameState) state).move(Settings.NW);
            } else if (e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == 37) {
                ((GameState) state).move(Settings.SW);
            } else if (e.getKeyCode() == Settings.DOWN || e.getKeyCode() == 40) {
                ((GameState) state).move(Settings.SOUTH);
            } else if (e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == 39) {
                ((GameState) state).move(Settings.SE);
            } else if (e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == 34) {
                ((GameState) state).move(Settings.NE);
            } else if (e.getKeyCode() == Settings.ESC) {        /* open pause menu */
                state.switchState(State.PAUSESTATE);
            } else if (e.getKeyCode() == Settings.EQUIP) {          /* open equipment state */
                state.switchState(State.EQUIPMENTSTATE);
            } else if (e.getKeyCode() == Settings.ATTACK) {
                ((GameState) state).executePlayerCommand(CommandsEnum.attack);
            } else if (e.getKeyCode() == Settings.ONE) {        /* execute ability1 */
                ((GameState) state).executePlayerCommand(CommandsEnum.ability1);
            } else if (e.getKeyCode() == Settings.TWO) {        /* execute ability2 */
                ((GameState) state).executePlayerCommand(CommandsEnum.ability2);
            } else if (e.getKeyCode() == Settings.THREE) {      /* execute ability3 */
                ((GameState) state).executePlayerCommand(CommandsEnum.ability3);
            }   else if(e.getKeyCode() == Settings.FOUR){      /* execute Bind Wounds */
                ((GameState) state).executePlayerCommand(CommandsEnum.bindWounds);
            } else if (e.getKeyCode() == Settings.INVENTORY) {          /* open inventory state */
                state.switchState(State.INVENTORYSTATE);
            } else if (e.getKeyCode() == Settings.MAP) {          /* open map state */
            } else if (e.getKeyCode() == Settings.INTERACT) {          /* execute interaction */
                ((GameState) state).executePlayerCommand(CommandsEnum.interact);
            } else if (e.getKeyCode() == Settings.DROP) {          /* execute inventory dump (temporary?) */
                ((GameState) state).executePlayerCommand(CommandsEnum.drop);
            } else if(e.getKeyCode() == Settings.F){
                Settings.FOG = !Settings.FOG;
            } else if (e.getKeyCode() == Settings.SKILLS) {         /* open skills */
                state.switchState(State.SKILLSSTATE);
            }

        //these are already listed above
        //if(e.getKeyCode() == KeyEvent.VK_)state.switchState(StatesEnum.GameState);
        //if(e.getKeyCode() == KeyEvent.VK_I)state.switchState(State.INVENTORYSTATE);
        //if(e.getKeyCode() == KeyEvent.VK_E)state.switchState(State.EQUIPMENTSTATE);
        //if(e.getKeyCode() == KeyEvent.VK_P)state.switchState(State.PAUSESTATE);
    }
}
