package Model.GameObject.MobileObjects;

import Utilities.Utilities;

/**
 * Created by Aidan on 3/6/2016.
 */
public class ViewLocation {

    private float x, y;

    public ViewLocation(int x, int y) {
        this.x = Utilities.calculateTileCenterXLocation(x, y);
        this.y = Utilities.calculateTileCenterYLocation(x, y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "ViewLocation{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
