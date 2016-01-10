package me.piatgory.model.Item;

import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
@XmlRootElement
public class Weapon extends Equipment {

    private int damage;

    public Weapon(String name,int weight , Stats stats, int damage) {
        super(name, weight, stats);
        this.setDamage(damage);
    }

    public Weapon(String name,String description,int weight , Stats stats, int damage) {
        super(name, description,weight, stats);
        this.setDamage(damage);
    }

    public Weapon(){}

    public int getDamage(Stats stats) {
        return damage + stats.getStats().get("Puissance");
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        String message = "Emplacement : Arme\n";
        message += super.toString();
        message += "\nDégat bonus de l'arme : " + damage;
        return message;
    }

    public int attack(Stats stats){
        return damage + stats.getStats().get("Power");
    }
}
