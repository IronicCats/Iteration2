package View.Views;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Sneak;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Summoner;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;
import View.ViewUtilities.ViewModules.ViewModule;

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
    public void setPlayer(Player player) { this.player = player; }

    @Override
    public void render(Graphics g) {
        width = Settings.GAMEWIDTH;
        height = Settings.GAMEHEIGHT;

    mX = Settings.GAMEWIDTH / 800;
    mY = Settings.GAMEHEIGHT / 600;
    incY=10*mY;
    intY=((int)incY);
    renderClass(g);
    renderQuickKeys(g);
    renderStats(g);

}

public void renderClass(Graphics g) {
    //System.out.println(width+" "+height);

        g.setFont(new Font("Arial", Font.PLAIN, 20*intY/10));

        FontMetrics fm = g.getFontMetrics();
        int circleWidth = ((int) mX * 100);
        int totalWidth = (fm.stringWidth("Lvl "+player.getStats().getLevel()+" "+player.getOccupation().getName()));

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
        g.drawString("Lvl "+player.getStats().getLevel()+" "+player.getOccupation().getName(), tempMove, 25 );
        ///Cicrcle DESIGN
        g.drawImage(Assets.CIRCLE, (width - ((int)(mX)*125)), 25,(int) mX * 100, (int) mX * 100, null);
    }

    public void renderStats(Graphics g) {
        int x=width/4;
        int y=height*10/12+height/48;
        double life=player.getStats().getLife();
        double maxLife= player.getStats().getBaseLife();
        //System.out.println(life+" "+maxLife);
        g.setColor(Color.BLACK);
        //HEALTH
        g.drawRect(x + 10, y - 5, width/5, height/24);
        g.setColor(ViewModule.calculateHealthColor(life/maxLife));
        g.fillRect(x + 11, y - 4, (int) ((life/maxLife) * (width/5-3)), height/24);
        //HEALTH
        y+=height/24+height/100;
        g.setColor(Color.BLACK);
        g.drawRect(x + 10, y - 5, width/5, height/24);
        g.setColor(Color.BLUE);
        double mana=player.getStats().getMana();
        double maxMana= player.getStats().getBaseMana();
        g.fillRect(x + 11, y - 4, (int) ((mana/maxMana) * (width/5-3)), height/24);
    }

    public void renderQuickKeys(Graphics g) {
        g.setColor(new Color(12, 12, 12, 160));
        //System.out.println(height*11/12+" "+height/12);
        g.fillRect(width/4,height*10/12,width/2,height*2/12);
        int tempX=width/2-width/48;
        int tempY=height*41/48;
        int size=width*3/48;
        if(player.getOccupation()instanceof Sneak){
            for(int i=0;i<3;++i){
                g.setColor(new Color(12, 12, 12, 230));
                g.fillRect(tempX,tempY,size,size);
                tempX+=size+size/12;
            }

        }
        else if(player.getOccupation() instanceof Summoner){
            for(int i=0;i<4;++i){
                g.setColor(new Color(12, 12, 12, 230));
                g.fillRect(tempX,tempY,size,size);
                tempX+=size+size/12;
            }

        }
        else if(player.getOccupation()instanceof Smasher){
            for(int i=0;i<2;++i){
                g.setColor(new Color(12, 12, 12, 230));
                g.fillRect(tempX,tempY,size,size);
                tempX+=size+size/12;
            }

        }


    }




}
