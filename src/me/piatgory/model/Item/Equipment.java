package me.piatgory.model.Item;

import me.piatgory.model.Buff;
import me.piatgory.model.Stats;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Equipment extends Item {
    private Stats stats;

    public Equipment(String name, int weight, Buff buff, Stats stats) {
        super(name, weight, buff);
        this.stats = stats;
    }

    public Equipment(String name, int weight, Stats stats) {
        super(name, weight);
        this.stats = stats;
    }

    public Equipment(String name, Stats stats) {
        super(name);
        this.stats = stats;
    }

    public Stats getstats() {
        return stats;
    }

    public void setstats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += stats.toString();
        return message;
    }
}
