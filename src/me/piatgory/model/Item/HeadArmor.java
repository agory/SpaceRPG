package me.piatgory.model.Item;

import me.piatgory.model.Stat;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class HeadArmor extends Equipment {
    public HeadArmor(int weight, String name, Stat Stat) {
        super(name, weight, Stat);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Tête";
        return message;
    }
}
