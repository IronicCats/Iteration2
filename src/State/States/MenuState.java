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
        private MenuView menuView;

        public MenuState() {
                menuView=new MenuView();
                setController(new MenuController(this));
        }

        public void switchState(StatesEnum state) {
                setState(state);
        }
        public void moveUp(){
                menuView.previous();
        }
        public void moveDown(){
                menuView.next();
        }
        public void tick(){
                System.out.println("MenuState tick");
        }

        public void render(Graphics g) {
                menuView.render(g);
        }

}
