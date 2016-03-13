package View.Views;

import Model.GameObject.Item.Items.Takable;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Equippable;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.Item.Items.Takables.Usable;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Sneak;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Equipment;
import Model.Inventory.EquipmentSlotEnum;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Map.Map;
import Model.Stats.CharacterStats;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;


/**
 * Created by Dartyx on 3/6/2016.
 */
public class EquipmentView implements Renderable, Observer {


    Inventory inventory;
    Player player;
    Equipment equipment;
    CharacterStats stats;
    ItemView equipView[] = new ItemView[12];


    int width,height;
    double mX,mY;
    public EquipmentView(Player player) {
        this.player = player;
        this.inventory=player.getInventory();
        this.equipment=inventory.getEquipment();
        stats = player.getStats();
        stats.addObserver(this);
    }

    @Override
    public void render(Graphics g) {

    }

    public void render() {

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
        int totalWidth = (fm.stringWidth("Equipment"));
        int tempMove= (width-totalWidth)/2;
        g.drawString("Equipment",tempMove,height/10+40*intY/10);
        //fill out the itemview
        //MAINHAND, OFFHAND, HEAD, CHEST, LEGS, GLOVES, BOOTS, SHIELD, ACCESSORY1, ACCESSORY2

        if (equipment.getSlot(EquipmentSlotEnum.MAINHAND) == null) equipView[3] = null;
            else equipView[3] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.MAINHAND).getItemType(), equipment.getSlot(EquipmentSlotEnum.MAINHAND));

        if (equipment.getSlot(EquipmentSlotEnum.OFFHAND) == null&&equipment.getSlot(EquipmentSlotEnum.SHIELD) == null) equipView[5] = null;
            else if(equipment.getSlot(EquipmentSlotEnum.OFFHAND) != null)equipView[5] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.OFFHAND).getItemType(), equipment.getSlot(EquipmentSlotEnum.OFFHAND));
            else if(equipment.getSlot(EquipmentSlotEnum.OFFHAND) == null)equipView[5] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.SHIELD).getItemType(), equipment.getSlot(EquipmentSlotEnum.SHIELD));

        if (equipment.getSlot(EquipmentSlotEnum.HEAD) == null) equipView[1] = null;
            else equipView[1] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.HEAD).getItemType(), equipment.getSlot(EquipmentSlotEnum.HEAD));

        if (equipment.getSlot(EquipmentSlotEnum.CHEST) == null) equipView[4] = null;
            else equipView[4] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.CHEST).getItemType(), equipment.getSlot(EquipmentSlotEnum.CHEST));

        if (equipment.getSlot(EquipmentSlotEnum.LEGS) == null) equipView[7] = null;
            else equipView[7] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.LEGS).getItemType(), equipment.getSlot(EquipmentSlotEnum.LEGS));

        if (equipment.getSlot(EquipmentSlotEnum.GLOVES) == null) equipView[6] = null;
            else equipView[6] = equipView[8]=ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.GLOVES).getItemType(), equipment.getSlot(EquipmentSlotEnum.GLOVES));

        if (equipment.getSlot(EquipmentSlotEnum.BOOTS) == null) equipView[10] = null;
            else equipView[10] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.BOOTS).getItemType(), equipment.getSlot(EquipmentSlotEnum.BOOTS));

        if (equipment.getSlot(EquipmentSlotEnum.ACCESSORY1) == null) equipView[9] = null;
            else equipView[9] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.ACCESSORY1).getItemType(), equipment.getSlot(EquipmentSlotEnum.ACCESSORY1));

        if (equipment.getSlot(EquipmentSlotEnum.ACCESSORY2) == null) equipView[11] = null;
            else equipView[11] = ItemFactory.makeAsset(equipment.getSlot(EquipmentSlotEnum.ACCESSORY2).getItemType(), equipment.getSlot(EquipmentSlotEnum.ACCESSORY2));




        //render the pack
        renderSlots(g, s);
        //render the bottom right icons
        renderIcons(g,s);
        //render the bottom left descriptions
        renderDescriptions(g,s);
        //render the stats
        renderStats(g);
        //renderEquipment(g,s);
    }
    public void renderSlots(Graphics g, int s){
        double ds = 64 * 4 / 5 * mX;
        int size = ((int) ds);
        double incY = 10 * mY;
        int intY = ((int) incY);
        double incX = 10 * mX;
        int intX = ((int) incX);
        int tempX = width / 2;//+ intX / 2;i
        int tempY = height / 2 - (4 * (size + intY)) / 2;


        g.setColor(new Color(12, 12, 12, 200));
        g.fillRect(tempX, tempY, 4 * (size + intX), (-2) * tempY + height);
        tempX+= intX+size/2;
        tempY += intY / 2;
        for(int i=0;i<12;++i){
            if((i!=0) && (i!=2)){
                if (i != s) {
                    g.drawImage(Assets.BOX2, tempX, tempY, size, size, null);
                } else {
                    g.drawImage(Assets.BOX, tempX, tempY, size, size, null);
                }
            }
            if (equipView[i] != null) equipView[i].render(g, tempX, tempY, size, size);
            tempX += size + intX;
            if (i % 3 == 2) {
                tempY += size + intY;
                tempX = width / 2 + intX +size/2;
            }
        }
    }
    public void renderIcons(Graphics g, int s){
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
        if(equipView[s]!=null){
            g.setColor(new Color(12, 12, 12, 250));
            g.fillOval(x,y,size,size);
            g.setColor(new Color(255, 255, 255, 250));
            g.drawString("Q",x+size/4,y+size/2);
            x+=size+intX;
            //add to y

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
        g.drawString("E",x+size/4,y+size/2);
        x+=size+intX;
        //equip
        g.setColor(new Color(12, 12, 12, 250));
        g.fillOval(x,y,size,size);
        g.setColor(new Color(255, 255, 255, 250));
        g.drawString("I",x+size/4,y+size/2);
    }
    public void renderDescriptions(Graphics g, int s){
        Takable temp=null;
        switch(s){
            case 1:
                temp=equipment.getSlot(EquipmentSlotEnum.HEAD);
                break;
            case 3:
                temp=equipment.getSlot(EquipmentSlotEnum.MAINHAND);
                break;
            case 4:
                temp=equipment.getSlot(EquipmentSlotEnum.CHEST);
                break;
            case 5:
                temp=equipment.getSlot(EquipmentSlotEnum.OFFHAND);
                if(temp==null)temp=equipment.getSlot(EquipmentSlotEnum.SHIELD);
                break;
            case 6:
                temp=equipment.getSlot(EquipmentSlotEnum.GLOVES);
                break;
            case 7:
                temp=equipment.getSlot(EquipmentSlotEnum.HEAD);
                break;
            case 8:
                temp=equipment.getSlot(EquipmentSlotEnum.GLOVES);
                break;
            case 9:
                temp=equipment.getSlot(EquipmentSlotEnum.ACCESSORY1);
                break;
            case 10:
                temp=equipment.getSlot(EquipmentSlotEnum.BOOTS);
                break;
            case 11:
                temp=equipment.getSlot(EquipmentSlotEnum.ACCESSORY2);
                break;
            default:
                break;
        }
        if (temp != null) {
            int x=width*3/20;
            int y=height*31/40;
            double incY=10*mY;
            int intY=((int)incY);
            g.setColor(new Color(12, 12, 12, 250));
            g.fillRect(x,y, width*6/20, height*1/10);
            g.setColor(new Color(255,255,255));
            g.setFont(new Font("Arial", Font.PLAIN, 14*intY/10));
            y+=intY*1.5;
            g.drawString("Name: "+temp.getName(),x,y);y+=2*intY;
            g.drawString("Desc: "+temp.getDescription(),x,y);y+=2*intY;
            if(temp instanceof Weapon)g.drawString("Damage: "+((Weapon) temp).getDamage(),x,y);
            else if(temp instanceof Armor)g.drawString("Armor: "+((Armor) temp).getDefense(),x,y);
        }
    }

    public void renderStats(Graphics g){
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
        g.drawString(""+t,x,y);y+=2*intY;
        g.setFont(new Font("Arial", Font.PLAIN, 14*intY/10));
        g.drawString("Health: "+stats.getLife(),x,y);y+=2*intY;
        g.drawString("Mana: "+stats.getMana(),x,y);y+=2*intY;
        g.drawString("Strength: "+stats.getStrength(),x,y);y+=2*intY;
        g.drawString("Agility: "+stats.getAgility(),x,y);y+=2*intY;
        g.drawString("Intellect: "+stats.getIntellect(),x,y);y+=2*intY;
        g.drawString("Hardiness: "+stats.getHardiness(),x,y);y+=2*intY;
        g.drawString("Offensive Rating: "+stats.getOffensiveRating(),x,y);y+=2*intY;
        g.drawString("Defensive Rating: "+stats.getDefensiveRating(),x,y);y+=2*intY;
        g.drawString("Armor Rating: "+stats.getArmorRating(),x,y);y+=2*intY;
    }
    public void renderEquipment(Graphics g, int s){

    }
    @Override
    public void update() {
        System.out.println("update called");
        stats = player.getStats();
    }

    @Override
    public void remove() {

    }

}
