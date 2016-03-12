package View.Views;

import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class PauseView implements Renderable {

    private int width,height;
    private String[] pauseMenuItems = {"Resume Game", "Save Game", "Load Game", "Settings", "Exit Game"};
    private int currentItem;


    public PauseView(){
        currentItem=0;
    }

    @Override
    public void render(Graphics g) {

        width= Settings.GAMEWIDTH;
        height= Settings.GAMEHEIGHT;
        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(width/2-200, height/2-250, 400, 500);
        for(int i = 0; i < pauseMenuItems.length; ++i) {
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            FontMetrics fm = g.getFontMetrics();
            int totalWidth = (fm.stringWidth(pauseMenuItems[i]));
            int x = (width - totalWidth) / 2;
            int y =  (height / 2) - 100 + 75 * i;

            if(i == currentItem){
                g.setColor(new Color(149, 165, 166, 175));
                g.fillRect(x, y - fm.getHeight() + (fm.getHeight() / 4), totalWidth, fm.getHeight() );
                g.setColor(new Color(228, 241, 254));

            }else {
                g.setColor(new Color(149, 165, 166, 175));
                g.fillRect(x, y - fm.getHeight() + (fm.getHeight() / 4), totalWidth, fm.getHeight() );
                g.setColor(new Color(25, 181, 254));
            }

            g.drawString(pauseMenuItems[i], x, y);

        }

        g.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 50));
        g.setColor(new Color(255,255,255));
        FontMetrics fm = g.getFontMetrics();
        g.drawString("Menu",width / 2 - fm.stringWidth("Menu")/2, height/2-200+fm.getHeight()/2 );
    }

    public void next() {
        ++currentItem;
        if(currentItem > pauseMenuItems.length - 1){
            currentItem = 0;
        }
    }

    public void previous() {
        --currentItem;
        if(currentItem < 0){
            currentItem = pauseMenuItems.length - 1;
        }
    }
}
