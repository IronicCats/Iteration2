package Controller.Controllers;

import Controller.Controller;
import Model.GameObject.MobileObjects.Entities.Characters.Player;

import java.awt.event.KeyEvent;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class GameController extends Controller {

    public void doSomething(int keyCode) {
        System.out.println("Game: " + keyCode);

        switch(keyCode) {
            case 1:
                break;
            default:
                break;
        }
    }


}
