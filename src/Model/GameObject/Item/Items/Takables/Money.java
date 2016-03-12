package Model.GameObject.Item.Items.Takables;

import Model.Effects.Effect;
import Model.GameObject.Item.Items.Takable;
import Model.Location;
import Model.Requirement;

/**
 * Created by broskj on 3/11/16.
 */
public class Money extends Takable {
    private int quantity;

    public Money() {
        super();
        this.quantity = 0;
    } // end default constructor

    public Money(int quantity) {
        super();
        this.quantity = quantity;
    }

    public Money(int id, String name, String description, int value, Location location, Requirement requirement, Effect effect, int quantity) {
         super(id, name, description, value, location, requirement, effect);
        this.quantity = quantity;
    } // end constructor

    public int getQuantity() { return quantity; }
} // end class Money
