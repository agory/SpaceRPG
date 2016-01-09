package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class FootArmor extends Equipment {
    public FootArmor(String name,int weight , Stats stats) {
        super(name, weight, stats);
    }

    @Override
    public String toString() {
        String message = "Emplacement : Pied\n";
        message += super.toString();

        return message;
    }
}
