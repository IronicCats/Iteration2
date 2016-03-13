package View.Views;

import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Utilities.Observer;
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

    } // end render

    public void renderSlots(Graphics g, int cursor) {

    } // end renderSlots

    public void renderIcons(Graphics g, int cursor) {

    } // end renderIcons

    public void renderDescriptions(Graphics g, int cursor) {

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
