package me.piatgory.model.Item;

import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Grégoire on 17/12/2015.
 */
@XmlRootElement
public class ChestArmor extends Equipment {
    public ChestArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }
    public ChestArmor(String name,String description,int weight , Stats stats) {
        super(name,description ,weight, stats);
    }
    public ChestArmor(){}
    @Override
    public String toString() {
        String message = "Emplacement : Torse\n";
        message += super.toString();

        return message;
    }
}
