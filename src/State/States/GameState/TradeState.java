package State.States.GameState;

import Controller.Controllers.TradeController;
import Model.Abilities.BargainAbility;
import Model.Abilities.CommandsEnum;
import Model.Effects.Effect;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Quest;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.SkillsEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Pack;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import State.State;
import Utilities.ItemUtilities.ItemFactory;
import View.Views.ItemView;
import View.Views.TradeView;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by broskj on 3/9/16.
 */
public class TradeState extends State {

    private HashMap<Item, ItemView> playerItems, shopItems;
    private Pack playerPack, shopPack;
    Player player;
    private TradeView tradeView;
    private int selector;
    private BargainAbility bargain;

    public TradeState(Player player, Pack shopPack) {
        setController(new TradeController(this));

        this.player = player;
        this.playerPack = player.getPack();
        this.shopPack = shopPack;
        this.bargain = new BargainAbility("Bargain",
                "Get a better deal",
                new Effect(new StatStructure(StatsEnum.LIFE, 0)),
                new Requirement(0),
                0,
                player.getBasicSkillsValue(SkillsEnum.BARGAIN),
                0);
        playerItems = new HashMap<>();
        shopItems = new HashMap<>();
        setController(new TradeController(this));
        selector = 0;
        tradeView = new TradeView(playerPack, shopPack, bargain);
        for (Item item : playerPack.getItems()) {
            if (item != null) {
                playerItems.put(item, ItemFactory.makeAsset(item.getItemType(), item));
            }
        }
        for (Item item : shopPack.getItems()) {
            if (item != null) {
                playerItems.put(item, ItemFactory.makeAsset(item.getItemType(), item));
            }
        }
    } // end constructor

    public void executeCommand(CommandsEnum command) {
        switch (command) {
            case bargain:
                System.out.println("Bargaining");
                break;
            case make_transaction:
                System.out.println("making transaction...");
                transaction();
                break;
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
            default:
                System.out.println("Don't send that command to the TradeState");
        }
    } // end executeCommand

    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        tradeView.render(g, selector);
    }

    public void up() {
        if (selector - 4 < 0 && selector < 16) selector += 12;
        else if (selector - 4 < 16 && selector > 15) selector += 12;
        else selector -= 4;
    }

    public void down() {
        if (selector + 4 > 15 && selector < 16) selector -= 12;
        else if (selector + 4 > 31 && selector > 15) selector -= 12;
        else selector += 4;
    }

    public void right() {
        if (selector % 4 == 3 && selector < 16) selector += 13;
        else if (selector % 4 == 3 && selector > 15) selector -= 19;
        else selector++;
    }

    public void left() {
        if (selector % 4 == 0 && selector < 16) selector += 19;
        else if (selector % 4 == 0 && selector > 15) selector -= 13;
        else selector--;
    }

    public void transaction() {
        Item good;
        int newValue;

        if (selector <= 15) {                                /* player'selection pack */
            good = playerPack.get(selector);                    /* selling item */
            if (good == null || good instanceof Quest)    /* can't trade quest items */
                return;
            newValue = (int) (good.getValue() * 0.9);
            newValue = bargain.bargain(newValue, true);

            if (shopPack.getMoney() >= newValue) {
                /*
                execute sale
                 */
                System.out.println("Selling " + good.getName() + " for " + newValue);
                playerPack.modifyMoney(newValue);
                shopPack.modifyMoney(-newValue);
                shopPack.place(playerPack.remove(selector));
            } // end if
        } else {                                           /* shopkeeper'selection pack */
            good = shopPack.get(selector - 16);                   /* buying item */
            if (good == null || good instanceof Quest)    /* can't trade quest items */
                return;
            newValue = (int) (good.getValue() * 1.1);
            newValue = bargain.bargain(newValue, false);

            if (playerPack.getMoney() >= newValue) {
                /*
                execute purchase
                 */
                System.out.println("Buying " + good.getName() + " for " + newValue);
                playerPack.modifyMoney(-newValue);
                shopPack.modifyMoney(newValue);
                playerPack.place(shopPack.remove(selector - 16));
            } // end if
        }
    } // end transaction

} // end TradeState
