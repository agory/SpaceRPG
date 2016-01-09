package me.piatgory.model.Entity;

import me.piatgory.model.*;
import me.piatgory.model.Item.*;

import java.util.HashMap;


/**
 * Created by Grégoire on 10/12/2015.
 */
public class Character extends Entity{

    private Weapon weapon;
    private ChestArmor chestArmor;
    private FootArmor footArmor;
    private HandArmor handArmor;
    private HeadArmor headArmor;
    private LegsArmor legsArmor;
    private Inventory inventory;



    public Character(String name){
        super(name,1);
        this.inventory = new Inventory();
        this.currentHealth = computeMaxHealth();
    }

    public Stats computeAllStats(){
        Stats stats = new Stats();
        stats.merge(this.stats);
        if(this.weapon != null)
            stats.merge(weapon.getStats());
        if(this.chestArmor != null)
            stats.merge(chestArmor.getStats());
        if(this.footArmor != null)
            stats.merge(footArmor.getStats());
        if(this.handArmor != null)
            stats.merge(handArmor.getStats());
        if(this.headArmor != null)
            stats.merge(headArmor.getStats());
        if(this.legsArmor != null)
            stats.merge(legsArmor.getStats());
        return stats;
    }

    public void applyBuff(Buff buff){

    }
    /*
    * Le poids maximun portable augment en fonction de la puissance du personnage
    * */
    public int getWeight(){
        return this.computeAllStats().getStats().get("Puissance")*5;
    }

    public int getDamage(){
        if(this.weapon != null)
            return this.weapon.getDamage(this.stats);
        else
            return this.computeAllStats().getStats().get("Puissance");
    }

    public void equipWeapon(Weapon weapon){
        this.balanceCurrentHealth((this.weapon != null) ? this.weapon.getStats() : null, (weapon != null) ? weapon.getStats() : null);
        this.weapon = weapon;
    }

    public void equipChestArmor(ChestArmor chestArmor){
        this.balanceCurrentHealth((this.chestArmor != null) ? this.chestArmor.getStats() : null,(chestArmor != null) ? chestArmor.getStats() : null);
        this.chestArmor=chestArmor;
    }

    public void equipFootArmor(FootArmor footArmor){
        this.balanceCurrentHealth((this.footArmor != null) ? this.footArmor.getStats() : null,(footArmor != null) ? footArmor.getStats() : null);
        this.footArmor=footArmor;
    }

    public void equipHeadArmor(HeadArmor headArmor){
        this.balanceCurrentHealth((this.headArmor != null) ? this.headArmor.getStats() : null,(headArmor != null) ? headArmor.getStats() : null);
        this.headArmor=headArmor;
    }

    public void equipHandArmor(HandArmor handArmor){
        this.balanceCurrentHealth((this.handArmor != null) ? this.handArmor.getStats() : null,(handArmor != null) ? handArmor.getStats() : null);
        this.handArmor=handArmor;
    }

    public void equipLegsArmor(LegsArmor legsArmor){
        this.balanceCurrentHealth((this.legsArmor != null) ? this.legsArmor.getStats() : null,(legsArmor != null) ? legsArmor.getStats() : null);
        this.legsArmor=legsArmor;
    }

    public int getInventoryWeight(){
        return inventory.getWeight();
    }

    public void removeFromInventory(Item item){
        inventory.removeItem(item);
    }

    public void addToInventory(Item item) throws Exception{
        if(this.inventory.getWeight() + item.getWeight() > this.getWeight())
            throw new Exception("Inventaire trop plein. Impossible de prendre l'item.");
        inventory.addItem(item);
    }

    public void incrementLevel(){
        this.setLevel(this.getLevel()+1);
        for (StatsCharacter stat:StatsCharacter.values()) {
            this.stats.getStats().put(
                    stat.getName(),
                    this.stats.getStats().get(stat.getName()) + stat.getValuePerLevel()
            );
        }
    }
    public void buildStats(){
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        for (StatsCharacter stat:StatsCharacter.values()) {
            stats.put(stat.getName(),stat.getValue());
        }
        this.stats = new Stats(stats);
    }

    public String showEquipement(){
        String message = "Equipement : ";
        if(this.weapon != null)
            message+=  "\n - " +weapon.toString();
        if(this.chestArmor != null)
            message+=  "\n - " + chestArmor.toString();
        if(this.footArmor != null)
            message+=  "\n - " + footArmor.toString();
        if(this.handArmor != null)
            message+=  "\n - " + handArmor.toString();
        if(this.headArmor != null)
            message+=  "\n - " + headArmor.toString();
        if(this.legsArmor != null)
            message+=  "\n - " + legsArmor.toString();
        return message;
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "\n" + showEquipement();
        return message;
    }
}

enum StatsCharacter {
    Stamina("Endurance", 1,1), Power("Puissance",20,5),
    Health("Santé", 100,100);

    private String name;//defaultvalue
    private int value;//defaultvalue
    private int valuePerLevel;

    StatsCharacter(String name, int value,int valuePerLevel) {
        this.name = name;
        this.value = value;
        this.valuePerLevel=valuePerLevel;
    }

    public int getValue() {
        return this.value;
    }
    public int getValuePerLevel() {
        return this.valuePerLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}