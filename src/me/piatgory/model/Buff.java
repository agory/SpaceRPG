package me.piatgory.model;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Buff {
    private String name;
    private Stats stats;
    private int healthEffect;
    private int nbTurn;

    public Buff(String name, Stats stats, int healthEffect, int nbTurn) {
        this.name = name;
        this.stats = stats;
        this.healthEffect = healthEffect;
        this.nbTurn = nbTurn;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getHealthEffect() {
        return healthEffect;
    }

    public void setHealthEffect(int healthEffect) {
        this.healthEffect = healthEffect;
    }

    public int getNbTurn() {
        return nbTurn;
    }

    public void setNbTurn(int nbTurn) {
        this.nbTurn = nbTurn;
    }
}
