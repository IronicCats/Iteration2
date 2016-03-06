package View;

import StatesEnum.State;
import Utilities.Settings;
import View.ViewUtilities.MainScreen;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class View implements Runnable {

    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;

    public View(MainScreen mainScreen){
        canvas = mainScreen.getCanvas();
        mainScreen.setVisible(true);
    }

    public void run() {
        while(true) {
            long lastTime = System.currentTimeMillis();


            render();

            double delta = System.currentTimeMillis() - lastTime;
            if(delta < 50) {
                try {
                    Thread.sleep(((long)(50 - delta)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("Redraw Graphics");
        }
    }

    public void render() {
        bs = this.getCanvas().getBufferStrategy();
        if(bs == null) {
            this.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        g.clearRect(0, 0, Settings.GAMEWIDTH, Settings.GAMEHEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Settings.GAMEWIDTH, Settings.GAMEHEIGHT);

        if(State.getState() != null ) {
            State.getState().render(g);
        }

        bs.show();
        g.dispose();

    }

    public synchronized void start() {
        new Thread(this).start();
    }


    public Canvas getCanvas() { return canvas; }

}
