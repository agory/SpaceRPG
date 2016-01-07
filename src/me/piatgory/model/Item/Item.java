package me.piatgory.model.Item;

import me.piatgory.model.Buff;

/**
 * Created by Grégoire on 10/12/2015.
 */
public class Item {
    private String name;
    private int weight;
    private Buff buff;
    private String descritption;

    public Item(String name, int weight, Buff buff) {
        this.name = name;
        this.weight = weight;
        this.buff = buff;
    }

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.buff = null;
    }

    public Item(String name) {
        this.name = name;
        this.weight = 1;
        this.buff = null;
    }

    public String getNom() {
        return name;
    }

    public void setNom(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public boolean haveBuff() {
        if (buff != null)
            return true;
        else
            return false;
    }

    public Buff ActivateBuff() {
        return this.buff;
    }

    @Override
    public String toString() {
        String message = "Nom : " + name;
        message = message + "\nPoids : " + weight;
        message = message + "\nDescription :" + descritption;
        return message;
    }
}
