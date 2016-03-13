package State.States.GameState;

import Model.Abilities.CommandsEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import State.State;
import View.ViewUtilities.Camera;
import View.Views.MapView;
import View.Views.MessageBox.DisplayMessage;

import java.awt.*;

/**
 * Created by Aidan on 3/12/2016.
 */
public class VehicleState extends State{

    private Camera camera;
    private MapView mapView;
    private boolean cameraMoving;
    
    private static Player player;


    public void render(Graphics g) {
        if (!cameraMoving) {
            camera.centerOnPlayer(player);
        }
        mapView.render(g, camera.getxOffset(), camera.getyOffset(), player.getLocation());

        DisplayMessage.render(g);
    }

}
