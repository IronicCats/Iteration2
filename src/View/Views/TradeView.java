package View.Views;

import Model.Map.Map;
import Utilities.Observer;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by broskj on 3/9/16.
 */
public class TradeView implements Renderable, Observer{
    private Map map;

    public TradeView(Map map) {
        this.map = map;
        map.addObserver(this);
    } // end constructor

    @Override
    public void render(Graphics g) {

    } // end render

    @Override
    public void update() {

    } // end update

    @Override
    public void remove() {

    } // end remove
}
