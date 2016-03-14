package State.States.GameState;

import Controller.Controllers.SkillsController;
import Model.Abilities.CommandsEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.SkillsEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import State.State;
import View.Views.SkillsView;

import java.awt.*;
import java.util.Map;

/**
 * Created by broskj on 3/13/16.
 *
 * Class and cursor number for skill tree
 *    SUMMONER/SNEAK          SMASHER
 *      0   1   2            0   1   2
 *        3   4              3   4   5
 *        5   6
 */
public class SkillsState extends State {
    SkillsView skillsView;
    Player player;
    Occupation occupation;
    Map<SkillsEnum, Integer> basicSkills, occupationalSkills;
    int cursor;

    public SkillsState() {
        setController(new SkillsController(this));

        this.player = State.GAMESTATE.getPlayer();
        occupation = player.getOccupation();
        this.skillsView = new SkillsView(player);
        basicSkills = occupation.getBasicSkills();
        occupationalSkills = occupation.getOccupationalSkills();
        cursor = 0;
    } // end constructor

    public void setPlayer(Player player) {
        this.player = player;
        this.occupation = player.getOccupation();
        skillsView.setPlayer(player);
        basicSkills = occupation.getBasicSkills();
        occupationalSkills = occupation.getOccupationalSkills();
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        skillsView.render(g, cursor);
    } // end render

    public void executePlayerCommand(CommandsEnum command) {
        switch(command) {
            case up:
                up();
                break;
            case down:
                down();
                break;
            case left:
                left();
                break;
            case right:
                right();
                break;
            case interact:
                break;
            default:
                System.out.println("Don't send that command to the SkillsState");
                break;
        }
    } // end executePlayerCommand

    public void up() {
        switch(occupation.getName()) {
            case "Smasher":
                if(cursor > 2)
                    cursor -= 3;
                else
                    cursor += 3;
                break;
            case "Summoner":
                if (cursor == 0 || cursor == 1 || cursor == 2) {
                    cursor += 15;
                } else if (cursor == 3 || cursor == 4 || cursor == 5) {
                    cursor -= 3;
                } else {
                    cursor -= 4;
                }
                break;
            case "Sneak":
                if(cursor == 0 || cursor == 1)
                    cursor = 5;
                else if(cursor == 2)
                    cursor = 6;
                else if(cursor == 3 || cursor == 4)
                    cursor -= 3;
                else
                    cursor -= 2;
                break;
            default:
                break;
        }
    } // end up

    public void down() {
        switch(occupation.getName()) {
            case "Smasher":
                up();
                break;
            case "Summoner":
                if (cursor == 15 || cursor == 16 || cursor == 17) {
                    cursor -= 15;
                } else if (cursor == 18) {
                    cursor = 2;
                } else if (cursor == 0 || cursor == 1 || cursor == 2) {
                    cursor += 3;
                } else {
                    cursor += 4;
                }
                break;
            case "Sneak":
                if(cursor == 0 || cursor == 1)
                    cursor = 3;
                else if(cursor == 2)
                    cursor = 4;
                else if(cursor == 3 || cursor == 4)
                    cursor += 2;
                else
                    cursor -= 5;
                break;
            default:
                break;
        }
    } // end down

    public void left() {
        switch(occupation.getName()) {
            case "Smasher":
                if(cursor == 1 || cursor == 2 ||
                        cursor == 4 || cursor == 5)
                    cursor -= 1;
                else
                    cursor += 2;
                break;
            case "Summoner":
                if(cursor == 0) {
                    cursor = 2;
                } else if (cursor == 3 || cursor == 7 ||
                        cursor == 11 || cursor == 15) {
                    cursor += 3;
                } else {
                    cursor -= 1;
                }
                break;
            case "Sneak":
                if(cursor == 0)
                    cursor = 2;
                else if(cursor == 1 || cursor == 2 ||
                        cursor == 4 || cursor == 6)
                    cursor -= 1;
                else
                    cursor += 1;
                break;
            default:
                break;
        }
    } // end left

    public void right() {
        switch(occupation.getName()) {
            case "Smasher":
                if(cursor == 0 || cursor == 1 ||
                        cursor == 3 || cursor == 4)
                    cursor += 1;
                else
                    cursor -= 2;
                break;
            case "Summoner":
                if (cursor == 2) {
                    cursor = 0;
                } else if (cursor == 6 || cursor == 10 ||
                        cursor == 14 || cursor == 18) {
                    cursor -= 3;
                } else {
                    cursor += 1;
                }
                break;
            case "Sneak":
                if(cursor == 0 || cursor == 1 ||
                        cursor == 3 || cursor == 5)
                    cursor += 1;
                else if(cursor == 2)
                    cursor = 0;
                else
                    cursor -= 1;
                break;
            default:
                break;
        }
    } // end right



} // end class SkillsState
