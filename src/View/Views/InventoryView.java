package View.Views;

import Model.Map.Map;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class InventoryView implements Renderable, Observer {
    //private Map map;
    //int selector;

    int width,height;
    double mX,mY;
    public InventoryView(){

        //this.map = map;
        //selector=0;
        //map.addObserver(this);
    }

    @Override
    public void render(Graphics g) {

    }
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
        renderPack(g,s);
        //render the bottom right icons
        renderIcons(g);
        //render the bottom left descriptions
        renderDescriptions(g);
        //render the stats
        renderStats(g);
    }

    public void renderPack(Graphics g, int s) {

        double ds=64*4/5*mX;
        int size= ((int)ds);
        double incY=10*mY;
        int intY=((int)incY);
        double incX=10*mX;
        int intX=((int)incX);
        int tempX=width/2;
        int tempY=height/2-(4*(size+intY))/2;


        g.setColor(new Color(12, 12, 12, 200));
        g.fillRect(tempX,tempY, 4*(size+intX), (-2)*tempY+height);
        tempX+=intX/2;
        tempY+=intY/2;

        for(int i=0;i<16;++i){
            if(i!=s){
                g.drawImage(Assets.BOX2,tempX,tempY,size,size,null);
                //g.drawString("ayy"+i,tempX,tempY);
                //tempX+=64+10;
            }
            else{
                g.drawImage(Assets.BOX,tempX,tempY,size,size,null);
            }

            tempX+=size+intX;
            if(i%4==3){
                tempY+=size+intY;
                tempX=width/2+intX/2;
            }
        }
    //System.out.print(mX+"   "+mY+" \n");
    //System.out.print(Settings.GAMEWIDTH+"   "+Settings.GAMEHEIGHT+" \n");
    }
    public void renderIcons(Graphics g) {

    }
    public void renderDescriptions(Graphics g) {

    }
    public void renderStats(Graphics g) {

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }



}
