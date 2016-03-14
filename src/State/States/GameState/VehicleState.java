package State.States.GameState;

import Controller.Controllers.VehicleController;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Vehicle;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import State.State;
import Utilities.MapUtilities.Navigation;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import Utilities.MobileObjectUtilities.MobileObjectFactory;
import Utilities.Settings;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;
import View.Views.MobileObjectView;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Aidan on 3/12/2016.
 */
public class VehicleState extends State{

    private Vehicle vehicle;
    private HashMap<MobileObject, MobileObjectView> mobileObjects;

    @Override
    public void tick() {
        for (MobileObject key : mobileObjects.keySet()) {
            key.tick();
        }
    }

    public void reinit() {
        this.vehicle.getDriver().deregister();
        if(this.vehicle.getPet() != null){
            this.vehicle.getPet().deregister();
        }
        mobileObjects = GAMESTATE.getMobileObjects();
        setController(new VehicleController(this));
        mobileObjects.remove(this.vehicle.getDriver());
        mobileObjects.remove(this.vehicle.getPet());
    } // end reinit

    public VehicleState(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.vehicle.getDriver().deregister();
        if(this.vehicle.getPet() != null){
            this.vehicle.getPet().deregister();
        }
        mobileObjects = GAMESTATE.getMobileObjects();
        setController(new VehicleController(this));
        mobileObjects.remove(this.vehicle.getDriver());
        mobileObjects.remove(this.vehicle.getPet());
    }

    public void unmount(){
        Location playerLoc = location();

        if(playerLoc != null) {
            vehicle.unmount();
            vehicle.getDriver().setLocation(playerLoc);
            vehicle.getDriver().setViewLocation(new ViewLocation(vehicle.getDriver().getX(), vehicle.getDriver().getY()));
            mobileObjects.put(vehicle.getDriver(), MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, vehicle.getDriver()));
            vehicle.getDriver().dismountOntoTile(playerLoc);
            if(this.vehicle.getPet() != null){
                Location petLoc = location();
                if(petLoc != null) {
                    vehicle.getPet().setLocation(petLoc);
                    vehicle.getPet().setViewLocation(new ViewLocation(vehicle.getDriver().getX(), vehicle.getDriver().getY()));
                    mobileObjects.put(vehicle.getPet(), MobileObjectFactory.makeAsset(MobileObjectEnum.DAVE_PET, vehicle.getPet()));
                    vehicle.getPet().dismountOntoTile(petLoc);
                    vehicle.getPet().setOwnership();
                }
            }
            State.switchState(State.GAMESTATE);
        }
        else{
            DisplayMessage.addMessage(new GameMessage("You cannot unmount here", 2));
        }
    }

    public void move(int degrees) {
        //If camera is moving then movement will be applied to camera, otherwise apply it to the player
       if (Navigation.checkMove(Location.newLocation(degrees, vehicle.getLocation()), vehicle.getMap(), vehicle) && vehicle.canMove()) { // returns if new location is walkable
            vehicle.move(degrees);
        } else {
            vehicle.face(degrees);
        }
    }

    @Override
    public void render(Graphics g) {
        GAMESTATE.getCamera().centerOnObject(vehicle);
        GAMESTATE.getMapView().render(g, GAMESTATE.getCamera().getxOffset(), GAMESTATE.getCamera().getyOffset(), vehicle.getLocation());
        DisplayMessage.render(g);
    }

    public Location location(){
        if(Navigation.checkMove(Location.newLocation(Settings.NORTH, vehicle.getLocation()), GAMESTATE.getMap(), this.vehicle.getDriver())){
            return Location.newLocation(Settings.NORTH, vehicle.getLocation());
        }
        else if(Navigation.checkMove(Location.newLocation(Settings.NE, vehicle.getLocation()), GAMESTATE.getMap(), this.vehicle.getDriver())){
            return Location.newLocation(Settings.NE, vehicle.getLocation());
        }
        else if(Navigation.checkMove(Location.newLocation(Settings.SE, vehicle.getLocation()), GAMESTATE.getMap(), this.vehicle.getDriver())){
            return Location.newLocation(Settings.SE, vehicle.getLocation());
        }
        else if(Navigation.checkMove(Location.newLocation(Settings.SOUTH, vehicle.getLocation()), GAMESTATE.getMap(), this.vehicle.getDriver())){
            return Location.newLocation(Settings.SOUTH, vehicle.getLocation());
        }
        else if(Navigation.checkMove(Location.newLocation(Settings.SW, vehicle.getLocation()), GAMESTATE.getMap(), this.vehicle.getDriver())){
            return Location.newLocation(Settings.SW, vehicle.getLocation());
        }
        else if(Navigation.checkMove(Location.newLocation(Settings.NW, vehicle.getLocation()), GAMESTATE.getMap(), this.vehicle.getDriver())){
            return Location.newLocation(Settings.NW, vehicle.getLocation());
        }
        else{
            return null;
        }
    }
}

