package Utilities;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class Settings {
    public static int GAMEWIDTH = 800;
    public static int GAMEHEIGHT = 600;
    //TODO: WTF? NO?
    public static int MAPWIDTH;
    public static int MAPHEIGHT;
    //MAP WIDTH AND HEIGHT CAN CHANGE, SHOULD BE STATIC
    public static int TILEWIDTH = 64*2;
    public static int TILEHEIGHT = 56*2;
    public static int PLAYERWIDTH = TILEWIDTH/2;
    public static int PLAYERHEIGHT = TILEHEIGHT/2;
    public static int DEFAULTITEMHEIGHT = TILEHEIGHT/4;
    public static int DEFAULTITEMWIDTH = TILEWIDTH/4;

    public static int NORTH = 90;
    public static int NE = 45;
    public static int SE = 315;
    public static int SOUTH = 270;
    public static int SW = 225;
    public static int NW = 135;


    public static int UP_RIGHT = 105;
    public static int UP = 104;
    public static int UP_LEFT = 103;
    public static int DOWN_LEFT = 97;
    public static int DOWN = 98;
    public static int DOWN_RIGHT = 99;

    public static int TAB = 0x09;
    public static int ENTER = 10;
    public static int ESC = 0x1B;


    public static int SPACE = 0x20;
    public static int ATTACK = 0x20;
    public static int ONE = 0x31;
    public static int TWO = 0x32;
    public static int THREE = 0x33;
    public static int D = 0x44;
    public static int E = 69;
    public static int I = 0x49;
    public static int M = 0x4D;
    public static int Q = 81;
    public static int F = 70;
    public static boolean FOG = false;

    public static String TITLE = "Ironic Cats";
}
