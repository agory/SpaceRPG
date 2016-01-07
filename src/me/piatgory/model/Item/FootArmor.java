package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class FootArmor extends Equipment {
    public FootArmor(int weight, String name, Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Pied";
        return message;
    }
}
