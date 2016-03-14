package View.Views;

import Utilities.Settings;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by broskj on 3/13/16.
 */
public class CreateView implements Renderable {
    private int width, height;
    private String[] occupations = {"Smasher", "Sneak", "Summoner"};
    private int cursor;

    public CreateView() {
        width = Settings.GAMEWIDTH;
        height = Settings.GAMEHEIGHT;
        cursor = 0;
    } // end constructor

    @Override
    public void render(Graphics g) {
        width = Settings.GAMEWIDTH;
        height = Settings.GAMEHEIGHT;
        //g.drawImage(Assets.background, 0, 0, 800, 600, null);
        g.setColor(new Color(12, 12, 12, 130));
        g.fillRect(0, 0, width, height);
        g.drawImage(Assets.BACK, 0, 0, width, height, null);
        g.drawImage(Assets.LEFT, 0, height * 1 / 5, width * 7 / 24, height * 4 / 5, null);
        g.drawImage(Assets.RIGHT, width * 17 / 24, height * 1 / 5, width * 7 / 24, height * 4 / 5, null);
        for (int i = 0; i < occupations.length; ++i) {
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            FontMetrics fm = g.getFontMetrics();
            int totalWidth = (fm.stringWidth(occupations[i]));
            int x = (width - totalWidth) / 2;
            int y = 50 + (height / 2) - 100 + 120 * i;

            if (i == cursor) {
                g.setColor(new Color(149, 165, 166, 175));
                g.fillRect(x, y - fm.getHeight() + (fm.getHeight() / 4), totalWidth, fm.getHeight());
                g.setColor(new Color(243, 156, 18));

            } else {
                g.setColor(new Color(149, 165, 166, 175));
                g.fillRect(x, y - fm.getHeight() + (fm.getHeight() / 4), totalWidth, fm.getHeight());
                g.setColor(new Color(231, 76, 60));
            }
            g.drawString(occupations[i], x, y);

        }

        g.setFont(new Font("Papyrus", Font.PLAIN, 100));
        g.setColor(new Color(255, 255, 255));
        FontMetrics fm = g.getFontMetrics();
        g.drawString("Occupation", width / 2 - fm.stringWidth("Occupation") / 2, 25 + fm.getHeight() / 2);
    } // end render


    public void next() {
        ++cursor;
        if (cursor > occupations.length - 1) {
            cursor = 0;
        }
    } // end next

    public void previous() {
        --cursor;
        if (cursor < 0) {
            cursor = occupations.length - 1;
        }
        System.out.println("made it");
    } // end previous

    public void render() {

    }
} // end class CreateView
