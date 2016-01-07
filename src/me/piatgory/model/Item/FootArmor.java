package me.piatgory.model.Item;

import me.piatgory.model.enumStat;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class FootArmor extends Equipment {
    public FootArmor(int weight, String name, enumStat Stat) {
        super(name, weight, Stat);
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Pied";
        return message;
    }
}
