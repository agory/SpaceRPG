package me.piatgory.model.Character;

import me.piatgory.model.Buff;
import me.piatgory.model.Inventory;
import me.piatgory.model.Item.*;
import me.piatgory.model.Stats;
import me.piatgory.model.enumStat;

/**
 * Created by Grégoire on 10/12/2015.
 */
public class Character {

    private String name;
    private double health;
    private double stamina; //Résistance
    private double weight; //Poids
    private double maxHealth;
    private int level;
    private Weapon weapon;
    private ChestArmor chestArmor;
    private FootArmor footArmor;
    private HandArmor handArmor;
    private HeadArmor headArmor;
    private LegsArmor legsArmor;
    private Inventory inventory;

    public Character(){

    }

    public Character(String name, double health, double stamina, double power, double accurancy, double weight){
        this.name = name;
        this.health = health;
        this.stamina = stamina;
        this.weight = weight;
        this.inventory = new Inventory();
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
        return inventory.getWeight();
    }

    public void removeFromInventory(Item item){
        inventory.removeItem(item);
    }

    public void addToInventory(Item item){
        inventory.addItem(item);
    }

    public void computeMaxHealth(){

    }

    public void incrementLevel(){
        level++;
    }

    public int getValueCarac(Stats Stat) {
        int value = 0;
        return value;
    }

    public void initCapacity(){

    }
}
