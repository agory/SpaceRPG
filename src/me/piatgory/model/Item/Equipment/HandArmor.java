package me.piatgory.model.Item.Equipment;

import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Grégoire on 17/12/2015.
 */
@XmlRootElement
public class HandArmor extends Equipment{

    public HandArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }

    public HandArmor(String name,String description,int weight , Stats stats) {
        super(name,description ,weight, stats);
    }

    public HandArmor(){}

    @Override
    public String toString() {
        String message = "Emplacement : Mains\n";
        message += super.toString();
        return message;
    }

}
