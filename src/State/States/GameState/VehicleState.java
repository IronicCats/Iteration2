package State.States.GameState;

import Controller.Controllers.VehicleController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Location;
import Model.Map.Map;
import State.State;
import Utilities.MapUtilities.Navigation;
import Utilities.Settings;
import View.ViewUtilities.Camera;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MapView;
import Model.Map.Map;

import java.awt.*;

/**
 * Created by Aidan on 3/12/2016.
 */
public class VehicleState extends State{

    private Vehicle vehicle;

    @Override
    public void tick() {
        State.GAMESTATE.tick();
    }

    public VehicleState(Vehicle vehicle) {
        this.vehicle = vehicle;
        setController(new VehicleController(this));
    }

    public void unmount(){
        vehicle.unmount();
        State.switchState(State.GAMESTATE);
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
}

