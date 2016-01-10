package me.piatgory.model.Entity;

import me.piatgory.model.*;
import me.piatgory.model.Item.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;


/**
 * Created by Grégoire on 10/12/2015.
 */
@XmlRootElement
public class Character extends Entity{

    private Weapon weapon;
    private ChestArmor chestArmor;
    private FootArmor footArmor;
    private HandArmor handArmor;
    private HeadArmor headArmor;
    private LegsArmor legsArmor;
    private Inventory inventory;
    private int experience;


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ChestArmor getChestArmor() {
        return chestArmor;
    }

    public void setChestArmor(ChestArmor chestArmor) {
        this.chestArmor = chestArmor;
    }

    public FootArmor getFootArmor() {
        return footArmor;
    }

    public void setFootArmor(FootArmor footArmor) {
        this.footArmor = footArmor;
    }

    public HandArmor getHandArmor() {
        return handArmor;
    }

    public void setHandArmor(HandArmor handArmor) {
        this.handArmor = handArmor;
    }

    public HeadArmor getHeadArmor() {
        return headArmor;
    }

    public void setHeadArmor(HeadArmor headArmor) {
        this.headArmor = headArmor;
    }

    public LegsArmor getLegsArmor() {
        return legsArmor;
    }

    public void setLegsArmor(LegsArmor legsArmor) {
        this.legsArmor = legsArmor;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Character(String name){
        super(name,1);
        this.inventory = new Inventory();
        this.currentHealth = computeMaxHealth();
    }

    public Character() {
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

    public int upExperience(int value){
        int expForUp = experienceForUp();
        if(experience + value > expForUp){
            incrementLevel();
            experience = experience + value - expForUp;
            overExperience();
        } else {
            experience += value;
        }
        return experience;
    }

    public void overExperience(){
        int expForUp = experienceForUp();
        while (experience  > expForUp) {
            incrementLevel();
            experience = experience - expForUp;
            expForUp = experienceForUp();
        }
    }

    public int experienceForUp(){
        return (int)(this.getLevel() * 100 * Math.pow(1.10,this.getLevel()));
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
        String message = "―――――――――  Equipement : ――――――――――";
        message+="\n――――――――――――――――――――――――――――";
        if(this.weapon != null)
            message+=  "\n - " +weapon.toString();
        message+="\n――――――――――――――――――――――――――――";
        if(this.chestArmor != null)
            message+=  "\n - " + chestArmor.toString();
        message+="\n――――――――――――――――――――――――――――";
        if(this.footArmor != null)
            message+=  "\n - " + footArmor.toString();
        message+="\n――――――――――――――――――――――――――――";
        if(this.handArmor != null)
            message+=  "\n - " + handArmor.toString();
        message+="\n――――――――――――――――――――――――――――";
        if(this.headArmor != null)
            message+=  "\n - " + headArmor.toString();
        message+="\n――――――――――――――――――――――――――――";
        if(this.legsArmor != null)
            message+=  "\n - " + legsArmor.toString();
        message+="\n――――――――――――――――――――――――――――";
        return message;
    }

    public String showWeight(){
        return  "Poids : " + getInventoryWeight() + " / " + getWeight();
    }

    @Override
    public String toString() {
        String message = super.toString();
        message+="\n" + showWeight();
        message+="\n――――――――――――――――――――――――――――";
        message += "\n" + showEquipement();
        return message;
    }
}

enum StatsCharacter {
    Stamina("Endurance", 1,2), Power("Puissance",20,10),
    Health("Santé", 100,40);

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