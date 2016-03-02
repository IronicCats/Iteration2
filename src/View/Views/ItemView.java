package View.Views;

import Model.GameObject.Item.Item;
import Utitlies.Observer;
import View.ViewUtilities.Renderable;

import java.awt.*;


/**
 * Created by Joshua Kegley on 2/29/2016.
 */
public class ItemView implements Observer, Renderable {

    private Item item;

    public ItemView(Item item) {
        this.item = item;
        item.addObserver(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        //draw it
    }
}
