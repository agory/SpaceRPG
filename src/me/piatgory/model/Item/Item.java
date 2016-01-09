package me.piatgory.model.Item;

import me.piatgory.model.Buff;

/**
 * Created by Grégoire on 10/12/2015.
 */
public class Item {

    private static int ID = 0;

    private int id;
    private String name;
    private int weight;
    private String descritption;

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Item(String name) {
        this.name = name;
        this.weight = 1;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescritption() {
        return descritption;
    }

    @Override
    public String toString() {
        String message = "Nom : " + name;
        message = message + "\nPoids : " + weight;
        message = message + "\nDescription :" + descritption;
        return message;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
