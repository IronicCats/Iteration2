package Utilities;

import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.AreaEffect.TeleportAreaEffect;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.MobileObjects.Entities.Characters.FriendlyNPC;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Sneak;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Summoner;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Vehicle;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Inventory.Equipment;
import Model.Inventory.EquipmentSlotEnum;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Model.Map.Tiles.Grass;
import Model.Map.Tiles.Mountain;
import Model.Map.Tiles.Water;
import Model.Stats.*;
import State.State;
import State.States.GameState.*;
import Utilities.AreaEffectUtilities.AreaEffectFactory;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import Utilities.MobileObjectUtilities.MobileObjectFactory;
import View.ViewUtilities.Graphics.Assets;
import View.Views.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Andy on 3/5/2016.
 */
public class SaveLoad {


    private static String currFileName;     //the name of the file that will be saved.
    public static SaveLoad instance = new SaveLoad(); //an instance of SaveLoad, only needs one.
    private static Player player;   //will probably need an Entity list
    private static Map gameMap;     //list of all maps may be needed
    private static GameState game;
    private static MapView gamemapView;
    private static HashMap<MobileObject, MobileObjectView> mobileObjects;
    private static HashMap<AreaEffect, DecalView> decals;
    private static HashMap<Item, ItemView> mapItems;
    private static ArrayList<TeleportAreaEffect> teleportAreaEffectsA = new ArrayList<TeleportAreaEffect>();

    private static GameState gs;

    //private static final String filePathExtension = Utilities.getFileSystemDependentPath("src/res/saveFiles";)



    public static GameState getGameState() {
        return gs;
    }

    public static Player getPlayer(){// this will be changed later
        return player;

    }

    public static Map getGameMap() {// to save the map
        return gameMap;
    }

    public static MapView getGamemapView() {
        return gamemapView;
    }


    public static void setGameMap(Map map) {// sets the current map
        gameMap = map;
    }

    public static void setPlayer(Player a) {//sets the current player
        player = a;
    }

    public static void setGamemapView(MapView m) {
        gamemapView = m;
    }

    public static void setMobileObjects(HashMap<MobileObject, MobileObjectView> mobileObjectMap) {
        mobileObjects = mobileObjectMap;
    }
    public static void setMapItems(HashMap<Item,ItemView> mapItemsMap){
        mapItems = mapItemsMap;
    }

    public static void setCurrFileName(String fileName) {
        currFileName = fileName;
    }

    public static SaveLoad getInstance() {//returns the instance of SaveLoad
        return instance;
    }

    //---------------------------------LOAD--------------------------------// ヽ༼ຈل͜ຈ༽ﾉ
    public static void load(String fileName) {

        gs = new GameState(true);
        State.GAMESTATE = gs;



        currFileName = fileName;
        mapItems = new HashMap<>();
        decals = new HashMap<>();

        //String filePath = filePathExtension + fileName;
        String filePath = "res/saveFiles/" + fileName;
        loadMap(gameMap, filePath);  //gameMap may be wrong, need to check this
        GameState.map = gameMap;

        loadPlayer(filePath);
        GameState.player = player;


        loadMobileObjects(filePath);
        gs.setMobileObjects(mobileObjects);

        gs.initGameState();
        for(Item item: mapItems.keySet()){
            gameMap.getTile(item.getLocation()).addItem(item);
        }
        gameMap.setMapItems(mapItems);
        for(AreaEffect areaEffect: decals.keySet()){
            gameMap.getTile(areaEffect.getLocation()).setAreaEffectTile(areaEffect);
        }
        for(TeleportAreaEffect teleportAreaEffect: teleportAreaEffectsA){
            gameMap.getTile(teleportAreaEffect.getLocation()).setTeleportAreaEffectTile(teleportAreaEffect);
        }

        //gameMap setdecals? It should load in properly now.


        //((Player) player).update();
        if(mobileObjects.isEmpty()){
            System.out.println("This is empty");
        }
        else
            System.out.println("This isn't empty");
        if (gs == null){
            System.out.println("This is null");
        }


        System.out.println("Player x location:" + player.getLocation().getX() + " Player Y location:" + player.getLocation().getY());

        System.out.println("Everything has been loaded!");
        State.INVENTORYSTATE  = new InventoryState();//adding the inv state

        State.EQUIPMENTSTATE  = new EquipmentState();//adding the equipment state

        State.SKILLSSTATE  = new SkillsState();

        State.PAUSESTATE = new PauseState(); // adding pause state

        State.SETTINGSTATE  = new SettingState();
        State.SAVESTATE = new SaveState();
        State.LOADSTATE  = new LoadState();
        gs.toggleloading();
        gs.setPlayer(player);




        //
        //State.GAMESTATE = new GameState((Player)player,gameMap,gamemapView,mobileObjects,decals,mapItems);
        //gs.togglePause();
    }

    public static void loadMap(Map inputMap, String fileName) {

        String filepath = fileName;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filepath));

            doc.getDocumentElement().normalize(); //this normalizes it, idk what that means though lol

            NodeList mapList = doc.getElementsByTagName("map");
            Element map = (Element) mapList.item(0); //need to change this for working with multiple maps
            int mapWidth = Integer.parseInt(map.getAttribute("width"));
            int mapHeight = Integer.parseInt(map.getAttribute("height"));

            //if there is problem it may be here
            Tile[][] tiles = new Tile[mapWidth][mapHeight]; //this might not be good in our implementation
            TileView[][] tv = new TileView[mapWidth][mapHeight];
            //TeleportAreaEffect[] teleportAreaEffectsArray;
            ArrayList<TeleportAreaEffect> teleportAreaEffectsArrayList = new ArrayList<TeleportAreaEffect>();
            //was mapHeight/mapWitdth

            NodeList rows = doc.getElementsByTagName("row");

            for (int i = 0; i < rows.getLength(); i++) {
                Element row = (Element) rows.item(i); //for each row in i
                NodeList tileNodes = row.getElementsByTagName("tile");

                for (int j = 0; j < tileNodes.getLength(); j++) {
                    Element tileElement = (Element) tileNodes.item(j);


                    Element terrainElement = (Element) tileElement.getElementsByTagName("terrain").item(0); //other thing has item(0)
                    String terrainType = terrainElement.getAttribute("terrainType");


                    //AreaEffect areaEffect = null;       //blank areaEffect to fill in
                    NodeList areaEffectNodes = tileElement.getElementsByTagName("areaEffect");
                    if (areaEffectNodes.getLength() > 0) {
                        Element areaEffectElement = (Element) areaEffectNodes.item(0);
                        String areaEffectEnum = areaEffectElement.getAttribute("enum");

                        AreaEffect areaEffect1 = null;
                        AreaEffectEnum a = AreaEffectEnum.valueOf(areaEffectEnum) ;
                        Location areaLoc = new Location(i,j,270);
                        System.out.println("This enum is being loaded in:" + areaEffectEnum + " at location " + areaLoc.toString());
                        areaEffect1 = AreaEffectFactory.makeAreaEffect(a,areaLoc);
                        decals.put(areaEffect1,AreaEffectFactory.makeAsset(a,areaEffect1));

                    }
                    NodeList teleportEffectNode = tileElement.getElementsByTagName("teleportAreaEffect");
                    if(teleportEffectNode.getLength() > 0)
                    {
                        Element teleportEffectElement = (Element)teleportEffectNode.item(0);
                        String teleX = teleportEffectElement.getAttribute("locX");
                        String teleY = teleportEffectElement.getAttribute("locY");
                        String destX = teleportEffectElement.getAttribute("destX");
                        String destY = teleportEffectElement.getAttribute("destY");

                        Location teleLoc = new Location(Integer.parseInt(teleX),Integer.parseInt(teleY));
                        Location destLoc = new Location(Integer.parseInt(destX),Integer.parseInt(destY));

                        TeleportAreaEffect tAE = new TeleportAreaEffect(teleLoc,destLoc);
                        teleportAreaEffectsArrayList.add(tAE);
                        teleportAreaEffectsA.add(tAE);
                    }
                    //decal stuff but I don't even have that saving yet

                    Item[] itemArray = new Item[10];//check this
                    NodeList itemNodes = tileElement.getElementsByTagName("item");
                    if (itemNodes.getLength() > 0) {
                        //this is very iffy at the moment
                        for (int k = 0; k < itemNodes.getLength(); k++) {
                            Element itemElement = (Element) itemNodes.item(k);
                            String itemID = itemElement.getAttribute("id");
                            int id = Integer.parseInt(itemID);
                            //id = itemsEnum.
                            //ItemsEnum.AGILITY_POTION.ordinal

                            Location l = new Location(0, 0, 270);
                            l.setX(i);
                            l.setY(j);

                            //I'm probably going to need some huge if statement or something. Idk
                            ItemsEnum a = ItemsEnum.values()[id];
                            itemArray[k] = ItemFactory.makeItem(a,l);
                            mapItems.put(itemArray[k],ItemFactory.makeAsset(a,itemArray[k]));
                            System.out.println("Item ID:" + id);

                           System.out.println("X item loc:" + Integer.toString(itemArray[k].getLocation().getX()));
                           System.out.println("Y item loc:" + Integer.toString(itemArray[k].getLocation().getY()));
                            //mapItems.put(itemArray[k],ItemFactory.makeAsset(ItemsEnum.PANTS,itemArray[k]));


                        }

                    }


                   // ArrayList itemArrayList = new ArrayList<>(Arrays.asList(itemArray));
                    //THIS ARRAYLIST AND ADDITEMS STUFF WAS RECENTLY ADDED TO SEE IF IT COULD FIX THE THING

                    Location lg = new Location(i,j);
                    System.out.println("This is the terrain type of " + i + "," + j + " " + terrainType);
                    if(terrainType.equalsIgnoreCase("grass")) {
                        //System.out.println("I get the grass.");

                        tiles[i][j] = new Grass(lg);
                        tv[i][j] = new TileView(tiles[i][j], Assets.GRASSHEXTILE);

                        //if(!itemArrayList.isEmpty())
                            //tiles[i][j].addItems(itemArrayList);

                    }
                    else if(terrainType.equalsIgnoreCase("water")) {
                        //System.out.println("I get the water.");

                        tiles[i][j] = new Water(lg);
                        tv[i][j] = new TileView(tiles[i][j], Assets.WATERHEXTILE);
                        //if(!itemArrayList.isEmpty())
                            //tiles[i][j].addItems(itemArrayList);

                    } else if (terrainType.equalsIgnoreCase("mountain")) {
                        tiles[i][j] = new Mountain(lg);
                        tv[i][j] = new TileView(tiles[i][j], Assets.MOUNTAINHEXTILE);
                        //if(!itemArrayList.isEmpty())
                            //tiles[i][j].addItems(itemArrayList);
                    }
                    //System.out.println(tiles[i][j].toString());

                }
            }
            Location spawn = new Location(0, 0);
            spawn.setX(Integer.parseInt(map.getAttribute("spawnX")));
            spawn.setY(Integer.parseInt(map.getAttribute("spawnY")));


            Map recreateMap = new Map(tiles, mapWidth, mapHeight, spawn);
            gameMap = recreateMap;
            MapView mv = new MapView(gameMap, tv);

            gamemapView = mv;
            mv.update();
            //inputMap = recreateMap;


            // Map recreateMap = new Map();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Map problems");
        }
    }

    public static void loadPlayer(String fileName) {
        // Get the xml filepath string ensuring file separators are specific to the use's OS.
        String file = fileName;
        Player avatar = player;
        //Map m = IOMediator.getInstance().map;
        try {
            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(file));
            doc.getDocumentElement().normalize();

            NodeList mobileObjects = doc.getElementsByTagName("mobileObjects"); //might have to be player
            //Checks the type of the avatar and sets information
            //System.out.println("length =" + entities.getLength()); TODO erase
            for (int i = 0; i < mobileObjects.getLength(); i++) {
                Element entity = (Element) mobileObjects.item(i); // this is a player
                NodeList p = doc.getElementsByTagName("player");

                Element pl = (Element)p.item(0);
               // if (entity.getAttribute("player").equals("player")) {
                    int x = Integer.parseInt(pl.getAttribute("locX"));
                    int y = Integer.parseInt(pl.getAttribute("locY"));
                    int d = Integer.parseInt(pl.getAttribute("direction"));
                    String occupationString = pl.getAttribute("occupation");
                    //System.out.println("Occupation:" + occupation);

                    System.out.println("x=" + x + " y=" + y + " direction=" + d);
                    Location l = new Location(x,y,d);
                    //Location l = new Location(x,y);
                    //avatar.setLocation(l);
                    //System.out.println(player.toString()); //FIXME this broke it for some reason

                    //player.setLocation(l);
                    //Player p = new Player(l,)

                    //Player peer = new Player()
                            //Assign occupation when creating new player, don't update jesus fucking christ what is wrong with me.

                Occupation occupation = null;
                switch (occupationString) {
                    case "Smasher":
                        occupation = new Smasher();
                        break;
                    case "Summoner":
                        occupation = new Summoner();
                        break;
                    case "Sneak":
                        occupation = new Sneak();
                        break;
                }
                //load inventory FIXME
                Inventory inv = new Inventory();
                loadInventory(pl,inv);

                Player peer = new Player(l,2,occupation,inv); //THIS SHOULD BE WORKING
                loadStats(peer.getStats(),pl);

                player = peer;

            }

            //System.out.println("Finish loading avatar: " + avatar.getLocation()[0] + "," + avatar.getLocation()[1] + "," + avatar.getOrientation());
        } catch (Exception e) {
            System.out.println("Problem parsing avatar");
            e.printStackTrace();
        }
    }
    public static void loadInventory(Element player,Inventory in){
        NodeList temp = player.getElementsByTagName("inventory");
        System.out.println("TEMP LENGHT" + temp.getLength());
        if(temp.getLength() != 0) {
            for (int i = 0; i < temp.getLength(); i++) {
                Location l = new Location(-1, -1);
                Element prime = (Element) temp.item(i);
                Element item = (Element) prime.getElementsByTagName("item").item(0);
                if(item != null) {
                    String id = item.getAttribute("id");
                    System.out.println("i: " + i);
                    int k = Integer.parseInt(id);
                    //Item a = ItemFactory.makeItem(,l)
                    ItemsEnum tempEnum = ItemsEnum.values()[k];
                    Item a = ItemFactory.makeItem(tempEnum, l);
                    in.place(a);
                }

            }
        }
        NodeList tempEquip = player.getElementsByTagName("equipped");
        Location equipLoc = new Location(-1,-1);
        ItemsEnum tempEnumE;
        Item z;
        int equipIdint;
        String equipId;


        Element head = (Element) tempEquip.item(0);
        Element equipItem = (Element)head.getElementsByTagName("head").item(0);

        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Armor) z);
        }


        Element chest = (Element) tempEquip.item(0);
        equipItem = (Element)chest.getElementsByTagName("chest").item(0);

        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Armor) z);
        }

        Element gloves = (Element) tempEquip.item(0);
        equipItem = (Element)gloves.getElementsByTagName("gloves").item(0);
        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Armor) z);
        }

        Element boots = (Element) tempEquip.item(0);
        equipItem = (Element)boots.getElementsByTagName("boots").item(0);
        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Armor) z);
        }

        Element legs = (Element) tempEquip.item(0);
        equipItem = (Element)legs.getElementsByTagName("legs").item(0);
        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Armor) z);
        }

        Element shield = (Element) tempEquip.item(0);
        equipItem = (Element)shield.getElementsByTagName("shield").item(0);
        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Armor) z);
        }
        Element mainHand = (Element) tempEquip.item(0);
        equipItem = (Element)mainHand.getElementsByTagName("mainHand").item(0);
        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Weapon) z);
        }

        Element offHand = (Element) tempEquip.item(0);
        equipItem = (Element)offHand.getElementsByTagName("offHand").item(0);
        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Weapon) z);
        }
        Element accessory1 = (Element)tempEquip.item(0);
        equipItem = (Element)accessory1.getElementsByTagName("accessory1").item(0);
        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Armor) z);
        }
        Element accessory2 = (Element)tempEquip.item(0);
        equipItem = (Element)accessory2.getElementsByTagName("accessory2").item(0);
        equipId = equipItem.getAttribute("id");
        equipIdint = Integer.parseInt(equipId);
        if(equipIdint != -1) {
            tempEnumE = ItemsEnum.values()[equipIdint];
            z = ItemFactory.makeItem(tempEnumE, equipLoc);
            in.equip((Armor) z);
        }


    }

    private static void loadStats(CharacterStats cStats, Element player) {
        NodeList temp = player.getElementsByTagName("primary");
        Element prime = (Element) temp.item(0);

        cStats.setStrength(Integer.parseInt(prime.getAttribute("strength")));
        cStats.setBaseLife(Integer.parseInt(prime.getAttribute("baseLife")));
        cStats.setAgility(Integer.parseInt(prime.getAttribute("agility")));
        cStats.setBaseAgi(Integer.parseInt(prime.getAttribute("baseAgi")));
        cStats.setBaseStr(Integer.parseInt(prime.getAttribute("baseStr")));
        cStats.setIntellect(Integer.parseInt(prime.getAttribute("intellect")));
        cStats.setBaseIntel(Integer.parseInt(prime.getAttribute("baseIntel")));
        cStats.setBaseMana(Integer.parseInt(prime.getAttribute("baseMana")));
        cStats.setExperience(Integer.parseInt(prime.getAttribute("experience")));
        cStats.setBaseLives(Integer.parseInt(prime.getAttribute("baseLives")));
        cStats.setHardiness(Integer.parseInt(prime.getAttribute("hardiness")));
        cStats.setBaseHard(Integer.parseInt(prime.getAttribute("baseHard")));
        cStats.setDefensiveRating(Integer.parseInt(prime.getAttribute("defense")));
        cStats.setOffensiveRating(Integer.parseInt(prime.getAttribute("offense")));
        cStats.setLife(Integer.parseInt(prime.getAttribute("life")));
        cStats.setMana(Integer.parseInt(prime.getAttribute("mana")));
        cStats.setMovement(Integer.parseInt(prime.getAttribute("movement")));
        //xp threshhold?


        //cStats.set
        //StatStructure statStructure = new StatStructure()

        //CharacterStats c = new CharacterStats()

    }

    private static void loadVehicles(String filename) {

    }

    private static void loadPets(String filename) {

    }

    private static void loadMobileObjects(String filename){
        mobileObjects = new HashMap<>();
        Location l = new Location(2,3,0);
        //Stats stats = new Stats();
        System.out.println("It should be here.");
        //gameMap.setMobileObjects(mobileObjects);
         mobileObjects = MobileObjectFactory.Init(gameMap,(Player)player);
         //Pet a = new MobileObjectFactory().makeNPC(MobileObjectEnum.DAVE_PET,l,gameMap,(Player)player);
        //FriendlyNPC a = (FriendlyNPC) MobileObjectFactory.makeNPC(MobileObjectEnum.CORGI_SHOPKEEPER,l,gameMap,(Player)player);
        //a.getController().setBaseLoc(new Location(10, 10));
        //mobileObjects.put(a,MobileObjectFactory.makeAsset(MobileObjectEnum.CORGI_SHOPKEEPER,a));


        ////////////////////////////////////// TEST
        Iterator it = mobileObjects.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry) it.next();
            System.out.println(pair.getKey() + "  This is the related view: " + pair.getValue());
            //it.remove(); ??? Says it avoids CurrentModificationException
        }

        ///////////////////////////////
    }

    //---------------------------------------------------------------------//
    //                                                                     //
    //                                                                     //
    //---------------------------------SAVE--------------------------------// ヽ༼ຈل͜ຈ༽ﾉ
    public static void save() {//function that will be called when you want to save
        if (currFileName == null)
            currFileName = "saveFile1";

        try {
            //Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); //create an instance of a documentBuilderFactory
            //Defines the API to obtain DOM Document instances from an XML document. Using this class, an application programmer can obtain a Document from XML.
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); //creates a documentBuilder from Factory
            Document doc = docBuilder.newDocument();                        //and creates a doc from Builder

            //String filePath = "/res/saveFiles/" + currFileName;              //the filePath is in resource folders plus passed in filename
            String filePath = getSaveFilePath(currFileName);                    //should work for saveFilePath
            //The Element interface represents an element in an XML document
            Element rootElement = doc.createElementNS(filePath, "SaveFile"); //starts the root Element of XML from filePath
            doc.appendChild(rootElement);                                   //adds the child to the doc
            rootElement.appendChild(getMobileObjects(doc, mobileObjects));
            rootElement.appendChild(getPlayerInfo(doc, (Player) player));                 //adds the next root element which is entities
            rootElement.appendChild(getMap(doc, gameMap));

            toXML(doc, filePath);                                            //turns the document into an XML
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static Node getMobileObjects(Document doc, HashMap<MobileObject, MobileObjectView> mO) {
        Element mobileObjects = doc.createElement("mobileObjects"); // gets entity with createElement
        Attr test = doc.createAttribute("test");
        Iterator it = mO.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry) it.next();
            System.out.println(pair.getKey() + "=" + pair.getValue());
            if (pair.getKey() instanceof Player) {
                getPlayerInfo(doc, (Player) pair.getKey());
            } else if (pair.getKey() instanceof Pet) {
                getPetInfo(doc, (Pet) pair.getKey());
            } else if (pair.getKey() instanceof Vehicle) {
                getVehicleInfo(doc, (Vehicle) pair.getKey());
            } else if (pair.getKey() instanceof FriendlyNPC) {
                //getFriendlyNPCInfo
            } else if (pair.getKey() instanceof HostileNPC) {
                //getHostileNPCInfo
            }
            //it.remove(); ??? Says it avoids CurrentModificationException
        }
        //test.
        //entity.appendChild(getPlayerInfo(doc,e));//need to make Entity info
        return mobileObjects;
    }

    private static Node getPetInfo(Document doc, Pet p) {
        Element type = doc.createElement("pet");

        Attr x = doc.createAttribute("locX");
        x.setValue(Integer.toString(p.getLocation().getX()));
        type.setAttributeNode(x);

        Attr y = doc.createAttribute("locY"); //creates an attribute for Y location
        y.setValue(Integer.toString(p.getLocation().getY())); //sets the y attribute to player y
        type.setAttributeNode(y);

        Attr direction = doc.createAttribute("direction"); //creates an attribute for direction
        direction.setValue(Integer.toString(p.getLocation().getDir()));//sets the direction attribute to player direction
        type.setAttributeNode(direction); //element sets attribute Node to direction

        Attr owned = doc.createAttribute("owned");
        owned.setValue(Boolean.toString(p.getOwned()));
        type.setAttributeNode(owned);

        //probably need to get "base"
        return type;
    }

    private static Node getVehicleInfo(Document doc, Vehicle v) {
        Element type = doc.createElement("vehicle");

        Attr x = doc.createAttribute("locX");
        x.setValue(Integer.toString(v.getLocation().getX()));
        type.setAttributeNode(x);

        Attr y = doc.createAttribute("locY");
        y.setValue(Integer.toString(v.getLocation().getX()));
        type.setAttributeNode(y);

        Attr direction = doc.createAttribute("direction"); //creates an attribute for direction
        direction.setValue(Integer.toString(v.getLocation().getDir()));//sets the direction attribute to player direction
        type.setAttributeNode(direction);


        //may need to wait for it to be implemented so I know what I'm saving

        return type;
    }

    private static Node getPlayerInfo(Document doc, Player e) {
        Element type = doc.createElement("player"); //creates a new element in document, labels it player

        // Attr etype = doc.createAttribute("type");
        // etype.setValue(e.getType());

        Attr x = doc.createAttribute("locX"); //creates an attribute for X location
        x.setValue(Integer.toString(e.getLocation().getX())); //sets the x attriubte to player x
        type.setAttributeNode(x);   //sets the attribute of element as x.

        Attr y = doc.createAttribute("locY"); //creates an attribute for Y location
        y.setValue(Integer.toString(e.getLocation().getY())); //sets the y attribute to player y
        type.setAttributeNode(y);  //element sets attribute Node to y

        Attr direction = doc.createAttribute("direction"); //creates an attribute for direction
        direction.setValue(Integer.toString(e.getLocation().getDir()));//sets the direction attribute to player direction
        type.setAttributeNode(direction); //element sets attribute Node to direction

        Attr occupation = doc.createAttribute("occupation"); //creates an attribute for occupation
        occupation.setValue(e.getOccupation().getName());   //sets the occupation attribute to player occupation
        type.setAttributeNode(occupation);          //element sets attribute Node to element

        //Element stat = doc.createElement("Stats");
        type.appendChild(getCharacterStats(doc, (CharacterStats) e.getStats()));
        type.appendChild(getInventory(doc, e.getInventory()));
        type.appendChild(getEquippedItems(doc, e.getInventory().getEquipment()));

        //need to get Primary then secondary
        return type;                //returns it so it can be used in XML
    }

    private static Node getCharacterStats(Document doc, CharacterStats stat) {


        Element primary = doc.createElement("primary");

        Attr livesLeft = doc.createAttribute("livesLeft");
        livesLeft.setValue(Integer.toString(stat.getLivesLeft()));
        primary.setAttributeNode(livesLeft);

        Attr strength = doc.createAttribute("strength");
        strength.setValue(Integer.toString(stat.getStrength()));
        primary.setAttributeNode(strength);

        Attr agility = doc.createAttribute("agility");
        agility.setValue(Integer.toString(stat.getAgility()));
        primary.setAttributeNode(agility);

        Attr intellect = doc.createAttribute("intellect");
        intellect.setValue(Integer.toString(stat.getIntellect()));
        primary.setAttributeNode(intellect);

        Attr hardiness = doc.createAttribute("hardiness");
        hardiness.setValue(Integer.toString(stat.getHardiness()));
        primary.setAttributeNode(hardiness);

        Attr experience = doc.createAttribute("experience");
        experience.setValue(Integer.toString(stat.getExperience()));
        primary.setAttributeNode(experience);

        Attr movement = doc.createAttribute("movement");
        movement.setValue(Integer.toString(stat.getMovement()));
        primary.setAttributeNode(movement);

        Attr baseStr = doc.createAttribute("baseStr");
        baseStr.setValue(Integer.toString(stat.getBaseStr()));
        primary.setAttributeNode(baseStr);

        Attr baseAgi = doc.createAttribute("baseAgi");
        baseAgi.setValue(Integer.toString(stat.getBaseAgi()));
        primary.setAttributeNode(baseAgi);

        Attr baseIntel = doc.createAttribute("baseIntel");
        baseIntel.setValue(Integer.toString(stat.getBaseIntel()));
        primary.setAttributeNode(baseIntel);

        Attr baseHard = doc.createAttribute("baseHard");
        baseHard.setValue(Integer.toString(stat.getBaseHard()));
        primary.setAttributeNode(baseHard);

        Attr baseMovement = doc.createAttribute("baseMovement");
        baseMovement.setValue(Integer.toString(stat.getBaseMovement()));
        primary.setAttributeNode(baseMovement);

        Attr xpThresh = doc.createAttribute("xpThresh");
        xpThresh.setValue(Integer.toString(stat.getXpThreshhold()));
        primary.setAttributeNode(xpThresh);

        Attr level = doc.createAttribute("level");
        level.setValue(Integer.toString(stat.getLevel()));
        primary.setAttributeNode(level);

        Attr life = doc.createAttribute("life");
        life.setValue(Integer.toString(stat.getLife()));
        primary.setAttributeNode(life);

        Attr mana = doc.createAttribute("mana");
        mana.setValue(Integer.toString(stat.getMana()));
        primary.setAttributeNode(mana);

        Attr offensiveRating = doc.createAttribute("offense");
        offensiveRating.setValue(Integer.toString(stat.getOffensiveRating()));
        primary.setAttributeNode(offensiveRating);

        Attr defesniveRating = doc.createAttribute("defense");
        defesniveRating.setValue(Integer.toString(stat.getDefensiveRating()));
        primary.setAttributeNode(defesniveRating);

        Attr armorRating = doc.createAttribute("armorRating");
        armorRating.setValue(Integer.toString(stat.getArmorRating()));
        primary.setAttributeNode(armorRating);

        Attr baseLife = doc.createAttribute("baseLife");
        baseLife.setValue(Integer.toString(stat.getBaseLife()));
        primary.setAttributeNode(baseLife);

        Attr baseMana = doc.createAttribute("baseMana");
        baseMana.setValue(Integer.toString(stat.getBaseMana()));
        primary.setAttributeNode(baseMana);

        Attr baseLives = doc.createAttribute("baseLives");
        baseLives.setValue(Integer.toString(stat.getBaseLives()));
        primary.setAttributeNode(baseLives);

        return primary;
    }


    private static Node getInventory(Document doc, Inventory inv) {
        Element inventory = doc.createElement("inventory");

        int temp = inv.getPackSpaceLeft();
        temp = 16 - temp;

        int i = 0;
        while(temp != 0){
            Element iItem = doc.createElement("item");
            if(inv.get(i) != null) {
                Attr id = doc.createAttribute("id");
                id.setValue(Integer.toString(inv.get(i).getId()));
                iItem.setAttributeNode(id);
                inventory.appendChild(iItem);
                temp--;
            }
            i++;
        }


        return inventory;
    }


    private static Node getEquippedItems(Document doc, Equipment equipped) {
        Element equip = doc.createElement("equipped");
        equip.appendChild(getEquip(doc, equipped, "head"));
        equip.appendChild(getEquip(doc, equipped, "chest"));
        equip.appendChild(getEquip(doc, equipped, "gloves"));
        equip.appendChild(getEquip(doc, equipped, "boots"));
        equip.appendChild(getEquip(doc, equipped, "legs"));
        equip.appendChild(getEquip(doc, equipped, "shield"));
        equip.appendChild(getEquip(doc, equipped, "mainHand"));
        equip.appendChild(getEquip(doc, equipped, "offHand"));
        equip.appendChild(getEquip(doc, equipped, "accessory1"));
        equip.appendChild(getEquip(doc, equipped, "accessory2"));

        return equip;
    }

    private static Node getEquip(Document doc, Equipment equipped, String typeOfItem) {
        Element type = doc.createElement(typeOfItem);
        Item item = null;
        switch (typeOfItem) {
            case "head":
                item = equipped.getSlot(EquipmentSlotEnum.HEAD);
                break;
            case "chest":
                item = equipped.getSlot(EquipmentSlotEnum.CHEST);
                break;
            case "gloves":
                item = equipped.getSlot(EquipmentSlotEnum.GLOVES);
                break;
            case "legs":
                item = equipped.getSlot(EquipmentSlotEnum.LEGS);
                break;
            case "boots":
                item = equipped.getSlot(EquipmentSlotEnum.BOOTS);
                break;
            case "shield":
                item = equipped.getSlot(EquipmentSlotEnum.SHIELD);
                break;
            case "mainHand":
                item = equipped.getSlot(EquipmentSlotEnum.MAINHAND);
                break;
            case "offHand":
                item = equipped.getSlot(EquipmentSlotEnum.OFFHAND);
                break;
            case "accessory1":
                item = equipped.getSlot(EquipmentSlotEnum.ACCESSORY1);
                break;
            case "accessory2":
                item = equipped.getSlot(EquipmentSlotEnum.ACCESSORY2);
        }
        Attr id = doc.createAttribute("id");
        if (item != null) {
            id.setValue(Integer.toString(item.getId()));
        } else {
            id.setValue("-1");
        }
        type.setAttributeNode(id);

        return type;
    }

    private static Node getMap(Document doc, Map m) {
        Element map = doc.createElement("map"); //creates a map element for the doc
        int getWidth = m.getWidth();               //gets width
        int getHeight = m.getHeight();             //gets height
        Location l = m.getSpawn();                 //gets the spawn Location
        int spawnX = l.getX();                      //gets the X of the spawn location
        int spawnY = l.getY();                      //gets the Y of the spawn Location

        Attr width = doc.createAttribute("width");  //creates an attribute for width
        width.setValue(Integer.toString(getWidth)); //sets the value of attribute to width of map
        map.setAttributeNode(width);                //setsAttributeNode on element

        Attr height = doc.createAttribute("height");//creates an attribute for height
        height.setValue(Integer.toString(getHeight));//sets the value of the attribute to height of map
        map.setAttributeNode(height);               //setsAttributeNode on element

        //note I did something different than original cause it made mroe sense
        for (int i = 0; i < getWidth; i++) {  //for each in map width
            Element row = doc.createElement("row");//create element row
            for (int j = 0; j < getHeight; j++) { //for each in height
                row.appendChild(getTile(doc, m.getTile(i, j)));          //append each tile into row
            }
            map.appendChild(row);           //adds the node row to element map
        }

        Attr spawnXX = doc.createAttribute("spawnX");   //creates attribute for spawn location X
        spawnXX.setValue(Integer.toString(spawnX));     //sets the value of the attribute to spawn location x
        map.setAttributeNode(spawnXX);                  //sets the attribute Node on element

        Attr spawnYY = doc.createAttribute("spawnY");   //creates attribute for spawn location y
        spawnYY.setValue(Integer.toString(spawnY));     //sets the value of the attribute to spawn location y
        map.setAttributeNode(spawnYY);                  //sets the attribute Node on element

        return map;                     //returns the element

    }

    private static Node getTile(Document doc, Tile t) {
        Element tile = doc.createElement("tile"); //Creates an element with tag tile
        Element terrain = doc.createElement("terrain"); //creates and element for terrain

        Attr type = doc.createAttribute("terrainType");//creates an attribute for terrain
        if (t instanceof Grass)          //checks if it is an instance of grass
            type.setValue("grass");     //if so setValue to grass
        else if (t instanceof Water)     //checks to see if it is an instance of water
            type.setValue("water");     //if os setValue to water
        else if (t instanceof Mountain)  //checks to see if it is an instance of mountain
            type.setValue("mountain");  //if so setValue to mountain
        terrain.setAttributeNode(type); //sets the attribute node to whatever type it ended up
        tile.appendChild(terrain);      //appends the child to the element

        //May have to get Entity? Not 100% sure if that is necessary

        //AreaEffect
        if (t.getHasAreaEffect())// if it has an area effect
        {
            Element areaEffect = doc.createElement("areaEffect");   //creates an element and tags it as areaEffect

            Attr areaEnum = doc.createAttribute("enum");            //creates an attribute called areaEnum

            areaEnum.setValue(t.getAreaEffectEnum().toString());    //sets that attribute as AreaEffectEnum as a string
            areaEffect.setAttributeNode(areaEnum);                  //set AttributeNode of element
            tile.appendChild(areaEffect);                           //appends Node to element;
        }
        if(t.getHasTeleportAreaEffect()){
            Element teleportAreaEffect = doc.createElement("teleportAreaEffect");

            Attr teleportEnumX = doc.createAttribute("locX");
            Attr teleportEnumY = doc.createAttribute("locY");
            Attr teleportDestX = doc.createAttribute("destX");
            Attr teleportDestY = doc.createAttribute("destY");

            teleportEnumX.setValue(Integer.toString(t.getTeleportAreaEffect().getX()));
            teleportEnumY.setValue(Integer.toString(t.getTeleportAreaEffect().getY()));
            teleportDestX.setValue(Integer.toString(t.getTeleportAreaEffect().getEndLocation().getX()));
            teleportDestY.setValue(Integer.toString(t.getTeleportAreaEffect().getEndLocation().getY()));

            teleportAreaEffect.setAttributeNode(teleportEnumX);
            teleportAreaEffect.setAttributeNode(teleportEnumY);
            teleportAreaEffect.setAttributeNode(teleportDestX);
            teleportAreaEffect.setAttributeNode(teleportDestY);

            tile.appendChild(teleportAreaEffect);
        }
        //if(t.)
        //Decal

        //item

        if (t.hasItems()) {

            Element item = doc.createElement("item"); // creates an element and tags it as a document
            //will probably have to go through each item on a tile since we can have multiple

            Attr itemID = doc.createAttribute("id");
            //gets the id of an item.
            for (int i = 0; i < t.getItems().size(); i++) {//note make sure this is correct for size
                itemID.setValue(Integer.toString(t.getItems().get(i).getId()));
                item.setAttributeNode(itemID);      //sets the attribute node to the item id
                tile.appendChild(item);             //appends node to element
            }
            //note, I am pretty sure having it in the loop is right

        }

        return tile; //returns the element
    }

    public static void toXML(Document doc, String fileName) {
        TransformerFactory tf = TransformerFactory.newInstance(); //creates an instance of a Transformer factory for xml shenanigans
        try {
            //An instance of this abstract class can transform a source tree into a result tree
            Transformer transformer = tf.newTransformer();      //creates a new tranformer from the factory

            //Acts as a holder for a transformation Source tree in the form of a Document Object Model (DOM) tree.
            DOMSource dSource = new DOMSource(doc);

            //cts as an holder for a transformation result, which may be XML
            StreamResult sResult = new StreamResult(new File(fileName));//streamResult for file.

            //Set an output property that will be in effect for the transformation
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setAttribute("indent-number", 5);
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                    "5");//figure out why the fuck you need that
            transformer.transform(dSource, sResult);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static String getSaveFilePath(String saveFileName) {
        return (System.getProperty("user.dir") + File.separatorChar + "res" + File.separatorChar + "saveFiles" + File.separatorChar + saveFileName + ".sav");
    }
}
