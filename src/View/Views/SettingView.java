package View.Views;

import Utilities.Settings;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Dartyx on 3/9/2016.
 */
public class SettingView implements Renderable{
    private int width,height;
    public SettingView(){

    }
    public void render(Graphics g){
        width= Settings.GAMEWIDTH;
        height= Settings.GAMEHEIGHT;

        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(width/10, height/10, width*4/5, height*4/5);


    }
}
