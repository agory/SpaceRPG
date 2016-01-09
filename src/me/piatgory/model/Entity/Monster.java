package me.piatgory.model.Entity;

import me.piatgory.model.Stats;

import java.util.HashMap;

/**
 * Created by Alexandre on 09/01/2016.
 */
public class Monster extends Entity {

    private Stats bonus;

    public Monster(String name,int level){
        super(name,level);
        bonus = new Stats();
        this.currentHealth = computeMaxHealth();
    }

    public Monster(String name,int level, Stats bonus){
        super(name,level);
        this.bonus = bonus;
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
}

enum StatsMonster {
    Stamina("Endurance", 0,1), Power("Puissance", 5,5),
    Health("Santé", 40,5);

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