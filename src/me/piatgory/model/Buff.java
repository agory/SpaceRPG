package me.piatgory.model;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Buff {
    private String name;
    private enumStat Stat;
    private int healthEffect;

    public Buff(String name, enumStat Stat, int healthEffect) {
        this.name = name;
        this.Stat = Stat;
        this.healthEffect = healthEffect;
    }

    @Override
    public String toString() {
        return name;
    }
}
