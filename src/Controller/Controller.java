package Controller;


import State.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public abstract class Controller implements KeyListener {
    public State state;
    public Controller(State state) {
        this.state = state;
    }
    public void activate() {
    }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Typed: " + e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed: " + e.getKeyCode());
    }


    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Released: " + e.getKeyCode());
    }

}
