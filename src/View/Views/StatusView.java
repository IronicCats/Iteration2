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

        mX = ((double) Settings.GAMEWIDTH) / 800;
        mY = ((double) Settings.GAMEHEIGHT) / 600;
        incY=10*mY;
        intY=((int)incY);
        renderClass(g);
        renderStats(g);


    }

    public void renderClass(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20*intY/10));

        FontMetrics fm = g.getFontMetrics();
        int circleWidth = ((int) mX * 100);
        int totalWidth = (fm.stringWidth(player.getOccupation().getName()));
        int tempMove= width - ((int)(mX)*125) - circleWidth/2 + totalWidth/2;
        g.setColor(Color.RED);
        g.drawString(player.getOccupation().getName(),tempMove, 25 );
        g.drawImage(Assets.CIRCLE, (width - ((int)(mX)*125)), 25,(int) mX * 100, (int) mX * 100, null);
    }

    public void renderStats(Graphics g) {

    }





}
