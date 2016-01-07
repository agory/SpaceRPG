package me.piatgory.model.Item;

import me.piatgory.model.Stats;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Weapon extends Equipment {
    private int damage;

    public Weapon(int weight, String name, Stats stats, int damage) {
        super(name, weight, stats);
        this.setDamage(damage);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\nEmplacement : Arme";
        message += "\nDégat bonus de l'arme : " + damage;
        return message;
    }

    public int attack(Stats stats){
        return damage + stats.getStats().get("Power");
    }
}
