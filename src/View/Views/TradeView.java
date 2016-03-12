package View.Views;

import Model.Map.Map;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by broskj on 3/9/16.
 */
public class TradeView implements Renderable, Observer{
    private Map map;
    int width,height;
    double mX,mY;
    public TradeView() {
        //this.map = map;
        //map.addObserver(this);
    } // end constructor

    @Override
    public void render(Graphics g) {

    } // end render
    public void render(Graphics g, int s){
        //System.out.println(s);
        width= Settings.GAMEWIDTH;
        height= Settings.GAMEHEIGHT;

        mX=((double)Settings.GAMEWIDTH)/800;
        mY=((double)Settings.GAMEHEIGHT)/600;
        //fill the base rectangle
        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(width/10, height/10, width*4/5, height*4/5);
        //making the title

        //render the pack
        renderPackOne(g,s);
        renderPackTwo(g,s);
        //render the bottom right icons
        renderIcons(g,s);
        //render the bottom left descriptions
        renderDescriptions(g,s);

    }
    public void renderPackTwo(Graphics g,int s){

        double ds=64*4/5*mX;
        int size= ((int)ds);
        double incY=10*mY;
        int intY=((int)incY);
        double incX=10*mX;
        int intX=((int)incX);
        int tempX=width/2+intX;
        int tempY=height/2-(4*(size+intY))/2;


        g.setColor(new Color(12, 12, 12, 200));
        g.fillRect(tempX,tempY, 4*(size+intX), (-2)*tempY+height);
        tempX+=intX/2;
        tempY+=intY/2;

        for(int i=16;i<32;++i) {
            if (i != s) {
                g.drawImage(Assets.BOX2, tempX, tempY, size, size, null);
                //g.drawString("ayy"+i,tempX,tempY);
                //tempX+=64+10;
            } else {
                g.drawImage(Assets.BOX, tempX, tempY, size, size, null);
                g.setColor(new Color(200, 200, 200, 200));
                g.drawString("Shop:"+(i-16),tempX,tempY);
            }

            tempX += size + intX;
            if (i % 4 == 3) {
                tempY += size + intY;
                tempX = width / 2 + 3*intX / 2;
            }
        }
    }
    public void renderPackOne(Graphics g,int s){

        double ds=64*4/5*mX;
        int size= ((int)ds);
        double incY=10*mY;
        int intY=((int)incY);
        double incX=10*mX;
        int intX=((int)incX);
        int tempX=width/2-4*(size+intX)-intX;
        int tempY=height/2-(4*(size+intY))/2;


        g.setColor(new Color(12, 12, 12, 200));
        g.fillRect(tempX,tempY, 4*(size+intX), (-2)*tempY+height);
        tempX+=intX/2;
        tempY+=intY/2;

        for(int i=0;i<16;++i) {
            if (i != s) {
                g.drawImage(Assets.BOX2, tempX, tempY, size, size, null);
                //g.drawString("ayy"+i,tempX,tempY);
                //tempX+=64+10;
            } else {
                g.drawImage(Assets.BOX, tempX, tempY, size, size, null);
                g.setColor(new Color(200, 200, 200, 200));
                g.drawString("User:"+i,tempX,tempY);
            }

            tempX += size + intX;
            if (i % 4 == 3) {
                tempY += size + intY;
                tempX = width / 2 -4*(size+intX)-intX / 2;
            }
        }
    }
    public void renderIcons(Graphics g,int s){

    }
    public void renderDescriptions(Graphics g,int s){

    }


    @Override
    public void update() {

    } // end update

    @Override
    public void remove() {

    } // end remove
}
