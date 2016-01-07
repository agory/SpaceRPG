package me.piatgory.model.Character;

/**
 * Created by Grégoire on 15/12/2015.
 */
public class Mercenary extends Character {

}
enum statsMercenary {
    Stamina("Endurance", 0), Power("Puissance", 0),
    Health("Santé", 100);

    private String name;//defaultvalue
    private int value;//defaultvalue

    enumStat(String name, int value) {
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