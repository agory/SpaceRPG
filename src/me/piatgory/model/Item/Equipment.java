package me.piatgory.model.Item;

import me.piatgory.model.Buff;
import me.piatgory.model.Stats;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Equipment extends Item {
    private Stats stats;

    public Equipment(String nom, int poid, Buff buff, Stats stats) {
        super(nom, poid, buff);
        this.stats = stats;
    }

    public Equipment(String nom, int poid, Stats stats) {
        super(nom, poid);
        this.stats = stats;
    }

    public Equipment(String nom, Stats stats) {
        super(nom);
        this.stats = stats;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
