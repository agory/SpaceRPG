package me.piatgory.model.Item;

import me.piatgory.model.Buff;
import me.piatgory.model.enumStat;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Equipment extends Item {
    private enumStat Stat;

    public Equipment(String name, int weight, Buff buff, enumStat Stat) {
        super(name, weight, buff);
        this.Stat = Stat;
    }

    public Equipment(String name, int weight, enumStat Stat) {
        super(name, weight);
        this.Stat = Stat;
    }

    public Equipment(String name, enumStat Stat) {
        super(name);
        this.Stat = Stat;
    }

    public enumStat getStat() {
        return Stat;
    }

    public void setStat(enumStat Stat) {
        this.Stat = Stat;
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += Stat.toString();
        return message;
    }
}
