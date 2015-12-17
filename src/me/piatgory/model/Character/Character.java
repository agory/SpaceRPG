package me.piatgory.model.Character;

import me.piatgory.model.Buff;
import me.piatgory.model.Item.*;
import me.piatgory.model.Stats;

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
    private Weapon weapon;
    private ChestArmor chestArmor;
    private FootArmor footArmor;
    private HandArmor handArmor;
    private HeadArmor headArmor;
    private LegsArmor legsArmor;


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
        this.weapon = weapon;
    }

    public void equipChestArmor(ChestArmor chestArmor){
        this.chestArmor=chestArmor;
    }

    public void equipFootArmor(FootArmor footArmor){
        this.footArmor=footArmor;
    }

    public void equipHeadArmor(HeadArmor headArmor){
        this.headArmor=headArmor;
    }

    public void equipHandArmor(HandArmor handArmor){
        this.handArmor=handArmor;
    }

    public void equipLegsArmor(LegsArmor legsArmor){
        this.legsArmor=legsArmor;
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
