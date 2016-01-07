package me.piatgory.model.Item;

import me.piatgory.model.enumStat;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class ChestArmor extends Equipment {
    public ChestArmor(int weight, String name, enumStat Stat) {
        super(name, weight, Stat);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Torse";
        return message;
    }
}
