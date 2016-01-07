package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class HeadArmor extends Equipment {
    public HeadArmor(int weight, String name, Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Tête";
        return message;
    }
}
