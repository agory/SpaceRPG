package me.piatgory.model.Item.Equipment;

import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Grégoire on 17/12/2015.
 */
@XmlRootElement
public class HeadArmor extends Equipment {

    public HeadArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }

    public HeadArmor(String name,String description,int weight , Stats stats) {
        super(name,description ,weight, stats);
    }

    public HeadArmor(){}

    @Override
    public String toString() {
        String message = "Emplacement : Tête\n";
        message += super.toString();
        return message;
    }
}
