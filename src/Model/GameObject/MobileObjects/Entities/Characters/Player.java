package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Stats.PlayerStats;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;

/**
 * Created by Wimberley on 3/3/16.
 */

/*
This is the player class. This class contains everything the player controlled
character will need.
 */
public class Player extends Entity{

    // Player inventory needed
    public Player() {
        super();

    }
    public Player(Location location, PlayerStats stats, Occupation occupation){
        super(location, stats, occupation);
    }

    /*public void move(int x){
        if(!navigation.isMoving) {
            if (x == 0) {
                location.setDir(0);
                navigation.move(x);
            } else if (x == 1) {
                location.setDir(1);
                navigation.move(x);
            } else if (x == 2) {
                location.setDir(2);
                navigation.move(x);
            } else if (x == 3) {
                location.setDir(3);
                navigation.move(x);
            } else if (x == 4) {
                location.setDir(4);
                navigation.move(x);
            } else if (x == 5) {
                location.setDir(5);
                navigation.move(x);
            } else if (x == 6) {
                location.setDir(6);
                navigation.move(x);
            } else if (x == 7) {
                location.setDir(7);
                navigation.move(x);
            }
        }
    }*/
}
