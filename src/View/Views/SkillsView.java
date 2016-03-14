package View.Views;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.*;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by broskj on 3/13/16.
 */
public class SkillsView implements Renderable, Observer {
    Player player;
    Occupation occupation;

    int width, height;
    double mX, mY;

    public SkillsView(Player player) {
        this.player = player;
        this.occupation = player.getOccupation();
    } // end constructor

    public void render(Graphics g, int cursor) {
        width = Settings.GAMEWIDTH;
        height = Settings.GAMEHEIGHT;

        mX = ((double) Settings.GAMEWIDTH) / 800;
        mY = ((double) Settings.GAMEHEIGHT) / 600;
        //fill the base rectangle
        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(width / 10, height / 10, width * 4 / 5, height * 4 / 5);
        //making the title
        String t;
        if(player.getOccupation() instanceof Smasher)t="Smasher";
        else if(player.getOccupation() instanceof Sneak)t="Sneak";
        else t="Summoner";
        double incY=10*mY;
        int intY=((int)incY);
        g.setColor(new Color(255, 255, 255, 255));
        g.setFont(new Font("Arial", Font.PLAIN, 40*intY/10));
        FontMetrics fm = g.getFontMetrics();
        int totalWidth = (fm.stringWidth(t+" Skills"));
        int tempMove= (width-totalWidth)/2;
        g.drawString(t+" Skills",tempMove,height/10+40*intY/10);
        //render basic skills
        int tempX=width/3-width/24;
        int tempY=height*3/12;
        SkillsEnum ayy[]={SkillsEnum.BINDWOUNDS,SkillsEnum.BARGAIN,SkillsEnum.OBSERVATION};
        String array[]={"Bind Wounds","Bargain","Observation"};
        for(int i=0;i<3;++i){
            if(cursor==i){g.setColor(new Color(12, 12, 12, 250));}
            else g.setColor(new Color(12, 12, 12, 170));
            g.fillRect(tempX,tempY,width/12,width/12);
            g.setFont(new Font("Arial", Font.PLAIN, 30*intY/10));
            g.setColor(new Color(255, 255, 255, 255));
            g.drawString(""+player.getOccupation().getBasicSkillValue(ayy[i]),tempX,tempY+width/12);
            tempX+=width/6;
        }
    //player.getOccupation().getAbilityAt()
        if(player.getOccupation()instanceof Sneak){
            //render specific skills
            //render descriptions
        }
        else if(player.getOccupation() instanceof Summoner){
            //render specific skills
            tempY+=
            for(int i=3;i<15;++i){


            }
            //render descriptions
        }
        else if(player.getOccupation()instanceof Smasher){
            //render specific skills
            //render descriptions
        }
    } // end render

    public void renderSmasher(Graphics g, int cursor) {

        //render the description
    } // end renderSlots

    public void renderSneak(Graphics g, int cursor) {

        //render the description
    } // end renderSlots

    public void renderSummoner(Graphics g, int cursor) {

        //render the description
    } // end renderSlots

    public void renderIcons(Graphics g, int cursor) {

    } // end renderIcons

    public void renderDescriptions(Graphics g, int cursor) {
    //have to be passed the individual skill
    }

    @Override
    public void update() {

    } // end update

    @Override
    public void remove() {

    } // end remove

    @Override
    public void render(Graphics g) {

    } // end render
} // end class SkillsView
