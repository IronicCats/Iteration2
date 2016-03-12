package View.Views;

import Model.Inventory.Pack;
import Model.Map.Map;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by broskj on 3/9/16.
 */
public class TradeView implements Renderable, Observer {
    private Map map;
    int width, height;
    Pack playerPack;
    Pack shopPack;
    double mX, mY;
    ItemView itemView[] = new ItemView[32];

    public TradeView(Pack playerPack, Pack shopPack) {
        for (int i = 0; i < 32; ++i) itemView[i] = null;
        this.playerPack = playerPack;
        this.shopPack = shopPack;
        //this.map = map;
        //map.addObserver(this);
    } // end constructor

    @Override
    public void render(Graphics g) {

    } // end render

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

        //making the views
        for (int i = 0; i < 32; ++i) {
            if (i < 16) {
                if (playerPack.get(i) == null) itemView[i] = null;
                else itemView[i] = ItemFactory.makeAsset(playerPack.get(i).getItemType(), playerPack.get(i));
            } else {
                if (shopPack.get(i - 16) == null) itemView[i] = null;
                else itemView[i] = ItemFactory.makeAsset(shopPack.get(i - 16).getItemType(), shopPack.get(i - 16));
            }
        }

        //render the pack
        renderPackOne(g, s);
        renderPackTwo(g, s);
        //render the bottom right icons
        renderIcons(g, s);
        //render the bottom left descriptions
        renderDescriptions(g, s);

    }

    public void renderPackTwo(Graphics g, int s) {

        double ds = 64 * 4 / 5 * mX;
        int size = ((int) ds);
        double incY = 10 * mY;
        int intY = ((int) incY);
        double incX = 10 * mX;
        int intX = ((int) incX);
        int tempX = width / 2 + intX;
        int tempY = height / 2 - (4 * (size + intY)) / 2;


        g.setColor(new Color(12, 12, 12, 200));
        g.fillRect(tempX, tempY, 4 * (size + intX), (-2) * tempY + height);
        tempX += intX / 2;
        tempY += intY / 2;

        for (int i = 16; i < 32; ++i) {
            if (i != s) {
                g.drawImage(Assets.BOX2, tempX, tempY, size, size, null);
                //g.drawString("ayy"+i,tempX,tempY);
                //tempX+=64+10;
            } else {
                g.drawImage(Assets.BOX, tempX, tempY, size, size, null);
                g.setColor(new Color(200, 200, 200, 200));
                g.drawString("Shop:" + (i - 16), tempX, tempY);
            }
            if (itemView[i] != null) itemView[i].render(g, tempX, tempY, size, size);
            tempX += size + intX;
            if (i % 4 == 3) {
                tempY += size + intY;
                tempX = width / 2 + 3 * intX / 2;
            }
        }
    }

    public void renderPackOne(Graphics g, int s) {

        double ds = 64 * 4 / 5 * mX;
        int size = ((int) ds);
        double incY = 10 * mY;
        int intY = ((int) incY);
        double incX = 10 * mX;
        int intX = ((int) incX);
        int tempX = width / 2 - 4 * (size + intX) - intX;
        int tempY = height / 2 - (4 * (size + intY)) / 2;


        g.setColor(new Color(12, 12, 12, 200));
        g.fillRect(tempX, tempY, 4 * (size + intX), (-2) * tempY + height);
        tempX += intX / 2;
        tempY += intY / 2;

        for (int i = 0; i < 16; ++i) {
            if (i != s) {
                g.drawImage(Assets.BOX2, tempX, tempY, size, size, null);
                //g.drawString("ayy"+i,tempX,tempY);
                //tempX+=64+10;
            } else {
                g.drawImage(Assets.BOX, tempX, tempY, size, size, null);
                g.setColor(new Color(200, 200, 200, 200));
                g.drawString("User:" + i, tempX, tempY);
            }
            if (itemView[i] != null) itemView[i].render(g, tempX, tempY, size, size);
            tempX += size + intX;
            if (i % 4 == 3) {
                tempY += size + intY;
                tempX = width / 2 - 4 * (size + intX) - intX / 2;
            }
        }
    }

    public void renderIcons(Graphics g, int s) {

    }

    public void renderDescriptions(Graphics g, int s) {
        g.setColor(new Color(12, 12, 12, 250));
        g.fillRect(width * 3 / 20, height * 31 / 40, width * 6 / 20, height * 1 / 10);
    }


    @Override
    public void update() {

    } // end update

    @Override
    public void remove() {

    } // end remove
}
