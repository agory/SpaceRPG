package me.piatgory.model.Item;

import me.piatgory.model.Stat;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class ChestArmor extends Equipment {
    public ChestArmor(int weight, String name, Stat Stat) {
        super(name, weight, Stat);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Torse";
        return message;
    }
}
