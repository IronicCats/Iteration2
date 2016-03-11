package State.States.GameState;

import Controller.Controllers.TradeController;
import Model.Abilities.CommandsEnum;
import Model.GameObject.Item.Item;
import Model.Inventory.Pack;
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
    private GameState game;
    private HashMap<Item, ItemView> playerItems, shopItems;
    private Pack playerPack,
            shopPack;
    private TradeView tradeView;

    public TradeState(GameState game, Pack playerPack, Pack shopPack) {
        setController(new TradeController(this));
        this.game = game;
        this.playerPack = playerPack;
        this.shopPack = shopPack;
        playerItems = new HashMap<>();
        shopItems = new HashMap<>();

        for(Item item : playerPack.getItems()) {
            if (item != null) {
                playerItems.put(item, ItemFactory.makeAsset(item.getItemType(), item));
            }
        }for(Item item : shopPack.getItems()) {
            if (item != null) {
                playerItems.put(item, ItemFactory.makeAsset(item.getItemType(), item));
            }
        }
    } // end constructor

    public void executeCommand(CommandsEnum command) {
        switch(command) {
            case make_transaction:
                System.out.println("making transaction...");
                break;
            default:
                System.out.println("Don't send that command to the TradeState");
        }
    } // end executeCommand

    public void tick() {
    }

    public void render(Graphics g) {
        game.render(g);
    }
    @Override

    public void switchState(State state) {
        setState(state);
    }
} // end TradeState
