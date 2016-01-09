package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class HeadArmor extends Equipment {
    public HeadArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = "Emplacement : Tête\n";
        message += super.toString();
        return message;
    }
}
