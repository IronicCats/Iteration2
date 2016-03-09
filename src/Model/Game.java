package Model;

import State.State;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class Game implements Runnable {

    //Used for Game Loop

    private boolean running = false;
    private Thread thread;


    public void run() {

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if( delta >= 1 ) {
                //DO STUFF
                try {
                    if (State.getCurrentState() != null) {
                        State.getCurrentState().tick();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                //
                ticks++;
                delta--;
            }

            if( timer >= 1000000000 ) {
                ticks = 0;
                timer = 0;
            }

        }

        stop();
    }


    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }


    public synchronized void stop() {
        if( !running ) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
