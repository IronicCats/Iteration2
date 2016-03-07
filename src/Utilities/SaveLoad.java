package Utilities;

import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Map.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Model.Map.Tile;
import Model.Map.Tiles.Grass;
import Model.Map.Tiles.Mountain;
import Model.Map.Tiles.Water;
import State.States.GameState.GameState;
import org.w3c.dom.*;
import org.xml.sax.SAXParseException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by Andy on 3/5/2016.
 */
public class SaveLoad {


    private static String currFileName;     //the name of the file that will be saved.
    public static SaveLoad instance = new SaveLoad(); //an instance of SaveLoad, only needs one.
    private static Entity player;   //will probably need an Entity list
    private static Map gameMap;     //list of all maps may be needed
    private static GameState game;

    //private static final String filePathExtension = Utilities.getFileSystemDependentPath("src/res/saveFiles";)

    public static Entity getPlayer(){// this will be changed later
        return player;

    }
    public static Map getGameMap(){// to save the map
        return gameMap;
    }

    public static void setGameMap(Map map){// sets the current map
        gameMap = map;
    }
    public static void setPlayer(Entity a){//sets the current player
        player = a;
    }

    public static SaveLoad getInstance() {//returns the instance of SaveLoad
        return instance;
    }
    //---------------------------------LOAD--------------------------------// ヽ༼ຈل͜ຈ༽ﾉ
    public static void load(String fileName){
        currFileName = fileName;
        //String filePath = filePathExtension + fileName;
        String filePath = "src/res/saveFiles/" + fileName;
        loadMap(gameMap,filePath);  //gameMap may be wrong, need to check this
        loadPlayer(filePath);
    }

    public static void loadMap(Map inputMap,String fileName)
    {

        String filepath = "src/res/saveFiles/" + fileName;
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
            Tile[][] tiles = new Tile[mapHeight][mapWidth]; //this might not be good in our implementation

            NodeList rows = doc.getElementsByTagName("row");

            for(int i = 0; i <rows.getLength(); i++){
                Element row = (Element) rows.item(i); //for each row in i
                NodeList tileNodes = row.getElementsByTagName("tile");

                for(int j = 0; j < tileNodes.getLength(); j++){
                    Element tileElement = (Element) tileNodes.item(j);

                    AreaEffect areaEffect = null;

                    Element terrainElement = (Element) tileElement.getElementsByTagName("terrain").item(0); //other thing has item(0)

                    
                }
            }



           // Map recreateMap = new Map();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadPlayer(String fileName)
    {

    }



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

            rootElement.appendChild(getEntity(doc,player));                 //adds the next root element which is entities
            rootElement.appendChild(getMap(doc,gameMap));

            toXML(doc,filePath);                                            //turns the document into an XML
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    private static Node getEntity(Document doc, Entity e){
        Element entity = doc.createElement("entities"); // gets entity with createElement

        entity.appendChild(getEntityInfo(doc,e));//need to make Entity info
        return entity;
    }

    private static Node getEntityInfo(Document doc, Entity e){
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

        return type;                //returns it so it can be used in XML
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
        System.out.println("Right before it should see item.");
        if(t.hasItems()){
            System.out.println("It should see the item");
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
