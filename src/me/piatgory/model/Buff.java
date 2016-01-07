package me.piatgory.model;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Buff {
    private String name;
    private Stats stats;
    private int healthEffect;
    private int nbTurn;

    public Buff(String name, enumStat Stat, int healthEffect, int nbTurn) {
        this.name = name;
        this.stat = stats;
        this.healthEffect = healthEffect;
        this.nbTurn = nbTurn;
    }

    @Override
    public String toString() {
        return name;
    }
}
