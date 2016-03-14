package State.States;

import Controller.Controllers.CreateController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Location;
import State.State;
import State.States.GameState.GameState;
import Utilities.MobileObjectUtilities.MobileObjectFactory;
import View.Views.CreateView;

/**
 * Created by broskj on 3/13/16.
 */
public class CreateState extends State {
    private CreateView createView;
    private int cursor;

    public CreateState() {
        createView = new CreateView();
        setController(new CreateController(this));
        cursor = 0;
    } // end constructor

    @Override
    public void tick() {

    }

    public void up() {
        if(cursor == 0) {
            cursor = 2;
        } else {
            cursor--;
        }
    } // end up

    public void down() {
        if(cursor == 2) {
            cursor = 0;
        } else {
            cursor++;
        }
    } // end down

    public void makeSelection() {
        Player player;
        switch (cursor) {
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
    //public void moveUp() { createView.previous(); }
    //public void moveDown() { createView.next(); }
    //public void render(Graphics g) { createView.render(g); }
} // end class CreateState
