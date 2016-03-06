package Controller;


import Model.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public abstract class Controller implements KeyListener {

    public void activate() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        doSomething(e.getKeyCode());
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void doSomething(int keyCode) {
        System.out.println(keyCode);
    }


}
