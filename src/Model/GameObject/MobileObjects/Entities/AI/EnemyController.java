package Model.GameObject.MobileObjects.Entities.AI;

import Model.Abilities.Abilities;
import Model.GameObject.MobileObjects.Entities.AI.AIController;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Summoner;
import Model.GameObject.MobileObjects.MobileObject;
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
    protected static int sneakingTime = (int) (System.currentTimeMillis() /  1000L);
    protected static int lastProcessedTime1 = (int) (System.currentTimeMillis() / 1000L);

    boolean imAttacking;
    MobileObject previousTarget;

    public EnemyController(Map map) {
        super(map);
        imAttacking = false;
    }

    public void setEnemy(HostileNPC enemy) {
        this.enemy = enemy;
    }

    @Override
    public void tick() {
        if(target != null) {
            if (!enemy.getSleep()) {
                if (targetinView()) {
                    followThenAttackinRange();
                }
                randomlyMoveinRange();
            }
        }
        else {
            if(((int) (System.currentTimeMillis() / 1000L) - lastProcessedTime >= 5) ){
                target = previousTarget;
                target.getStats().setMovement(target.getMovement() + 8);

                }
            randomlyMoveinRange();
        }
    }

    @Override
    public void follow() {
        moveTo(target.getLocation());
        System.out.println("using enemy follow");
    }

    public void followThenAttackinRange() {
        int temp = random.nextInt(enemy.getHostilityRating() * 10);
        if (temp == 1 || imAttacking) {
            imAttacking = true;
            if (canFace()) {
                enemy.face(DirectionofTarget.getDir(enemy.getLocation(), target.getLocation()));
                if (enemy.getOccupation() instanceof Smasher) {
                    if (DistanceFromFaceableTarget.calculate(enemy, target) == 1) {
                        if ((int) (System.currentTimeMillis() / 1000L) - lastProcessedTime >= enemy.getAbilities().get(0).getCooldown()) {
                            System.out.println("attacking");
                            lastProcessedTime = (int) (System.currentTimeMillis() / 1000L);
                            enemy.attack(enemy.getAbilities().get(0));
                        }
                    } else if (DistanceFromFaceableTarget.calculate(enemy, target) > 1) {
                        follow();
                    }
                } else if (enemy.getOccupation() instanceof Summoner) {
                    Abilities a = enemy.getAbilities().get(2);
                    if (a.getRange() >= DistanceFromFaceableTarget.calculate(enemy, target)) {
                        if ((int) (System.currentTimeMillis() / 1000L) - lastProcessedTime1 >= enemy.getAbilities().get(2).getCooldown()) {
                            lastProcessedTime1 = (int) (System.currentTimeMillis() / 1000L);
                            enemy.attack(a);
                        }
                    }
                }
                else {
                    follow();
                }
            }
        }
    }

    public void takeAwaytarget(){
        previousTarget = target;
        target = null;
        sneakingTime = (int) (System.currentTimeMillis() / 1000L);
    }

        @Override
        public void update() {

        }

        @Override
        public void remove() {

        }
    }


