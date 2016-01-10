package me.piatgory.model.Entity;

import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

/**
 * Created by Alexandre on 09/01/2016.
 */
@XmlRootElement
public class Monster extends Entity {

    private static int ID=0;
    private Stats bonus;
    private int id;

    public Monster(String name,int level){
        super(name,level);
        bonus = new Stats();
        this.currentHealth = computeMaxHealth();
        this.id= ID++;
    }

    public Monster(String name,int level, Stats bonus){
        super(name,level);
        this.bonus = bonus;
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
        // Update Level monter
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

    public int giveExperience(){
        return getLevel()*50;
    }

    public static void resetID(){
        ID=0;
    }
}

enum StatsMonster {
    Stamina("Endurance", 0,1), Power("Puissance", 5,5),
    Health("Santé", 40,15);

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