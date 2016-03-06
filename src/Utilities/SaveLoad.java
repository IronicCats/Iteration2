package Utilities;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Map.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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


    private static String fileName;
    public static SaveLoad instance = new SaveLoad();
    private static Entity avatar;   //will probably need an Entity list
    private static Map gameMap;     //list of all maps may be needed

    public static Entity getAvatar(){// this will be changed later
        return avatar;
    }
    public static Map getGameMap(){
        return gameMap;
    }

    public static void setGameMap(Map map){
        gameMap = map;
    }
    public static void setAvatar(Entity a){
        avatar = a;
    }

    public static SaveLoad getInstance() {
        return instance;
    }

    public static void save(){

        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            String filePath = "/res/saveFiles" + fileName;

            Element rootElement = doc.createElementNS(filePath,"SaveFile");
            doc.appendChild(rootElement);

            rootElement.appendChild(getEntity(doc,avatar));
           // rootElement.appendChild(getMap(doc,gameMap));
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    private static Node getEntity(Document doc, Entity e){
        Element entity = doc.createElement("entities");

        //entity.appendChild(getEntityInfo(doc,e));//need to make Entity info
        return entity;
    }

    public static void toXML(Document doc, String fileName){
        TransformerFactory tf = TransformerFactory.newInstance();
        try{
            Transformer transformer = tf.newTransformer();
            DOMSource dSource = new DOMSource(doc);

            StreamResult sResult = new StreamResult(new File(fileName));

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
