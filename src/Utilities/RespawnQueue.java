package Utilities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Map.Map;
import Model.Tickable;

import java.util.ArrayList;

/**
 * Created by broskj on 3/12/16.
 */
public class RespawnQueue implements Tickable{
    private Map map;
    private static ArrayList<MobileObject> queue;
    protected static int lastProcessedTime;

    public RespawnQueue() {
        map = null;
        queue = new ArrayList<>();
        lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);
    }

    public void registerMap(Map map) {
        this.map = map;
    } // end registerMap

    public void push(MobileObject object) {
        queue.add(object);
    } // end push

    public void pop() {
        map.respawn(queue.get(0));
        queue.remove(0);
    } // end pop

    @Override
    public void tick() {
        if(queue.size() > 0) {
            pop();
        }
    } // end tick
} // end class RespawnQueue
