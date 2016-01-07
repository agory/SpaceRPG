package me.piatgory.model.Item;

import me.piatgory.model.enumStat;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class HandArmor extends Equipment{
    public HandArmor(int weight, String name, enumStat Stat) {
        super(name, weight, Stat);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Mains";
        return message;
    }
}
