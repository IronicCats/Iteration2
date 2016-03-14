package View.Views;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Equippable;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.Item.Items.Takables.Usable;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Sneak;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Summoner;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Map.Map;
import Model.Stats.CharacterStats;
import Model.Stats.Stats;

import Utilities.ItemUtilities.ItemFactory;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class InventoryView implements Renderable, Observer {
    //private Map map;
    //int selector;
    Pack pack;
    Player player;
    ItemView itemView[] = new ItemView[16];

    //HashMap<Item,ItemView> playerItems;
    int width,height;
    double mX,mY;
    public InventoryView(Pack pack, Player player){
        this.pack=pack;
        this.player=player;

        //this.map = map;
        //selector=0;
        //map.addObserver(this);
        //for(Item item : pack.getItems()) {
        //    if (item != null) {
        //        playerItems.put(item, ItemFactory.makeAsset(item.getItemType(), item));
        //    }
        //}
        for (int i = 0; i < 16; ++i) itemView[i] = null;
    }

    public void setPlayer(Player player) {
        this.player = player;
        pack = player.getPack();
    }


    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, int s) {
        //System.out.println(s);
        width = Settings.GAMEWIDTH;
        height = Settings.GAMEHEIGHT;

        mX = ((double) Settings.GAMEWIDTH) / 800;
        mY = ((double) Settings.GAMEHEIGHT) / 600;
        //fill the base rectangle
        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(width / 10, height / 10, width * 4 / 5, height * 4 / 5);
        //making the title
        double incY=10*mY;
        int intY=((int)incY);
        g.setColor(new Color(255, 255, 255, 255));
        g.setFont(new Font("Arial", Font.PLAIN, 40*intY/10));
        FontMetrics fm = g.getFontMetrics();
        int totalWidth = (fm.stringWidth("Inventory"));
        int tempMove= (width-totalWidth)/2;
        g.drawString("Inventory",tempMove,height/10+40*intY/10);
        //fill out the itemview
        for (int i = 0; i < 16; ++i) {
            if (pack.get(i) == null) itemView[i] = null;
            else itemView[i] = ItemFactory.makeAsset(pack.get(i).getItemType(), pack.get(i));
        }

        //render the pack
        renderPack(g, s);
        //render the bottom right icons
        renderIcons(g,s);
        //render the bottom left descriptions
        renderDescriptions(g,s);
        //render the stats
        renderStats(g);
        renderItems();
    }

    public void renderPack(Graphics g, int s) {

        double ds = 64 * 4 / 5 * mX;
        int size = ((int) ds);
        double incY = 10 * mY;
        int intY = ((int) incY);
        double incX = 10 * mX;
        int intX = ((int) incX);
        int tempX = width / 2;
        int tempY = height / 2 - (4 * (size + intY)) / 2;


        g.setColor(new Color(12, 12, 12, 200));
        g.fillRect(tempX, tempY, 4 * (size + intX), (-2) * tempY + height);
        tempX += intX / 2;
        tempY += intY / 2;

        for (int i = 0; i < 16; ++i) {
            if (i != s) {
                g.drawImage(Assets.BOX2, tempX, tempY, size, size, null);
                // if(itemView[i]!=null)itemView[i].render(g,tempX,tempY,size,size);
                //g.drawString("ayy"+i,tempX,tempY);
                //tempX+=64+10;
            } else {
                g.drawImage(Assets.BOX, tempX, tempY, size, size, null);
            }
            if (itemView[i] != null) itemView[i].render(g, tempX, tempY, size, size);
            tempX += size + intX;
            if (i % 4 == 3) {
                tempY += size + intY;
                tempX = width / 2 + intX / 2;
            }
        }
        //System.out.print(mX+"   "+mY+" \n");
        //System.out.print(Settings.GAMEWIDTH+"   "+Settings.GAMEHEIGHT+" \n");
    }

    public void renderIcons(Graphics g, int s) {
        double ds=64*4/5*mX;
        int size= ((int)ds);
        size=size*3/4;
        double incY=10*mY;
        int intY=((int)incY);
        double incX=10*mX;
        int intX=((int)incX);
        int x=width*3/20;
        int y=height*31/40;
        int z=width*3/10;
        g.setFont(new Font("Arial", Font.PLAIN, 10*intY/10));
        g.setColor(new Color(12, 12, 12, 250));
        //g.fillRect(width/2,y, width*6/20, height*1/10);
        x=width/2;
        if(pack.get(s) instanceof Equippable){
            g.setColor(new Color(12, 12, 12, 250));
            g.fillOval(x,y,size,size);
            g.setColor(new Color(255, 255, 255, 250));
            g.drawString("Q",x+size/4,y+size/2);
            x+=size+intX;
            //add to y
        }
        if(pack.get(s) instanceof Usable){
            g.setColor(new Color(12, 12, 12, 250));
            g.fillOval(x,y,size,size);
            g.setColor(new Color(255, 255, 255, 250));
            g.drawString("Q",x+size/4,y+size/2);
            x+=size+intX;
        }
        if(pack.get(s) instanceof Usable || pack.get(s) instanceof Equippable){
            g.setColor(new Color(12, 12, 12, 250));
            g.fillOval(x,y,size,size);
            g.setColor(new Color(255, 255, 255, 250));
            g.drawString("D",x+size/4,y+size/2);
            x+=size+intX;
        }
        //escii
            g.setColor(new Color(12, 12, 12, 250));
            g.fillOval(x,y,size,size);
        g.setColor(new Color(255, 255, 255, 250));
            g.drawString("ESC",x+size/4,y+size/2);
            x+=size+intX;
        //inv
            g.setColor(new Color(12, 12, 12, 250));
            g.fillOval(x,y,size,size);
        g.setColor(new Color(255, 255, 255, 250));
            g.drawString("I",x+size/4,y+size/2);
            x+=size+intX;
        //equip
            g.setColor(new Color(12, 12, 12, 250));
            g.fillOval(x,y,size,size);
            g.setColor(new Color(255, 255, 255, 250));
            g.drawString("E",x+size/4,y+size/2);


    }
    public void renderDescriptions(Graphics g, int s) {
        if(pack.get(s)==null)return;
        int x=width*3/20;
        int y=height*31/40;
        double incY=10*mY;
        int intY=((int)incY);
        g.setColor(new Color(12, 12, 12, 250));
        g.fillRect(x,y, width*6/20, height*1/10);
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial", Font.PLAIN, 14*intY/10));
        y+=intY*1.5;
        g.drawString("Name: "+pack.get(s).getName(),x,y);y+=2*intY;
        g.drawString("Description: "+pack.get(s).getDescription(),x,y);y+=2*intY;
        if(pack.get(s) instanceof Weapon)g.drawString("Damage: "+((Weapon) pack.get(s)).getDamage(),x,y);
        else if(pack.get(s) instanceof Armor)g.drawString("Armor: "+((Armor) pack.get(s)).getDefense(),x,y);
    }

    public void renderStats(Graphics g) {
        double incX=10*mX;
        int intX=((int)incX);
        int x=width*3/20;
        int y=height*1/4;
        double incY=10*mY;
        int intY=((int)incY);
        //int add = (double)(5));
        g.setColor(new Color(12, 12, 12, 200));

        g.fillRect(x, y, width*6/20, height*5/10);
        x+=intX;
        CharacterStats temp = player.getStats();
        g.setColor(new Color(255,255,255));
        y+=4*intY;
        String t;
        if(player.getOccupation() instanceof Smasher)t="Smasher";
        else if(player.getOccupation() instanceof Sneak)t="Sneak";
        else t="Summoner";
        //
        g.setFont(new Font("Arial", Font.PLAIN, 40*intY/10));
        FontMetrics fm = g.getFontMetrics();
        int totalWidth = (fm.stringWidth("Stats"));
        int tempMove= x+(width*3/10-totalWidth)/2;

        //

        g.drawString("Stats",tempMove,y);y+=2*intY;
        g.setFont(new Font("Arial", Font.PLAIN, 18*intY/10));
        g.drawString("Lvl "+temp.getLevel()+" "+t,x,y);y+=2*intY;
        g.setFont(new Font("Arial", Font.PLAIN, 14*intY/10));
        g.drawString("Health: "+temp.getLife(),x,y);y+=2*intY;
        g.drawString("Mana: "+temp.getMana(),x,y);y+=2*intY;
        g.drawString("Strength: "+temp.getStrength(),x,y);y+=2*intY;
        g.drawString("Agility: "+temp.getAgility(),x,y);y+=2*intY;
        g.drawString("Intellect: "+temp.getIntellect(),x,y);y+=2*intY;
        g.drawString("Hardiness: "+temp.getHardiness(),x,y);y+=2*intY;
        g.drawString("Offensive Rating: "+temp.getOffensiveRating(),x,y);y+=2*intY;
        g.drawString("Defensive Rating: "+temp.getDefensiveRating(),x,y);y+=2*intY;
        g.drawString("Armor Rating: "+temp.getArmorRating(),x,y);y+=2*intY;
        g.drawString("Movement: "+temp.getMovement(),x,y);y+=2*intY;
        g.drawString("Money(Catnip): "+pack.getMoney(),x,y);y+=2*intY;


    }

    public void renderItems() {
        for (int i = 0; i < 16; ++i) {
            if (pack.get(i) != null) {

            }
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }


}
