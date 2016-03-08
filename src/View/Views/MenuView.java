package View.Views;

import Model.Map.Map;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;
import View.ViewUtilities.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Joshua Kegley on 3/1/2016.
 */
public class MenuView implements Renderable {
    private int width,height;
    private String[] menuItems = {"Create Game", "Load Game", "Exit Game"};
    //private States[] menuStates = {States.Create, States.Load, States.Exit};
    private int currentItem;

    public MenuView(){
    width= Settings.GAMEWIDTH;
    height= Settings.GAMEHEIGHT;
    currentItem=0;
    }

    @Override
    public void render(Graphics g) {

        //g.drawImage(Assets.background, 0, 0, 800, 600, null);
        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(0, 0, width, height);
        g.drawImage(Assets.BACK,0,0,width,height,null);
        g.drawImage(Assets.LEFT,0,height*1/5,width*7/24,height*4/5,null);
        g.drawImage(Assets.RIGHT,width*17/24,height*1/5,width*7/24,height*4/5,null);
        for(int i = 0; i < menuItems.length; ++i) {
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            FontMetrics fm = g.getFontMetrics();
            int totalWidth = (fm.stringWidth(menuItems[i]));
            int x = (width - totalWidth) / 2;
            int y = 50 + (height / 2) - 100 + 120 * i;

            if(i == currentItem){
                g.setColor(new Color(149, 165, 166, 175));
                g.fillRect(x, y - fm.getHeight() + (fm.getHeight() / 4), totalWidth, fm.getHeight() );
                g.setColor(new Color(243, 156, 18));

            }else {
                g.setColor(new Color(149, 165, 166, 175));
                g.fillRect(x, y - fm.getHeight() + (fm.getHeight() / 4), totalWidth, fm.getHeight() );
                g.setColor(new Color(231, 76, 60));
            }
            g.drawString(menuItems[i], x, y);

        }
        //changeColor();
        //Color color = new Color(redTitle, greenTitle, blueTitle);
        //int rgb = color.getRGB();
        /*
        for (int i = 0; i < Assets.title.getWidth(); i++) {
            for (int j = 0; j < Assets.title.getHeight(); j++) {
                Color c = new Color(Assets.title.getRGB(i, j));
                if(c.getAlpha() >= 255){
                    Assets.title.setRGB(i, j, rgb);
                }
            }
        }
        */
        g.setFont(new Font("Papyrus", Font.PLAIN, 100));
        g.setColor(new Color(255,255,255));
        FontMetrics fm = g.getFontMetrics();
        g.drawString("Ironic Cats",width / 2 - fm.stringWidth("Ironic Cats")/2, 25 + fm.getHeight()/2 );

    }
    public void next() {
        ++currentItem;
        if(currentItem > menuItems.length - 1){
            currentItem = 0;
        }
    }

    public void previous() {
        --currentItem;
        if(currentItem < 0){
            currentItem = menuItems.length - 1;
        }
    }

    public void render() {

    }

}
