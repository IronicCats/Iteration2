package Utilities;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Map.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
        String filePath = "src/res/saveFiles" + fileName;
    }



    //---------------------------------SAVE--------------------------------// ヽ༼ຈل͜ຈ༽ﾉ
    public static void save(){//function that will be called when you want to save

        try{
            //Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); //create an instance of a documentBuilderFactory
            //Defines the API to obtain DOM Document instances from an XML document. Using this class, an application programmer can obtain a Document from XML.
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); //creates a documentBuilder from Factory
            Document doc = docBuilder.newDocument();                        //and creates a doc from Builder

            String filePath = "/res/saveFiles" + currFileName;              //the filePath is in resource folders plus passed in filename

            //The Element interface represents an element in an HTML or XML document
            Element rootElement = doc.createElementNS(filePath,"SaveFile"); //starts the root Element of XML from filePath
            doc.appendChild(rootElement);                                   //adds the child to the doc

            rootElement.appendChild(getEntity(doc,player));                 //adds the next root element which is entities
           // rootElement.appendChild(getMap(doc,gameMap));

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

    public static void toXML(Document doc, String fileName){
        TransformerFactory tf = TransformerFactory.newInstance(); //creates an instance of a Transformer factory for xml shenanigans
        try{
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
}
