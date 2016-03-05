package Utilities;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Map.Map;

import javax.swing.text.Document;
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


    public static SaveLoad instance = new SaveLoad();
    private static Entity avatar;   //will probably need an Entity list
    private static Map gameMap;     //list of all maps may be needed

    public static Entity getEntity(){// this will be changed later
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
