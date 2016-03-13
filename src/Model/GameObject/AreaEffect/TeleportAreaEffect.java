package Model.GameObject.AreaEffect;

import Model.GameObject.GameObject;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;

/**
 * Created by mazumderm on 3/12/2016.
 */
public class TeleportAreaEffect extends GameObject {

    Location endLocation;

    //constructor
    public TeleportAreaEffect(Location location, Location endLocation){
        super(location);
        this.endLocation = endLocation;
    }

    public Location getEndLocation(){
        return this.endLocation;
    }
    public void teleportPlayer(MobileObject mobileObject){

    }
}
