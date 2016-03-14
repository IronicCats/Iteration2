package View.Views;

import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Joshua Kegley on 3/13/2016.
 */
public class StatusView implements Renderable {

    private Player player;
    private int height;
    private int width;

    private double mX;
    private double mY;

    private double incY;
    private int intY;

    public StatusView(Player player) {
        this.player = player;
    }

    @Override
    public void render(Graphics g) {
        width = Settings.GAMEWIDTH;
        height = Settings.GAMEHEIGHT;

        mX = Settings.GAMEWIDTH / 800;
        mY = Settings.GAMEHEIGHT / 600;
        incY=10*mY;
        intY=((int)incY);
        renderClass(g);
        renderStats(g);
        renderQuickKeys(g);

    }

    public void renderClass(Graphics g) {
        //System.out.println(width+" "+height);
        g.setFont(new Font("Arial", Font.PLAIN, 20*intY/10));

        FontMetrics fm = g.getFontMetrics();
        int circleWidth = ((int) mX * 100);
        int totalWidth = (fm.stringWidth(player.getOccupation().getName()));

        Image image;
        if(player.getOccupation().getName() == "Summoner") {
            image = Assets.SUMMONER;
        }else if(player.getOccupation().getName() == "Smasher"){
            image = Assets.SMASHER;
        }else {
            image = Assets.SNEAK;
        }
        g.setColor(new Color(12, 12, 12, 160));
        g.fillOval((width - ((int)(mX)*125)), 30,(int) mX * 98, (int) mX * 94);

        int tempMove = (width - ((int)(mX)*125)) + (circleWidth/2 - ((int) mX * 65)/2) + 5;
        g.drawImage(image,tempMove, 45, (int) mX * 65, (int) mX * 65, null);
        //TEXT FOR CLASS
        g.setColor(Color.WHITE);
        tempMove= (width - ((int)(mX)*125)) + (circleWidth/2 - totalWidth/2);
        g.drawString(player.getOccupation().getName(), tempMove, 25 );
        ///Cicrcle DESIGN
        g.drawImage(Assets.CIRCLE, (width - ((int)(mX)*125)), 25,(int) mX * 100, (int) mX * 100, null);
    }

    public void renderStats(Graphics g) {

    }

    public void renderQuickKeys(Graphics g) {
        g.setColor(new Color(12, 12, 12, 160));
        //System.out.println(height*11/12+" "+height/12);
        g.fillRect(width/4,height*11/12,width/2,height/12);

    }




}
