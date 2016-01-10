package me.piatgory.model.Item;

import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Grégoire on 17/12/2015.
 */
@XmlRootElement
public class LegsArmor extends Equipment{

    public LegsArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }

    public LegsArmor(String name,String description,int weight , Stats stats) {
        super(name,description ,weight, stats);
    }

    public LegsArmor(){}

    @Override
    public String toString() {
        String message = "Emplacement : Jambes\n";
        message += super.toString();
        return message;
    }
}
