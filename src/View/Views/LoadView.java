package View.Views;

import Utilities.Settings;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class LoadView implements Renderable {

    private int width,height;
    private int currentItem;
    private String[] loadViewItems = {"SaveFile1","SaveFile2","SaveFile3","Back"};
    public LoadView(){

    }
    public void render(Graphics g){
        width= Settings.GAMEWIDTH;
        height= Settings.GAMEHEIGHT;
        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(width/2-200, height/2-250, 400, 500);


        for(int i = 0; i < loadViewItems.length; ++i) {
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            FontMetrics fm = g.getFontMetrics();
            int totalWidth = (fm.stringWidth(loadViewItems[i]));
            int x = (width - totalWidth) / 2;
            int y = (height / 2) - 100 + 75 * i;

            if (i == currentItem) {
                g.setColor(new Color(149, 165, 166, 175));
                g.fillRect(x, y - fm.getHeight() + (fm.getHeight() / 4), totalWidth, fm.getHeight());
                g.setColor(new Color(228, 241, 254));

            } else {
                g.setColor(new Color(149, 165, 166, 175));
                g.fillRect(x, y - fm.getHeight() + (fm.getHeight() / 4), totalWidth, fm.getHeight());
                g.setColor(new Color(25, 181, 254));
            }

            g.drawString(loadViewItems[i], x, y);
        }
    }

    public void next() {
        ++currentItem;
        if(currentItem > loadViewItems.length - 1){
            currentItem = 0;
        }
    }

    public void previous() {
        --currentItem;
        if(currentItem < 0){
            currentItem = loadViewItems.length - 1;
        }
    }
}
