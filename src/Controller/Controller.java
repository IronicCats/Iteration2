package Controller;


import State.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public abstract class Controller implements KeyListener {
    public State state;
    protected long lastPressProcessed = 0;


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
        lastPressProcessed = 0;
    }

    public boolean canGetInput() {
        if(System.currentTimeMillis() - lastPressProcessed > 500) {
            lastPressProcessed = System.currentTimeMillis();
            return true;
        }
        return false;
    }


}
