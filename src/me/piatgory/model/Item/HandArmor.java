package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class HandArmor extends Equipment{
    public HandArmor(int weight, String name, Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Mains";
        return message;
    }
}
