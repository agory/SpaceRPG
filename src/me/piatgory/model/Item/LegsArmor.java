package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class LegsArmor extends Equipment{
    public LegsArmor(int weight, String name, Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Jambes";
        return message;
    }
}
