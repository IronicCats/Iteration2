package View.Views;

import Model.Map.Map;
import Utilities.Utilities;
import Utilities.*;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;



/**
 * Created by Dartyx on 3/6/2016.
 */
public class EquipmentView implements Renderable, Observer {

    private Map map;

    public EquipmentView(Map map){
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