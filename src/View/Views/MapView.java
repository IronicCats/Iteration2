package View.Views;

import Model.Map.Map;
import View.ViewUtilities.Renderable;

import java.awt.*;
import Utilities.Observer;

/**
 * Created by Aidan on 3/2/2016.
 */

public class MapView implements Observer, Renderable {

    private Map map;

    public MapView(Map map){
        this.map = map;
        map.addObserver(this);
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }

}
