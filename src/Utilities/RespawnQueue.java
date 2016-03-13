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
    protected static int lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);

    public RespawnQueue() {
        map = null;
        queue = new ArrayList<>();
    }

    public void registerMap(Map map) {
        this.map = map;
    } // end registerMap

    public void push(MobileObject object) {
        System.out.println("push called");
        queue.add(object);
        lastProcessedTime = (int)System.currentTimeMillis();
    } // end push

    public void pop() {
        System.out.println("pop called");
        map.respawn(queue.get(0));
        queue.remove(0);
    } // end pop

    @Override
    public void tick() {
        if(queue.size() > 0) {
            if((int)(System.currentTimeMillis() / 1000L) - lastProcessedTime > 30)
                pop();
        }
    } // end tick
} // end class RespawnQueue
