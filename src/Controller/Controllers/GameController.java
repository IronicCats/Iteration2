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

        switch(e.getKeyCode()) {
            case 38:
                //up key 8
                ((GameState)state).movePlayer(90);
                break;
            case 36:
                //leftup key 7
                ((GameState)state).movePlayer(135);
                break;
            case 33:
                //leftup key 9
                ((GameState)state).movePlayer(45);
                break;
            case 35:
                //leftup key 1
                ((GameState)state).movePlayer(225);
                break;
            case 40:
                //down key 2
                ((GameState)state).movePlayer(270);
                break;
            case 34:
                //rightdown key 3
                ((GameState)state).movePlayer(315);
                break;
            default:
                break;
        }
    }
    //7-36
    //8-38
    //9-33
    //1-35
    //2-40
    //3-34

}
