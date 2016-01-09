package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class HandArmor extends Equipment{
    public HandArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = "Emplacement : Mains\n";
        message += super.toString();
        return message;
    }
}
