package View.Views.MessageBox;

import View.ViewUtilities.Renderable;

import java.awt.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Joshua Kegley on 3/6/2016.
 */
public class DisplayMessage implements Renderable {

    public LinkedBlockingDeque<GameMessage> messages;
    public DisplayMessage() {messages = new LinkedBlockingDeque<>(); }

    public void addMessage(GameMessage message){
        messages.add(message);
    }

    @Override
    public void render(Graphics g) {

    }
}
