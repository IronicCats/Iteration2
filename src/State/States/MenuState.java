package State.States;

import Controller.Controllers.MenuController;
import State.State;
import State.StatesEnum;
import View.Views.MenuView;

import java.awt.*;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class MenuState extends State {
        private MenuView menu;

        public MenuState() {
                setController(new MenuController(this));
        }

        public void switchState(StatesEnum state) {
                if(getState() != getLiveState(state)) {
                        System.out.println(state);
                        setState(state);
                }
                menu=new MenuView();

        }

        public void tick(){

        }

        public void render(Graphics g) {
                //menu.render(g);
        }

}
