package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class LegsArmor extends Equipment{
    public LegsArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = "Emplacement : Jambes\n";
        message += super.toString();
        return message;
    }
}
