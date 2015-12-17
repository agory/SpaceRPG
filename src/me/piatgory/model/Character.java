package me.piatgory.model;

import me.piatgory.model.Item.Item;
import me.piatgory.model.Item.Weapon;

/**
 * Created by Grégoire on 10/12/2015.
 */
public class Character {

    private String name;
    private double health;
    private double stamina; //Résistance
    private double power;
    private double accurancy;
    private double weight;
    private double maxHealth;
    private double maxDexterity;



    public Character(){

    }

    public Character(String name, double health, double stamina, double power, double accurancy, double weight){
        this.name = name;
        this.health = health;
        this.stamina = stamina;
        this.power = power;
        this.accurancy = accurancy;
        this.weight = weight;
    }

    public void applyBuff(Buff buff){

    }

    public void equipWeapon(Weapon weapon){

    }

    public void equipArmor(Armor armor){

    }

    public int getInventoryWeight(){

    }

    public void removeInventory(Item item){

    }

    public void addInventory(Item item){

    }

    public void computeMaxHealth(){

    }

    public void computeMaxDexterity(){

    }

    public void incrementLevel(){

    }

    public void sumCarac(){

    }

    public int getValueCarac(Stats stats){

    }

    public void initStats(){

    }

    public void checkStats(){

    }

    public void initCapacity(){

    }
}
