package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Weapon extends Equipment {
    private int Degat;

    public Weapon(int poid, String nom, Stats stats, int degat) {
        super(poid, nom, stats);
        Degat = degat;
    }

    public int getDegat() {
        return Degat;
    }

    public void setDegat(int degat) {
        Degat = degat;
    }
}
