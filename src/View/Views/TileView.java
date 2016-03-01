package View.Views;

import Model.GameObject.Item.Item;
import Model.Map.Tiles.Tile;
import Utitlies.Observer;

/**
 * Created by Aidan on 3/1/2016.
 */
public class TileView implements Observer {
    private Tile tile;

    public TileView(Tile tile) {
        this.tile = tile;
        tile.addObserver(this);
    }

    @Override
    public void update() {

    }

}
