package me.piatgory.model.Entity;

import me.piatgory.game.Action.Usable;
import me.piatgory.model.*;
import me.piatgory.model.Item.*;
import me.piatgory.model.Item.Equipment.*;
import me.piatgory.model.Item.consumable.Consumable;
import me.piatgory.model.state.Buff;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

    public Integer getLevel() {
        return super.getLevel();
    }

    public Integer getCurrentHealth() {
        return super.getCurrentHealth();
    }

    public void setCurrentHealth(int currentHealth) {
        super.setCurrentHealth(currentHealth);
    }

    public void setLevel(int level){
        super.setLevel(level);
    }

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

        stats.merge(computeBuffStats());
        return stats;
    }

    public void applyBuff(Buff buff){
        buffs.add(buff);
    }

    /*
    * Le poids maximun portable augment en fonction de la puissance du personnage
    * */
    public int getWeight(){
        return this.computeAllStats().getStats().get("Puissance")*20;
    }

    public int getDamage(){
        if(this.weapon != null)
            return this.weapon.getDamage(this.stats);
        else
            return this.computeAllStats().getStats().get("Puissance");
    }

    public void makeItemIntoInventaire(Item item){
        this.getInventory().addItem(item);
    }

    public void makeItemsIntoInventaire(List<Item> items){
        this.getInventory().addItems(items);
    }

    public Equipment takePlaceOf(Equipment equipment){
        Equipment currentEquipment = null;
        if(equipment.getClass() == Weapon.class){
            currentEquipment = getWeapon();
        }
        if(equipment.getClass() == ChestArmor.class){
            currentEquipment = getChestArmor();
        }
        if(equipment.getClass() == FootArmor.class){
            currentEquipment = getFootArmor();
        }
        if(equipment.getClass() == HeadArmor.class){
            currentEquipment = getHeadArmor();
        }
        if(equipment.getClass() == HandArmor.class){
            currentEquipment = getHandArmor();
        }
        if(equipment.getClass() == LegsArmor.class){
            currentEquipment = getLegsArmor();
        }
        return currentEquipment;
    }

    public Equipment equip(Equipment equipment){
        Equipment lastEquipment = null;
        if(equipment.getClass() == Weapon.class){
            lastEquipment = equipWeapon((Weapon) equipment);
        }
        if(equipment.getClass() == ChestArmor.class){
            lastEquipment = equipChestArmor((ChestArmor) equipment);
        }
        if(equipment.getClass() == FootArmor.class){
            lastEquipment = equipFootArmor((FootArmor) equipment);
        }
        if(equipment.getClass() == HeadArmor.class){
            lastEquipment = equipHeadArmor((HeadArmor) equipment);
        }
        if(equipment.getClass() == HandArmor.class){
            lastEquipment = equipHandArmor((HandArmor) equipment);
        }
        if(equipment.getClass() == LegsArmor.class){
            lastEquipment = equipLegsArmor((LegsArmor) equipment);
        }
        return lastEquipment;
    }

    public Equipment equipWeapon(Weapon weapon){
        Equipment lastEquipment = this.weapon;
        this.balanceCurrentHealth((this.weapon != null) ? this.weapon.getStats() : null, (weapon != null) ? weapon.getStats() : null);
        this.weapon = weapon;
        return lastEquipment;
    }

    public Equipment equipChestArmor(ChestArmor chestArmor){
        Equipment lastEquipment = this.chestArmor;
        this.balanceCurrentHealth((this.chestArmor != null) ? this.chestArmor.getStats() : null,(chestArmor != null) ? chestArmor.getStats() : null);
        this.chestArmor=chestArmor;
        return lastEquipment;
    }

    public Equipment equipFootArmor(FootArmor footArmor){
        Equipment lastEquipment = this.footArmor;
        this.balanceCurrentHealth((this.footArmor != null) ? this.footArmor.getStats() : null,(footArmor != null) ? footArmor.getStats() : null);
        this.footArmor=footArmor;
        return lastEquipment;
    }

    public Equipment equipHeadArmor(HeadArmor headArmor){
        Equipment lastEquipment = this.headArmor;
        this.balanceCurrentHealth((this.headArmor != null) ? this.headArmor.getStats() : null,(headArmor != null) ? headArmor.getStats() : null);
        this.headArmor=headArmor;
        return lastEquipment;
    }

    public Equipment equipHandArmor(HandArmor handArmor){
        Equipment lastEquipment = this.handArmor;
        this.balanceCurrentHealth((this.handArmor != null) ? this.handArmor.getStats() : null,(handArmor != null) ? handArmor.getStats() : null);
        this.handArmor=handArmor;
        return lastEquipment;
    }

    public Equipment equipLegsArmor(LegsArmor legsArmor){
        Equipment lastEquipment = this.legsArmor;
        this.balanceCurrentHealth((this.legsArmor != null) ? this.legsArmor.getStats() : null,(legsArmor != null) ? legsArmor.getStats() : null);
        this.legsArmor=legsArmor;
        return lastEquipment;
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

    private void overExperience(){
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
        this.heal(computeMaxHealth());
    }

    public void removeLevel(){
        this.setLevel(this.getLevel()-1);
        for (StatsCharacter stat:StatsCharacter.values()) {
            this.stats.getStats().put(
                    stat.getName(),
                    this.stats.getStats().get(stat.getName()) - stat.getValuePerLevel()
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
        else
            message+= "\n Pas d'arme equipée.";
        message+="\n――――――――――――――――――――――――――――";
        if(this.chestArmor != null)
            message+=  "\n - " + chestArmor.toString();
        else
            message+= "\n Pas d'armure de torse equipée.";
        message+="\n――――――――――――――――――――――――――――";
        if(this.footArmor != null)
            message+=  "\n - " + footArmor.toString();
        else
            message+= "\n Pas de bottes equipée.";
        message+="\n――――――――――――――――――――――――――――";
        if(this.handArmor != null)
            message+=  "\n - " + handArmor.toString();
        else
            message+= "\n Pas de gants equipé";
        message+="\n――――――――――――――――――――――――――――";
        if(this.headArmor != null)
            message+=  "\n - " + headArmor.toString();
        else
            message+= "\n Pas de casque equipé.";
        message+="\n――――――――――――――――――――――――――――";
        if(this.legsArmor != null)
            message+=  "\n - " + legsArmor.toString();
        else
            message+= "\n Pas de jambières equipée.";
        message+="\n――――――――――――――――――――――――――――";
        return message;
    }

    public List<Consumable> getConsumable(){
        return inventory.getConsumable();
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

    public List<String> getItemsMenuCharacter(){
        List<String> items = new ArrayList<String>();
        items.add("Menu l'inventaire");
        items.add("Voir Equipement");
        items.add("Voir les stats");
        items.add("voir Character all description");
        return items;
    }
}

enum StatsCharacter {
    Stamina("Endurance", 4,2), Power("Puissance",20,10),
    Health("Santé", 100,50);

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