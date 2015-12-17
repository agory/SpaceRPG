package me.piatgory.model;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Equipment extends Item {
    private Stats stats;

    public Equipment(int poid, String nom, Stats stats) {
        super(poid, nom);
        this.stats =stats;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
