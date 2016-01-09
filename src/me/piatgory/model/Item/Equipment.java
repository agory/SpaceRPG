package me.piatgory.model.Item;

import me.piatgory.model.Buff;
import me.piatgory.model.Stats;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
abstract class Equipment extends Item {
    private Stats stats;

    public Equipment(String name, int weight, Stats stats) {
        super(name, weight);
        this.stats = stats;
    }

    public Equipment(String name, Stats stats) {
        super(name);
        this.stats = stats;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        String message = super.toString() +"\n";
        message += stats.toString();
        return message;
    }
}
