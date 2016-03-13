package Model.GameObject.AreaEffect;

import Model.GameObject.GameObject;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Utilities.Utilities;

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
        mobileObject.setLocation(endLocation);
        mobileObject.setViewLocation(new ViewLocation(endLocation.getX(), endLocation.getY()));
    }
}
