package View.Views;

import State.States.GameState.SettingState;
import Utilities.Settings;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Dartyx on 3/9/2016.
 */
public class SettingView implements Renderable {

    private int width, height;
    double mX,mY;

    String array[]={"North Key","NE Key","SE Key","South Key","SW Key","NW Key","Attack Key","Interact Key","Inventory Key","Equipment Key"};

    public SettingView() {

    }
    public void render(Graphics g){}

    public void render(Graphics g, int s, boolean newKey) {
        width = Settings.GAMEWIDTH;
        height = Settings.GAMEHEIGHT;

        int fontW,xMove,yMove;


        mX = ((double) Settings.GAMEWIDTH) / 800;
        mY = ((double) Settings.GAMEHEIGHT) / 600;
        double ds = 64 * 4 / 5 * mX;
        int size = ((int) ds);
        double incY = 10 * mY;
        int intY = ((int) incY);
        double incX = 10 * mX;
        int intX = ((int) incX);
        int resetX,resetY,tempX,tempY;
        resetX=width/2;
        resetY=175;
        tempX=resetX+intX/2;
        tempY=resetY;
        //making the giant box
        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(width / 10, height / 10, width * 4 / 5, height * 4 / 5);
        //making the title
        g.setColor(new Color(255, 255, 255, 255));
        g.setFont(new Font("Arial", Font.PLAIN, 40*intY/10));
        FontMetrics fm = g.getFontMetrics();
        int totalWidth = (fm.stringWidth("Settings"));
        int tempMove= (width-totalWidth)/2;
        g.drawString("Settings",tempMove,height/10+40*intY/10);
        if(!newKey) {
            int totalWidth2 = (fm.stringWidth("Select key to change!"));
            int tempMove2 = (width - totalWidth2) / 2;
            g.drawString("Select key to change!", tempMove2, height / 10 + 40 * intY / 5);
        }
        else{
            int totalWidth2 = (fm.stringWidth("Press desired key for new function"));
            int tempMove2 = (width - totalWidth2) / 2;
            g.drawString("Press desired key for new function", tempMove2, height / 10 + 40 * intY / 5);
        }
        //making the individual boxes
        tempX=resetX-width/4-intX/2;

        for(int i=0;i<10;i++){
            //print out square
            if(i==s)g.setColor(new Color(12, 12, 12, 255));
            else g.setColor(new Color(12, 12, 12, 200));
            g.fillRect(tempX,tempY,width/4,height*3/32);
            //print out item
            g.setColor(new Color(255, 255, 255, 255));
            g.setFont(new Font("Arial", Font.PLAIN, 24*intY/10));
            fm = g.getFontMetrics();
            fontW = (fm.stringWidth(array[i]));
            xMove= tempX+(width/4-fontW)/2;
            yMove=tempY+(height*3/32)/2+fm.getHeight()/4;
            g.drawString(array[i],xMove,yMove);

            //move on
            tempY+=height*3/32+intY;
            if(i==4){
                tempY=resetY;//reset
                tempX=resetX+intX/2;
            }
        }

    }
}
