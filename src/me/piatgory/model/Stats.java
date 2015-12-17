package me.piatgory.model;

/**
 * Created by Grégoire on 17/12/2015.
 */
public enum Stats {
    Stamina(0), Strenght(0), Power(0),
    health(0), intelligence(0);//...

    private int value;

    Stats(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
