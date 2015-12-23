package me.piatgory.model.Item;

import me.piatgory.model.Buff;
import me.piatgory.model.Stat;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Equipment extends Item {
    private Stat Stat;

    public Equipment(String name, int weight, Buff buff, Stat Stat) {
        super(name, weight, buff);
        this.Stat = Stat;
    }

    public Equipment(String name, int weight, Stat Stat) {
        super(name, weight);
        this.Stat = Stat;
    }

    public Equipment(String name, Stat Stat) {
        super(name);
        this.Stat = Stat;
    }

    public Stat getStat() {
        return Stat;
    }

    public void setStat(Stat Stat) {
        this.Stat = Stat;
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += Stat.toString();
        return message;
    }
}
