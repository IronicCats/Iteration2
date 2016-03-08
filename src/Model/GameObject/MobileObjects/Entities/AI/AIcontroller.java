package Model.GameObject.MobileObjects.Entities.AI;

import Model.Map.Tile;
import Model.Tickable;
import Utilities.AIUtilities.Astar;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/6/2016.
 */
    public abstract class AIcontroller implements Tickable {

       /* private Astar astar;

        public AIcontroller(Astar astar){
            this.astar = astar;
        }

        public void move(Tile startTile, Tile endTile){

            ArrayList<Tile> path = new ArrayList<Tile>();
            path = astar.Findpath(startTile,endTile);


        }

        @Override
        public void tick() {

        }
        */
}
