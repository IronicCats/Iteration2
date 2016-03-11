package Utilities;

import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Characters.FriendlyNPC;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Sneak;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Summoner;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Inventory.Equipment;
import Model.Inventory.EquipmentSlotEnum;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Map.Map;

import javax.swing.text.html.HTMLDocument;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Model.Map.Tile;
import Model.Map.Tiles.Grass;
import Model.Map.Tiles.Mountain;
import Model.Map.Tiles.Water;
import Model.Stats.*;
import State.States.GameState.GameState;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import View.ViewUtilities.Graphics.Assets;
import View.Views.MapView;
import View.Views.MobileObjectView;
import View.Views.TileView;
import org.w3c.dom.*;
import org.xml.sax.SAXParseException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Andy on 3/5/2016.
 */
public class SaveLoad {


    private static String currFileName;     //the name of the file that will be saved.
    public static SaveLoad instance = new SaveLoad(); //an instance of SaveLoad, only needs one.
    private static Entity player;   //will probably need an Entity list
    private static Map gameMap;     //list of all maps may be needed
    private static GameState game;
    private static MapView gamemapView;
    private static HashMap<MobileObject, MobileObjectView> mobileObjects;

    //private static final String filePathExtension = Utilities.getFileSystemDependentPath("src/res/saveFiles";)

    public static Entity getPlayer(){// this will be changed later
        return player;

    }
    public static Map getGameMap(){// to save the map
        return gameMap;
    }

    public static MapView getGamemapView(){
        return gamemapView;
    }
    

    public static void setGameMap(Map map){// sets the current map
        gameMap = map;
    }
    public static void setPlayer(Entity a){//sets the current player
        player = a;
    }
    public static void setGamemapView(MapView m){
        gamemapView = m;
    }
    public static void setMobileObjects(HashMap<MobileObject,MobileObjectView> mobileObjectMap){
        mobileObjects = mobileObjectMap;
    }

    public static SaveLoad getInstance() {//returns the instance of SaveLoad
        return instance;
    }
    //---------------------------------LOAD--------------------------------// ヽ༼ຈل͜ຈ༽ﾉ
    public static void load(String fileName){
        currFileName = fileName;
        //String filePath = filePathExtension + fileName;
        String filePath = "res/saveFiles/" + fileName;
        loadMap(gameMap,filePath);  //gameMap may be wrong, need to check this
        loadPlayer(filePath);
        System.out.println("Everything has been loaded!");
    }

    public static void loadMap(Map inputMap,String fileName)
    {

        String filepath = fileName;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filepath));

            doc.getDocumentElement().normalize(); //this normalizes it, idk what that means though lol

            NodeList mapList = doc.getElementsByTagName("map");
            Element map = (Element)mapList.item(0); //need to change this for working with multiple maps
            int mapWidth = Integer.parseInt(map.getAttribute("width"));
            int mapHeight = Integer.parseInt(map.getAttribute("height"));

            //if there is problem it may be here
            Tile[][] tiles = new Tile[mapWidth][mapHeight]; //this might not be good in our implementation
            TileView[][] tv = new TileView[mapWidth][mapHeight];
            //was mapHeight/mapWitdth

            NodeList rows = doc.getElementsByTagName("row");

            for(int i = 0; i <rows.getLength(); i++){
                Element row = (Element) rows.item(i); //for each row in i
                NodeList tileNodes = row.getElementsByTagName("tile");

                for(int j = 0; j < tileNodes.getLength(); j++){
                    Element tileElement = (Element) tileNodes.item(j);



                    Element terrainElement = (Element) tileElement.getElementsByTagName("terrain").item(0); //other thing has item(0)
                    String terrainType = terrainElement.getAttribute("terrainType");
                    //how are we telling something that it's a grass/water/mountain?


                    AreaEffect areaEffect = null;       //blank areaEffect to fill in
                    NodeList areaEffectNodes = tileElement.getElementsByTagName("areaEffect");
                    if(areaEffectNodes.getLength() > 0){
                        Element areaEffectElement = (Element) areaEffectNodes.item(0);
                        String areaEffectEnum = areaEffectElement.getAttribute("enum");

                        switch(areaEffectEnum){
                            //a case for each enum
                        }

                    }
                    //decal stuff but I don't even have that saving yet

                    Item[] itemArray = new Item[10];//check this
                    NodeList itemNodes = tileElement.getElementsByTagName("item");
                    if(itemNodes.getLength() > 0){
                        //this is very iffy at the moment
                        for(int k = 0; k < itemNodes.getLength();k++)
                        {
                            Element itemElement = (Element) itemNodes.item(k);
                            String itemID = itemElement.getAttribute("id");
                            int id = Integer.parseInt(itemID);
                            //id = itemsEnum.
                            //ItemsEnum.AGILITY_POTION.ordinal

                            Location l = new Location(0,0,270);
                            l.setX(i);
                            l.setY(j);

                            //I'm probably going to need some huge if statement or something. Idk

                            switch(id){
                                case 0:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.HEALTH_POTION,l);
                                    break;
                                case 1:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.MANA_POTION,l);
                                    break;
                                case 2:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.STRENGTH_POTION,l);
                                    break;
                                case 3:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.AGILITY_POTION,l);
                                    break;
                                case 4:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.INTELLECT_POTION,l);
                                    break;
                                case 5:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.HARDINESS_POTION,l);
                                    break;
                                case 6:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.EXPERIENCE_POTION,l);
                                    break;
                                case 7:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.MOVEMENT_POTION,l);
                                    break;
                                case 8:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.STICK_SWORD,l);
                                    break;
                                case 9:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.STICK_GREATSWORD,l);
                                    break;
                                case 10:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.SWORDFISH_DAGGER,l);
                                    break;
                                case 11:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.SWORDFISH_LANCE,l);
                                    break;
                                case 12:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.PUFFER_FISH_MACE,l);
                                    break;
                                case 13:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.PUFFER_FISH_FLAIL,l);
                                    break;
                                case 14:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.MOUSE_ON_A_STRING_WAND,l);
                                    break;
                                case 15:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.CATNIP_STAFF,l);
                                    break;
                                case 16:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.HAIRBALL,l);
                                    break;
                                case 17:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.LASER_POINTER,l);
                                    break;
                                case 18:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.FISH_BOOMERANG,l);
                                    break;
                                case 19:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.CHEST_KEY,l);
                                    break;
                                case 20:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.OPEN_TREASURE_CHEST,l);
                                    break;
                                case 21:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.CLOSED_TREASURE_CHEST,l);
                                    break;
                                case 22:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.DOOR_KEY,l);
                                    break;
                                case 23:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.OPEN_DOOR,l);
                                    break;
                                case 24:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.CLOSED_DOOR,l);
                                    break;
                                case 25:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.HELMET,l);
                                    break;
                                case 26:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.CHESTPLATE,l);
                                    break;
                                case 27:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.PLATELEGS,l);
                                    break;
                                case 28:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.GAUNTLETS,l);
                                    break;
                                case 29:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.BOOTS,l);
                                    break;
                                case 30:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.SHIELD,l);
                                    break;
                                case 31:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.MILDLY_COOL_RING,l);
                                    break;
                                case 32:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.DOPE_RING,l);
                                    break;
                                case 33:
                                    itemArray[k]  = ItemFactory.makeItem(ItemsEnum.PANTS,l);
                                    break;


                            }


                        }

                    }
                    Location lg = new Location(i,j);
                    System.out.println("This is the terrain type of " + i + "," + j + " " + terrainType);
                    if(terrainType.equalsIgnoreCase("grass")) {
                        System.out.println("I get the grass.");
                        tiles[i][j] = new Grass(lg);
                        tv[i][j] = new TileView(tiles[i][j], Assets.GRASSHEXTILE );

                    }
                    else if(terrainType.equalsIgnoreCase("water")) {
                        System.out.println("I get the water.");
                        tiles[i][j] = new Water(lg);
                        tv[i][j] = new TileView(tiles[i][j], Assets.WATERHEXTILE );

                    }
                    else if(terrainType.equalsIgnoreCase("mountain")) {
                        tiles[i][j] = new Mountain(lg);
                        tv[i][j] = new TileView(tiles[i][j], Assets.MOUNTAINHEXTILE );
                    }
                    //System.out.println(tiles[i][j].toString());

                }
            }
            Location spawn = new Location(0,0);
            spawn.setX(Integer.parseInt(map.getAttribute("spawnX")));
            spawn.setY(Integer.parseInt(map.getAttribute("spawnY")));



            Map recreateMap = new Map(tiles,mapWidth,mapHeight,spawn);
            //SaveLoad.setGameMap(recreateMap);
            gameMap = recreateMap;
            MapView mv = new MapView(recreateMap,tv);

            gamemapView = mv;
            mv.update();
            //inputMap = recreateMap;


           // Map recreateMap = new Map();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Map problems");
        }
    }

    public static void loadPlayer(String fileName)
    {
        // Get the xml filepath string ensuring file separators are specific to the use's OS.
        String file = fileName;
        Entity avatar = player;
        //Map m = IOMediator.getInstance().map;
        try {
            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(file));
            doc.getDocumentElement().normalize();

            NodeList entities = doc.getElementsByTagName("entities"); //might have to be player
            //Checks the type of the avatar and sets information
            //System.out.println("length =" + entities.getLength()); TODO erase
            for (int i = 0; i < entities.getLength(); i++) {
                Element entity = (Element) entities.item(i); // this is a player
                NodeList p = doc.getElementsByTagName("player");
                Element pl = (Element)p.item(0);
               // if (entity.getAttribute("player").equals("player")) {
                    int x = Integer.parseInt(pl.getAttribute("locX"));
                    int y = Integer.parseInt(pl.getAttribute("locY"));
                    int d = Integer.parseInt(pl.getAttribute("direction"));
                    String occupationString = pl.getAttribute("occupation");
                    //System.out.println("Occupation:" + occupation);

                    //System.out.println("x=" + x + " y=" + y + " direction=" + d);
                    Location l = new Location(x,y,d);
                    //Location l = new Location(x,y);
                    //avatar.setLocation(l);
                    System.out.println(player.toString());

                    //player.setLocation(l);
                    //Player p = new Player(l,)

                    //Player peer = new Player()
                            //Assign occupation when creating new player, don't update jesus fucking christ what is wrong with me.
                Occupation occupation = null;
                switch(occupationString) {
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

                //load inventory
                Player peer = new Player();
                //^ this will be location,occupation,inventory,stats


                   /* loadStats(avatar.getStats(), entity); //Separate function to handle loading stats
                    loadInventory(avatar.getInventory(), entity);
                    loadEquipped(avatar.getEquippedItems(), entity);
                    //Adds avatar to the map
                    m.insertEntityAtLocation(x, y, avatar);*/
                //}
            }

            //System.out.println("Finish loading avatar: " + avatar.getLocation()[0] + "," + avatar.getLocation()[1] + "," + avatar.getOrientation());
        } catch (Exception e) {
            System.out.println("Problem parsing avatar");
            e.printStackTrace();
        }
    }

    private static void loadStats(CharacterStats cStats, Element player){
        NodeList temp = player.getElementsByTagName("primary");
        Element prime = (Element)temp.item(0);

        cStats.setStrength(Integer.parseInt(player.getAttribute("strength")));
        cStats.setBaseLife(Integer.parseInt(player.getAttribute("baseLife")));
        cStats.setAgility(Integer.parseInt(player.getAttribute("agility")));
        cStats.setBaseAgi(Integer.parseInt(player.getAttribute("baseAgi")));
        cStats.setBaseStr(Integer.parseInt(player.getAttribute("baseStr")));
        cStats.setIntellect(Integer.parseInt(player.getAttribute("intellect")));
        cStats.setBaseIntel(Integer.parseInt(player.getAttribute("baseIntel")));
        cStats.setBaseMana(Integer.parseInt(player.getAttribute("baseMana")));
        cStats.setExperience(Integer.parseInt(player.getAttribute("experience")));
        cStats.setBaseLives(Integer.parseInt(player.getAttribute("baseLives")));
        cStats.setHardiness(Integer.parseInt(player.getAttribute("hardiness")));
        cStats.setBaseHard(Integer.parseInt(player.getAttribute("baseHard")));
        cStats.setDefensiveRating(Integer.parseInt(player.getAttribute("defense")));
        cStats.setOffensiveRating(Integer.parseInt(player.getAttribute("offense")));
        cStats.setLife(Integer.parseInt(player.getAttribute("life")));
        cStats.setMana(Integer.parseInt(player.getAttribute("mana")));
        cStats.setMovement(Integer.parseInt(player.getAttribute("movement")));
        //xp threshhold?











        //cStats.set
        //StatStructure statStructure = new StatStructure()

        //CharacterStats c = new CharacterStats()

    }
    private static void loadVehicles(String filename){

    }

    private static void loadPets(String filename){
        
    }
    //---------------------------------------------------------------------//
    //                                                                     //
    //                                                                     //
    //---------------------------------SAVE--------------------------------// ヽ༼ຈل͜ຈ༽ﾉ
    public static void save(){//function that will be called when you want to save
        if(currFileName == null)
            currFileName = "test";

            try{
            //Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); //create an instance of a documentBuilderFactory
            //Defines the API to obtain DOM Document instances from an XML document. Using this class, an application programmer can obtain a Document from XML.
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); //creates a documentBuilder from Factory
            Document doc = docBuilder.newDocument();                        //and creates a doc from Builder

            //String filePath = "/res/saveFiles/" + currFileName;              //the filePath is in resource folders plus passed in filename
            String filePath = getSaveFilePath(currFileName);                    //should work for saveFilePath
            //The Element interface represents an element in an XML document
            Element rootElement = doc.createElementNS(filePath,"SaveFile"); //starts the root Element of XML from filePath
            doc.appendChild(rootElement);                                   //adds the child to the doc
            rootElement.appendChild(getMobileObjects(doc,mobileObjects));
            rootElement.appendChild(getPlayerInfo(doc,(Player)player));                 //adds the next root element which is entities
            rootElement.appendChild(getMap(doc,gameMap));

            toXML(doc,filePath);                                            //turns the document into an XML
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    private static Node getMobileObjects(Document doc, HashMap<MobileObject, MobileObjectView> mO){
        Element mobileObjects = doc.createElement("mobileObjects"); // gets entity with createElement
        Attr test = doc.createAttribute("test");
        Iterator it = mO.entrySet().iterator();
        while(it.hasNext()){
            java.util.Map.Entry pair = (java.util.Map.Entry)it.next();
            System.out.println(pair.getKey()+ "=" + pair.getValue());
            if(pair.getKey() instanceof Player){
                getPlayerInfo(doc,(Player)pair.getKey());
            }
            else if(pair.getKey() instanceof Pet){
                getPetInfo(doc,(Pet)pair.getKey());
            }
            else if(pair.getKey() instanceof Vehicle){
                getVehicleInfo(doc,(Vehicle)pair.getKey());
            }
            else if(pair.getKey() instanceof FriendlyNPC){
                //getFriendlyNPCInfo
            }
            else if(pair.getKey() instanceof HostileNPC){
                //getHostileNPCInfo
            }
            //it.remove(); ??? Says it avoids CurrentModificationException
        }
        //test.
        //entity.appendChild(getPlayerInfo(doc,e));//need to make Entity info
        return mobileObjects;
    }
    private static Node getPetInfo(Document doc, Pet p){
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

    private static Node getVehicleInfo(Document doc, Vehicle v){
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

    private static Node getPlayerInfo(Document doc, Player e){
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
        type.appendChild(getCharacterStats(doc,(CharacterStats)e.getStats()));
        type.appendChild(getInventory(doc,e.getInventory()));
        type.appendChild(getEquippedItems(doc,e.getInventory().getEquipment()));
        
        //need to get Primary then secondary
        return type;                //returns it so it can be used in XML
    }

    private static Node getCharacterStats(Document doc, CharacterStats stat){


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



    private static Node getInventory(Document doc, Inventory inv){
        Element inventory = doc.createElement("inventory");


        for(int i = 0; i< 16-inv.getPackSpaceLeft(); i++)
        {
            Element iItem = doc.createElement("item");

            Attr id = doc.createAttribute("id");
            id.setValue(Integer.toString(inv.get(i).getId()));
            iItem.setAttributeNode(id);

            inventory.appendChild(iItem);
        }


        return inventory;
    }



    private static Node getEquippedItems(Document doc, Equipment equipped){
        Element equip = doc.createElement("equipped");
        equip.appendChild(getEquip(doc,equipped,"head"));
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

    private static Node getEquip(Document doc, Equipment equipped,String typeOfItem){
        Element type = doc.createElement(typeOfItem);
        Item item = null;
        switch(typeOfItem){
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
        if(item != null){
            id.setValue(Integer.toString(item.getId()));
        }
        else{
            id.setValue("-1");
        }
        type.setAttributeNode(id);

        return type;
    }

    private static Node getMap(Document doc, Map m){
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
        for(int i = 0; i < getWidth; i++){  //for each in map width
            Element row = doc.createElement("row");//create element row
            for(int j = 0; j < getHeight; j++){ //for each in height
                row.appendChild(getTile(doc,m.getTile(i,j)));          //append each tile into row
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

    private static Node getTile(Document doc, Tile t){
        Element tile = doc.createElement("tile"); //Creates an element with tag tile
        Element terrain = doc.createElement("terrain"); //creates and element for terrain

        Attr type = doc.createAttribute("terrainType");//creates an attribute for terrain
        if(t instanceof Grass)          //checks if it is an instance of grass
            type.setValue("grass");     //if so setValue to grass
        else if(t instanceof Water)     //checks to see if it is an instance of water
            type.setValue("water");     //if os setValue to water
        else if(t instanceof Mountain)  //checks to see if it is an instance of mountain
            type.setValue("mountain");  //if so setValue to mountain
        terrain.setAttributeNode(type); //sets the attribute node to whatever type it ended up
        tile.appendChild(terrain);      //appends the child to the element

        //May have to get Entity? Not 100% sure if that is necessary

        //AreaEffect
        if(t.getHasAreaEffect())// if it has an area effect
        {
            Element areaEffect = doc.createElement("areaEffect");   //creates an element and tags it as areaEffect

            Attr areaEnum = doc.createAttribute("enum");            //creates an attribute called areaEnum

            areaEnum.setValue(t.getAreaEffectEnum().toString());    //sets that attribute as AreaEffectEnum as a string
            areaEffect.setAttributeNode(areaEnum);                  //set AttributeNode of element
            tile.appendChild(areaEffect);                           //appends Node to element;
        }
        //if(t.)
        //Decal

        //item

        if(t.hasItems()){

            Element item = doc.createElement("item"); // creates an element and tags it as a document
            //will probably have to go through each item on a tile since we can have multiple

            Attr itemID = doc.createAttribute("id");
            //gets the id of an item.
            for(int i = 0; i < t.getItems().size(); i++){//note make sure this is correct for size
                itemID.setValue(Integer.toString(t.getItems().get(i).getId()));
                item.setAttributeNode(itemID);      //sets the attribute node to the item id
                tile.appendChild(item);             //appends node to element
            }
            //note, I am pretty sure having it in the loop is right

        }

        return tile; //returns the element
    }

    public static void toXML(Document doc, String fileName){
        TransformerFactory tf = TransformerFactory.newInstance(); //creates an instance of a Transformer factory for xml shenanigans
        try{
            //An instance of this abstract class can transform a source tree into a result tree
            Transformer transformer = tf.newTransformer();      //creates a new tranformer from the factory

            //Acts as a holder for a transformation Source tree in the form of a Document Object Model (DOM) tree.
            DOMSource dSource = new DOMSource(doc);

            //cts as an holder for a transformation result, which may be XML
            StreamResult sResult = new StreamResult(new File(fileName));//streamResult for file.

            //Set an output property that will be in effect for the transformation
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setAttribute("indent-number",5);
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                    "5");//figure out why the fuck you need that
            transformer.transform(dSource,sResult);

        }catch(TransformerException e){
            e.printStackTrace();
        }
    }

    public static String getSaveFilePath(String saveFileName) {
        return (System.getProperty("user.dir") + File.separatorChar + "res" + File.separatorChar + "saveFiles" + File.separatorChar + saveFileName + ".sav");
    }
}
