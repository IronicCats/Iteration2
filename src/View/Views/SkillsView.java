package View.Views;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.*;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Utilities.AbilitiesUtilities.AbilityEnum;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
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

    public void setPlayer(Player player) {
        this.player = player;
        occupation = player.getOccupation();
    }


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
        int resetX,resetY;
        int tempX=resetX=width/3-width/24;
        int tempY=resetY=height*3/12;
        int size=width/12;
        SkillsEnum ayy[]={SkillsEnum.BINDWOUNDS,SkillsEnum.BARGAIN,SkillsEnum.OBSERVATION};
        String array[]={"Bind Wounds","Bargain","Observation"};
        for(int i=0;i<3;++i){
            if(cursor==i){g.setColor(Color.BLUE);}
            else g.setColor(new Color(12, 12, 12, 170));
            g.fillRect(tempX,tempY,width/12,width/12);
            g.drawImage(Assets.allArt.get(i),tempX+size*1/12,tempY+size*1/12,size*10/12,size*10/12,null);
            g.setFont(new Font("Arial", Font.PLAIN, 30*intY/10));
            g.setColor(new Color(255, 255, 255, 255));
            g.drawString(""+player.getOccupation().getBasicSkillValue(ayy[i]),tempX,tempY+width /12);
            tempX+=width/6;
        }


    //player.getOccupation().getAbilityAt()
        if(player.getOccupation()instanceof Sneak){
            SkillsEnum arrayylmao[]={SkillsEnum.DRTRAP,SkillsEnum.CREEP,SkillsEnum.RANGEWEAP,SkillsEnum.PICKPOCK};
            //render specific skills
            tempY+=size*4/3;
            tempX=resetX+size;
            for(int i=3;i<7;++i){
                if(cursor==i){g.setColor(Color.BLUE);}
                else g.setColor(new Color(12, 12, 12, 170));
                g.fillRect(tempX,tempY,size,size);
                g.drawImage(Assets.sneakArt.get(i-3),tempX+size*1/12,tempY+size*1/12,size*10/12,size*10/12,null);
                g.setColor(new Color(255, 255, 255, 255));
                g.drawString(""+player.getOccupation().getOccupationalSkillsValue(arrayylmao[i-3]),tempX,tempY+width/12);
                if(i%2==0){
                    tempY+=2*size;
                    tempX=resetX+size;
                }
                else tempX+=2*size;

            }
            //render descriptions
            g.setColor(Color.BLACK);
            g.fillRect(width*1/4,height*2/3+height/12,width/2,height/6-height/24);
        }
        else if(player.getOccupation() instanceof Summoner){
            SkillsEnum arrayylmao[]={SkillsEnum.BANE,SkillsEnum.BOON,SkillsEnum.ENCHANT,SkillsEnum.STAFF};

            //render specific skills
            tempY+=size*4/3;
            tempX=resetX;
            int e=0;int f=0;
            for(int i=3;i<16;++i) {
                //print box
                if (cursor == i) {
                    g.setColor(Color.BLUE);
                } else g.setColor(new Color(12, 12, 12, 170));
                g.fillRect(tempX, tempY, size, size);
                g.setColor(new Color(255, 255, 255, 255));
                if (i % 4 == 3) {
                    g.drawImage(Assets.summonerArt.get(e), tempX + size * 1 / 12, tempY + size * 1 / 12, size * 10 / 12, size * 10 / 12, null);
                    g.drawString("" + player.getOccupation().getOccupationalSkillsValue(arrayylmao[e]), tempX, tempY + width / 12);
                    e++;
                } else {
                    g.drawImage(Assets.spellArt.get(f), tempX + size * 1 / 12, tempY + size * 1 / 12, size * 10 / 12, size * 10 / 12, null);
                    f++;
                }


                if (i % 4 == 3) {
                    tempX += size * 3 / 2;
                } else {
                    //add barely anything
                    tempX += size * 7 / 6;
                }
                if (i % 4 == 2) {
                    tempX = resetX;
                    tempY += size * 7 / 6;
                }
            }

            //render descriptions
        }
        else if(player.getOccupation()instanceof Smasher){
            SkillsEnum arrayylmao[]={SkillsEnum.ONEHANDWEAP,SkillsEnum.TWOHANDWEAP,SkillsEnum.BRAWL};
            //render specific skills
            tempY+=2*size;
            tempX=resetX;
            for(int i=3;i<6;++i){
                if(cursor==i){g.setColor(Color.BLUE);}
                else g.setColor(new Color(12, 12, 12, 170));
                g.fillRect(tempX,tempY,width/12,width/12);
                g.drawImage(Assets.smasherArt.get(i-3),tempX+size*1/12,tempY+size*1/12,size*10/12,size*10/12,null);
                g.setFont(new Font("Arial", Font.PLAIN, 30*intY/10));
                g.setColor(Color.BLACK);
                g.drawString(""+player.getOccupation().getOccupationalSkillsValue(arrayylmao[i-3]),tempX,tempY+width /12);
                tempX+=width/6;
            }
            g.setColor(Color.BLACK);
            g.fillRect(width*1/4,height*2/3,width/2,height/6);
            if(cursor<3){}
            else{}

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
