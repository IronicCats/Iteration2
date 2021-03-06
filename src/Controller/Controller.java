package Controller;


import State.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public abstract class Controller implements KeyListener {

    public State state;
    protected long lastPressProcessed = 0;
    public int waitingTime = 500;


    public Controller(State state) {
        this.state = state;
    }
    public void activate() {
    }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
        lastPressProcessed = 0;
    }

    public boolean canGetInput() {
        if(System.currentTimeMillis() - lastPressProcessed > waitingTime) {
            lastPressProcessed = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
