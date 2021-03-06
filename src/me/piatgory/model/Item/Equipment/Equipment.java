package me.piatgory.model.Item.Equipment;

import me.piatgory.model.Item.Item;
import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
@XmlRootElement
public abstract class Equipment extends Item {

    private Stats stats;

    public Equipment(String name, int weight, Stats stats) {
        super(name, weight);
        this.stats = stats;
    }

    public Equipment(String name, Stats stats) {
        super(name);
        this.stats = stats;
    }

    public Equipment(String name,String description, int weight, Stats stats){
        super(name,description,weight);
        this.stats = stats;
    }

    public Equipment(){}

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        String message = super.toString() +"\n";
        message += stats.toString();
        return message;
    }


    public static List<String> getMenuInventaireAction(){
        List<String> actions = new ArrayList<String>();
        actions.add("Afficher");
        actions.add("Equiper");
        actions.add("Jeter");
        return  actions;
    }
}
