package View.Views.MessageBox;

import Model.Game;

import java.awt.*;

/**
 * Created by Joshua Kegley on 3/6/2016.
 */
public class GameMessage {
    String message;
    public GameMessage(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
