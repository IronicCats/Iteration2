package View.Views;

import Model.Map.Map;
import Utilities.Observer;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class InventoryView implements Renderable, Observer {
    private Map map;

    public InventoryView(Map map){
        this.map = map;

        map.addObserver(this);
    }

    @Override
    public void render(Graphics g) {

    }

    public void render() {

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }

}
