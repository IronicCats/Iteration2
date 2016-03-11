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

    int width,height,mX,mY;
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
        mX=Settings.multiplyX;
        mY=Settings.multiplyY;
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
        g.setColor(new Color(12, 12, 12, 200));
        g.fillRect(width/2, height/4, width*4/10, height*5/10);
        int tempX=width/2;
        int tempY=height/4;
        for(int i=0;i<16;++i){
            if(i!=s){
                g.drawImage(Assets.BOX2,tempX,tempY,64,64,null);
                //g.drawString("ayy"+i,tempX,tempY);
                //tempX+=64+10;
            }
            else{
                g.drawImage(Assets.BOX,tempX,tempY,64,64,null);
            }

            tempX+=64+10;
            if(i%4==3){
                tempY+=64+10;
                tempX=width/2;
            }
        }
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
