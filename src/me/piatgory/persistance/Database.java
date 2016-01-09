package me.piatgory.persistance;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Item.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Created by gorya on 07/01/2016.
 */
public class Database {
    private XStream xstream;

    public Database(){
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("Character",  Character.class);
        xstream.alias("Item",       Item.class);
        xstream.alias("ChestArmor", ChestArmor.class);
        xstream.alias("ChestArmor", Weapon.class);
        xstream.alias("LegsArmor",  LegsArmor.class);
        xstream.alias("FootArmor",  FootArmor.class);
        xstream.alias("HandArmor",  HandArmor.class);
        xstream.alias("HeadArmor",  HeadArmor.class);
    }

    public void SaveItem(List<Item> items)/*throws Exception*/{
        String xml = this.xstream.toXML(items);
        /*FileWriter writer = null;
        try{
            writer = new FileWriter("item.xml", true);
            writer.write(xml,0,xml.length());
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            if(writer != null){
                writer.close();
            }
        }*/

    }

    public List<Item> OpenItem() throws Exception{
        String filePath = "item.xml";
        Scanner scanner= new Scanner(new File(filePath));
        String xml ="";
// On boucle sur chaque champ detecté
        while (scanner.hasNextLine()) {
            xml+= scanner.nextLine();
        }        scanner.close();
        return (List<Item>)xstream.fromXML(xml);
    }
}
