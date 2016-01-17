package me.piatgory.persistance;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Alexandre on 09/01/2016.
 */
public class JAXBserializer {
    public static void Save(DataGame dataGame){
        try {
            File file = new File(".\\Data\\datagame.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(DataGame.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(dataGame, file);
            //jaxbMarshaller.marshal(dataGame, System.out);
        }catch (JAXBException e){
            e.printStackTrace();
        }
    }

    public static DataGame Read(){
        DataGame datagame=null;
        try {
            File file = new File(".\\Data\\datagame.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(DataGame.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            datagame = (DataGame) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return datagame;
    }
}
