package State.States.GameState;

import Controller.Controllers.TradeController;
import Model.Abilities.CommandsEnum;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Quest;
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
    private int selector;

    public TradeState(GameState game){
        this.game=game;
        setController(new TradeController(this));
        selector=0;
        tradeView=new TradeView();
    }

    public TradeState(GameState game, Pack playerPack, Pack shopPack) {
        setController(new TradeController(this));
        this.game = game;
        this.playerPack = playerPack;
        this.shopPack = shopPack;
        playerItems = new HashMap<>();
        shopItems = new HashMap<>();
        setController(new TradeController(this));
        selector=0;
        tradeView=new TradeView();
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
        game.render(g);
        tradeView.render(g,selector);
    }
    @Override

    public void switchState(State state) {
        setState(state);
    }

    public void up(){
        if(selector-4<0&&selector<16)selector+=12;
        else if(selector-4<16&&selector>15)selector+=12;
        else selector-=4;
    }
    public void down(){
        if(selector+4>15&&selector<16)selector-=12;
        else if(selector+4>31&&selector>15)selector-=12;
        else selector+=4;
    }
    public void right(){
        if(selector%4==3&&selector<16)selector+=13;
        else if(selector%4==3&&selector>15)selector-=19;
        else selector++;
    }
    public void left(){
        if(selector%4==0&&selector<16)selector+=19;
        else if(selector%4==0&&selector>15)selector-=13;
        else selector--;
    }

    public void transaction() {
        Item good;
        int newValue;

        if(selector <= 15) {                             /* player's pack */
            good = playerPack.get(selector);                /* selling item */
            if(good == null || !(good instanceof Quest)) /* can't trade quest items */
                return;
            newValue = (int)(good.getValue() * 0.9);

            if(shopPack.getMoney() >= newValue) {
                /*
                execute sale
                 */
                System.out.println("Selling " + good.getName() + " for " + newValue);
                playerPack.modifyMoney(newValue);
                shopPack.modifyMoney(-newValue);
                shopPack.place(playerPack.remove(selector));
            } // end if
        } else  {                                       /* shopkeeper's pack */
            good = shopPack.get(selector-16);               /* buying item */
            if(good == null || !(good instanceof Quest)) /* can't trade quest items */
                return;
            newValue = (int)(good.getValue() * 1.1);

            if(playerPack.getMoney() >= newValue) {
                /*
                execute purchase
                 */
                System.out.println("Buying " + good.getName() + " for " + newValue);
                playerPack.modifyMoney(-newValue);
                shopPack.modifyMoney(newValue);
                playerPack.place(shopPack.remove(selector-16));
            } // end if
        }
    } // end transaction

} // end TradeState
