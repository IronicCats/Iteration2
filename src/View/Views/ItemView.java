package View.Views;

import Model.GameObject.Item.Item;
import Utitlies.Observer;


/**
 * Created by Joshua Kegley on 2/29/2016.
 */
public class ItemView implements Observer {

    private Item item;

    public ItemView(Item item) {
        this.item = item;
        item.addObserver(this);
    }

    @Override
    public void update() {

    }
}
