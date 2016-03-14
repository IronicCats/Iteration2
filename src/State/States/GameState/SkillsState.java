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
    public int set1,set2,set3;

    public SkillsState() {
        set1=set2=set3=0;
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
    public void set(int s){
        switch (occupation.getName()) {
            case "Summoner":
                if (cursor ==4) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(3);
                        set1=0;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(3);
                        set2=0;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(3);
                        set3=0;
                    }

                }
                else if (cursor == 5) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(4);
                        set1=1;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(4);
                        set2=1;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(4);
                        set3=1;
                    }
                }
                else if (cursor == 6) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(5);
                        set1=2;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(5);
                        set2=2;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(5);
                        set3=2;
                    }
                }
                else if (cursor == 8) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(0);
                        set1=3;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(0);
                        set2=3;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(0);
                        set3=3;
                    }
                }
                else if (cursor == 9) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(1);
                        set1=4;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(1);
                        set2=4;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(1);
                        set2=4;
                    }
                }
                else if (cursor == 10) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(2);
                        set1=5;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(2);
                        set2=5;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(2);
                        set3=5;
                    }
                }

                break;
            case "Sneak":
                if (cursor ==3) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(1);
                        set1=0;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(1);
                        set2=0;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(1);
                        set3=0;
                    }
                }
                else if (cursor == 4) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(2);
                        set1=1;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(2);
                        set2=1;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(2);
                        set3=1;
                    }
                }
                else if (cursor == 6) {
                    if(s==1){
                        player.ability1=occupation.getAbilityAt(0);
                        set1=3;
                    }
                    else if(s==2){
                        player.ability2=occupation.getAbilityAt(0);
                        set2=3;
                    }
                    else if(s==3){
                        player.ability3=occupation.getAbilityAt(0);
                        set3=3;
                    }
                }



                break;
            default:
                break;
        }
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

                if(occupation.getUnassignedSkillPoints()>0) {
                    switch (occupation.getName()) {
                        case "Smasher":
                            if (cursor ==0) {
                               occupation.incrementSkill(SkillsEnum.BINDWOUNDS);
                            }
                            else if (cursor == 1) {
                                occupation.incrementSkill(SkillsEnum.BARGAIN);
                            }
                            else if (cursor == 2) {
                                occupation.incrementSkill(SkillsEnum.OBSERVATION);
                            }
                            else if (cursor == 3) {
                                occupation.incrementSkill(SkillsEnum.ONEHANDWEAP);
                            }
                            else if (cursor == 4) {
                                occupation.incrementSkill(SkillsEnum.TWOHANDWEAP);
                            }
                            else if (cursor == 5) {
                                occupation.incrementSkill(SkillsEnum.BRAWL);
                            }


                            break;
                        case "Summoner":
                            if (cursor ==0) {
                                occupation.incrementSkill(SkillsEnum.BINDWOUNDS);
                            }
                            else if (cursor == 1) {
                                occupation.incrementSkill(SkillsEnum.BARGAIN);
                            }
                            else if (cursor == 2) {
                                occupation.incrementSkill(SkillsEnum.OBSERVATION);
                            }
                            else if (cursor == 3) {
                                occupation.incrementSkill(SkillsEnum.BANE);
                            }
                            else if (cursor == 7) {
                                occupation.incrementSkill(SkillsEnum.BOON);
                            }
                            else if (cursor == 11) {
                                occupation.incrementSkill(SkillsEnum.ENCHANT);
                            }
                            else if (cursor == 15) {
                                occupation.incrementSkill(SkillsEnum.STAFF);
                            }

                            break;
                        case "Sneak":
                            if (cursor ==0) {
                                occupation.incrementSkill(SkillsEnum.BINDWOUNDS);
                            }
                            else if (cursor == 1) {
                                occupation.incrementSkill(SkillsEnum.BARGAIN);
                            }
                            else if (cursor == 2) {
                                occupation.incrementSkill(SkillsEnum.OBSERVATION);
                            }
                            else if (cursor == 3) {
                                occupation.incrementSkill(SkillsEnum.DRTRAP);
                            }
                            else if (cursor == 4) {
                                occupation.incrementSkill(SkillsEnum.CREEP);
                            }
                            else if (cursor == 5) {
                                occupation.incrementSkill(SkillsEnum.RANGEWEAP);
                            }
                            else if (cursor == 6) {
                                occupation.incrementSkill(SkillsEnum.PICKPOCK);
                            }


                            break;
                        default:
                            break;
                    }
                }




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
                if (cursor == 0){
                    cursor += 15;
                }   else if(cursor == 1 || cursor == 2) {
                    cursor+=11;
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
                if (cursor == 15) {
                    cursor -= 15;
                } else if(cursor == 12 || cursor == 13) {
                    cursor -= 11;
                } else if (cursor == 14) {
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
                        cursor == 11) {
                    cursor += 3;
                } else if(cursor == 15){

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
                        cursor == 14 ) {
                    cursor -= 3;
                } else if(cursor == 15){

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
