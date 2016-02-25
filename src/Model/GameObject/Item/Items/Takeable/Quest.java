package Model.GameObject.Item.Items.Takeable;

import Model.GameObject.Item.ItemEnum;
import Model.Location;
import Model.Requirement;
import Model.Effect;

/**
 * Created by Wimberley on 2/25/16.
 */
public class Quest extends Takeable{

    Quest(ItemEnum id, String name, String description, Location location, int imageId, Requirement requirements, Effect []effects){
        super(id, name, description, location, imageId, requirements, effects);
    }
}
