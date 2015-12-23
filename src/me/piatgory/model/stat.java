package me.piatgory.model;

/**
 * Created by Grégoire on 17/12/2015.
 */
public enum Stat {
    Stamina("Endurance", 0), Strenght("Force", 0), Power("Puissance", 0),
    Health("Santé", 0), Intelligence("Intelligence", 0);//...

    private String name;//defaultvalue
    private int value;//defaultvalue

    Stat(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
