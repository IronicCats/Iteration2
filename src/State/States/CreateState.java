package State.States;

import Controller.Controllers.CreateController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Location;
import State.State;
import State.States.GameState.GameState;
import State.States.GameState.VehicleState;
import Utilities.MobileObjectUtilities.MobileObjectFactory;
import View.Views.CreateView;

import java.awt.*;

/**
 * Created by broskj on 3/13/16.
 */
public class CreateState extends State {
    private CreateView createView;

    public CreateState() {
        createView = new CreateView();
        setController(new CreateController(this));
    } // end constructor

    @Override
    public void tick() {
        return;
    }

    public void makeSelection(int s) {
        Player player;
        switch (s) {
            case 0:         /* smasher */
                player = MobileObjectFactory.makeSmasher();
                break;
            case 1:         /* sneak */
                player = MobileObjectFactory.makeSneak();
                break;
            case 2:         /* summoner */
                player = MobileObjectFactory.makeSummoner();
                break;
            default:
                player = new Player();
                break;
        }
        State.GAMESTATE.setPlayer(player);
        State.EQUIPMENTSTATE.setPlayer(player);
        State.INVENTORYSTATE.setPlayer(player);
        State.SKILLSSTATE.setPlayer(player);
        switchState(GAMESTATE);
    } // end makeSelection


    public void moveUp() {
        createView.previous();
    }

    public void moveDown() {
        createView.next();
    }

    public void render(Graphics g) {
        createView.render(g);
    }
} // end class CreateState
