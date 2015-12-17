package me.piatgory.model;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Arme extends Equipment {
    private int Degat;

    public Arme(int poid, String nom, Stats stats, int degat) {
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
