package me.piatgory.model.Item;

import me.piatgory.model.Buff;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grégoire on 10/12/2015.
 */
@XmlRootElement
public class Item {

    private static int ID = 0;
    private int id;
    private String name;
    private int weight;
    private String descritption;

    /*
    * Setter and Getter
    * */

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    * Constructor
    * */
    public Item(String name,String descritption, int weight) {
        this.name = name;
        this.weight = weight;
        this.id = ID++;
        this.descritption = descritption;
    }

    public Item(String name, int weight) {
        this(name,"Pas de description",weight);
    }

    public Item(String name) {
        this(name,1);
    }

    public Item(){ID++;}

    /*
    * Method
    * */
    @Override
    public String toString() {
        String message = "Nom : " + name;
        message = message + "\nPoids : " + weight;
        message = message + "\nDescription :" + descritption;
        return message;
    }

    public static void resetID(){
        ID=0;
    }

    public static List<String> getMenuInventaireAction(){
        List<String> actions = new ArrayList<String>();
        actions.add("Afficher");
        actions.add("Jeter");
        return  actions;
    }

}
