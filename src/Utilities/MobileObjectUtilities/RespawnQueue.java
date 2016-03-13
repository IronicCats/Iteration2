package Utilities.MobileObjectUtilities;

import Model.GameObject.MobileObjects.Entities.Characters.Character;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 3/13/2016.
 */
public class RespawnQueue {

    private static ArrayList<Character> characters = new ArrayList<>();
    private static Character currentCharacter;
    protected static int lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);


    public static void addCharacter(Character character) {
        characters.add(character);
    }

    private static void getNextCharacter() {
        if (!characters.isEmpty()) {
            currentCharacter = characters.remove(0);
        } else {
            currentCharacter = null;
        }
    }

    public static boolean isInQueue(Character c) {
        return characters.contains(c);
    }

    public static void tick() {
        if (currentCharacter != null) {
            if ((int) (System.currentTimeMillis() / 1000L) - lastProcessedTime >= 10) {
                lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);
                if(currentCharacter.revive()) {
                    getNextCharacter();
                }

            }

        } else {
            getNextCharacter();
            lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);
        }

    }
}
