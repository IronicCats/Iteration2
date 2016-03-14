package Model.GameObject.MobileObjects.Entities.AI;

import Model.Abilities.Abilities;
import Model.GameObject.MobileObjects.Entities.AI.AIController;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Summoner;
import Model.Map.Map;
import Utilities.AIUtilities.DirectionofTarget;
import Utilities.AIUtilities.DistanceFromFaceableTarget;
import Utilities.AbilitiesUtilities.checkAbilityRange;

/**
 * Created by Aidan on 3/7/2016.
 */

public class EnemyController extends AIController {

    HostileNPC enemy;
    protected static int lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);

    public EnemyController(Map map) {
        super(map);
    }

    public void setEnemy(HostileNPC enemy) {
        this.enemy = enemy;
    }

    @Override
    public void tick() {
        if (targetinView()) {
            followThenAttackinRange();
        } else {
            randomlyMoveinRange();
        }
    }

    @Override
    public void follow() {
        moveTo(target.getLocation());
        System.out.println("using enemy follow");
    }

    //TODO: add delay to attacks based off of speed
    public void followThenAttackinRange() {
        if (canFace()) {
            enemy.face(DirectionofTarget.getDir(enemy.getLocation(), target.getLocation()));
            if (enemy.getOccupation() instanceof Smasher) {
                if (DistanceFromFaceableTarget.calculate(enemy, target) == 1) {
                    if ((int) (System.currentTimeMillis() / 1000L) - lastProcessedTime >= 3) {
                        System.out.println("attacking");
                        lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);
                        enemy.attack(enemy.getAbilities().get(0));
                    }
                }
                else if (DistanceFromFaceableTarget.calculate(enemy, target) > 1) {
                        follow();
                    }
                } else if (enemy.getOccupation() instanceof Summoner) {
                    Abilities a = checkAbilityRange.check(enemy.getAbilities());
                    if (a.getRange() >= DistanceFromFaceableTarget.calculate(enemy, target)) {
                        //enemy.attack(a);
                    }
                }
            } else {
                follow();
            }
        }

        @Override
        public void update() {

        }

        @Override
        public void remove() {

        }
    }


