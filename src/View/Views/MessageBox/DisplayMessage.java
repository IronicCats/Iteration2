package View.Views.MessageBox;

import Utilities.Settings;
import View.ViewUtilities.Renderable;
import View.ViewUtilities.ViewSettings;

import java.awt.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Joshua Kegley on 3/6/2016.
 */
public class DisplayMessage  {

    private static ArrayList<GameMessage> messages = new ArrayList<>();
    private static GameMessage currentMessage;
    protected static int lastProcessedTime = (int)(System.currentTimeMillis() / 1000L);


    public static void addMessage(GameMessage message){
        messages.add(message);
    }

    private static void getNextMessage() {
        if(!messages.isEmpty()) {
            currentMessage = messages.remove(0);
        }else {
            currentMessage = null;
        }
    }

    public static void render(Graphics g) {
        //System.out.println(System.currentTimeMillis() / 1000L);
        if(currentMessage != null) {
            g.setFont(ViewSettings.messageFont);
            g.setColor(Color.WHITE);
            g.drawString(currentMessage.getMessage(), 0, Settings.GAMEHEIGHT - 15);
            if ((int) (System.currentTimeMillis() / 1000L) - lastProcessedTime >= currentMessage.seconds) {
                lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);
                getNextMessage();
            }

        }else {
            getNextMessage();
            lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);
        }

    }
}
