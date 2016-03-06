package States.States;

import Controller.Controllers.MenuController;
import States.State;
import java.awt.*;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class MenuState extends State {

        public MenuState() {
                setController(new MenuController());
        }

        public void switchState() {

        }

        public void tick() {
        }

        public void render(Graphics g) {

        }

        @Override
        public void switchState(States state) {

        }
}
