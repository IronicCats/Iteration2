package Controller.Controllers;

import Controller.Controller;
import StatesEnum.States.GameState.GameState;

import java.awt.event.KeyEvent;


/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class GameController extends Controller {

    public GameController(GameState state) {
        super(state);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Game: " + e.getKeyCode());

        switch (e.getKeyCode()) {
            case 38:
            case 104:
                //up key 8
                ((GameState) state).movePlayer(90);
                break;
            case 103:
                //leftup key 7
                ((GameState) state).movePlayer(135);
                break;
            case 105:
                //righttup key 9
                ((GameState) state).movePlayer(45);
                break;
            case 97:
                //leftup key 1
                ((GameState) state).movePlayer(225);
                break;
            case 40:
            case 98:
                //down key 2
                ((GameState) state).movePlayer(270);
                break;
            case 99:
                //rightdown key 3
                ((GameState) state).movePlayer(315);
                break;
            default:
                break;
        }

    }
}
