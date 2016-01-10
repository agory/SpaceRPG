package me.piatgory.model.Item;

import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Grégoire on 17/12/2015.
 */
@XmlRootElement
public class FootArmor extends Equipment {
    public FootArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }
    public FootArmor(String name,String description,int weight , Stats stats) {
        super(name,description ,weight, stats);
    }

    public FootArmor(){}
    @Override
    public String toString() {
        String message = "Emplacement : Pied\n";
        message += super.toString();

        return message;
    }
}
