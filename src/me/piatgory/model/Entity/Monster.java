package me.piatgory.model.Entity;

import me.grea.antoine.utils.Dice;
import me.piatgory.model.Item.Chest;
import me.piatgory.model.Item.Equipment;
import me.piatgory.model.Item.Item;
import me.piatgory.model.Item.Weapon;
import me.piatgory.model.Stats;
import me.piatgory.model.StatsBuilder;
import me.piatgory.persistance.DataGame;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alexandre on 09/01/2016.
 */
@XmlRootElement
public class Monster extends Entity {

    private static int ID=0;
    private Stats bonus;
    private int id;

    private List<Item> monsterItems = new ArrayList<Item>();
    private List<Item> chestItems = new ArrayList<Item>();

    public Monster(String name,int level){
        this(name,level,new Stats());
    }

    public Monster(String name,int level, Stats bonus){
        super(name,level);
        this.bonus = bonus;
        this.currentHealth = computeMaxHealth();
        this.id= ID++;
    }

    public Monster(String name,int level, Stats bonus,List<Item> monsterItems){
        super(name,level);
        this.bonus = bonus;
        this.currentHealth = computeMaxHealth();
        this.monsterItems = monsterItems;
        this.id= ID++;
    }

    public Monster() {
        ID++;
    }

    protected Stats computeAllStats(){
        Stats stats = new Stats();
        stats.merge(this.stats);
        stats.merge(this.bonus);
        return stats;
    }

    public int getDamage() {
        return this.computeAllStats().getStats().get("Puissance");
    }

    public void buildStats(){
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        for (StatsMonster stat:StatsMonster.values()) {
            stats.put(stat.getName(),stat.getValue());
        }
        this.stats = new Stats(stats);
        // Update Level monster
        for (StatsMonster stat: StatsMonster.values()) {
            this.stats.getStats().put(
                    stat.getName(),
                    this.stats.getStats().get(stat.getName()) + stat.getValuePerLevel()*getLevel()
            );
        }
    }

    public Stats getBonus() {
        return bonus;
    }

    public void setBonus(Stats bonus) {
        this.bonus = bonus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getMonsterItems() {
        return monsterItems;
    }

    public void setMonsterItems(List<Item> monsterItems) {
        this.monsterItems = monsterItems;
    }

    public int giveExperience(){
        return getLevel()*50;
    }

    public static void resetID(){
        ID=0;
    }

    public Chest generateChest(){

        if (Dice.roll(100) < 10) {
            chestItems.add(monsterItems.get(Dice.roll(monsterItems.size()-1)));
        }

        //while(chestItems.size()<nbItems){
            //Ajouter consommable
        //}
        return new Chest(chestItems);
    }
}

enum StatsMonster {
    Stamina("Endurance", 0,2), Power("Puissance", 5,6),
    Health("Santé", 40,20);

    private String name;//defaultvalue
    private int value;//defaultvalue
    private int valuePerLevel;

    StatsMonster(String name, int value,int valuePerLevel) {
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