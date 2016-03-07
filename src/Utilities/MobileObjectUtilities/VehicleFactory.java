package Utilities.MobileObjectUtilities;

import Model.GameObject.MobileObjects.Vehicle;
import View.Views.MobileObjectView;

/**
 * Created by Wimberley on 3/6/16.
 */
public class VehicleFactory {
    public static void  makeVehicle(MobileObjectEnum vehicleEnum) {
        switch (vehicleEnum) {
            case SADDLED_DOG:
            case SHEEP_VEHICLE:
            case LOW_RIDER:
            default:
                break; /* return null; */
        }
    } // end makeNPC

    public static MobileObjectView makeAsset(MobileObjectEnum vehicleEnum, Vehicle vehicle) {
        switch (vehicleEnum) {
            case SADDLED_DOG:
            case SHEEP_VEHICLE:
            case LOW_RIDER:
            default:
                break;
        }
        return null;
    } // end makeAsset
}
