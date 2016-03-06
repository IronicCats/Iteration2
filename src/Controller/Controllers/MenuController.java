package Controller.Controllers;

import Controller.Controller;
import States.State;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class MenuController extends Controller {


    public void doSomething(int keyCode) {
        System.out.println("Menu: " + keyCode);

        if(keyCode == 10) {
            State.setState(State.States.GameState);
        }


    }
}
