package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class ChestArmor extends Equipment {
    public ChestArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = "Emplacement : Torse\n";
        message += super.toString();

        return message;
    }
}
