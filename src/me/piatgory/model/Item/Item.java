package me.piatgory.model.Item;

import me.piatgory.model.Buff;

/**
 * Created by Grégoire on 10/12/2015.
 */
public class Item {
    private String nom;
    private int poid;
    private Buff buff;

    public Item(String nom, int poid, Buff buff) {
        this.nom = nom;
        this.poid = poid;
        this.buff = buff;
    }

    public Item(String nom, int poid) {
        this.nom = nom;
        this.poid = poid;
        this.buff = null;
    }

    public Item(String nom) {
        this.nom = nom;
        this.poid = 1;
        this.buff = null;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }
}
