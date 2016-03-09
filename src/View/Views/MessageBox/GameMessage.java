package View.Views.MessageBox;

import Model.Game;

import java.awt.*;

/**
 * Created by Joshua Kegley on 3/6/2016.
 */
public class GameMessage {
    String message;
    int seconds;
    public GameMessage(String message, int seconds) {
        this.message = message;
        this.seconds = seconds;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
