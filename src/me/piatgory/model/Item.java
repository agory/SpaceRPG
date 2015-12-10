package me.piatgory.model;

/**
 * Created by Grégoire on 10/12/2015.
 */
public class Item {
    private String nom;
    private int poid;

    public Item(int poid, String nom) {
        this.poid = poid;
        this.nom = nom;
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
