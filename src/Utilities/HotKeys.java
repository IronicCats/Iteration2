package Utilities;


/**
 * Created by Wimberley on 3/8/16.
 */
public class HotKeys {

    public static void adjustKey(int adjustKey, int newKey) {
        if(adjustKey == Settings.UP){
            Settings.UP = newKey;
        }
        else if(adjustKey == Settings.UP_RIGHT){
            Settings.UP_RIGHT = newKey;
        }
        else if(adjustKey == Settings.DOWN_RIGHT){
            Settings.DOWN_RIGHT = newKey;
        }
        else if(adjustKey == Settings.DOWN){
            Settings.DOWN = newKey;
        }
        else if(adjustKey == Settings.DOWN_LEFT){
            Settings.DOWN_LEFT = newKey;
        }
        else if(adjustKey == Settings.UP_LEFT){
            Settings.UP_LEFT = newKey;
        }
        else if(adjustKey == Settings.ATTACK){
            Settings.ATTACK = newKey;
        }
        else if(adjustKey == Settings.INTERACT){
            Settings.INTERACT = newKey;
        }
        else if(adjustKey == Settings.INVENTORY){
            Settings.INVENTORY = newKey;
        }
        else if(adjustKey == Settings.EQUIP){
            Settings.EQUIP = newKey;
        }
    }

}
