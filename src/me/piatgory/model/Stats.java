package me.piatgory.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alexandre Gory on 23/12/2015.
 */
public class Stats {
    HashMap<String, Integer> stats = new HashMap<String, Integer>();

    public Stats(HashMap<String, Integer> stats) {
        this.stats =stats;
    }

    public Stats() {
        this.buildStats();
    }

    public void merge(Stats stats){
        for (String key : stats.getStats().keySet()) {
            if(this.stats.containsKey(key)){
                this.stats.put(key,this.stats.get(key) + stats.getStats().get(key));
            } else {
                this.stats.put(key,stats.getStats().get(key));
            }
        }
    }

    public HashMap<String, Integer> getStats() {
        return stats;
    }

    @Override
    public String toString() {
        String message = "Stats :";
        for (String key : stats.keySet()) {
            message += " - " + key + " : " + stats.get(key);
        }
        return message;
    }

    public void buildStats(){
        this.stats = new HashMap<String, Integer>();
        for (StatsDefault stat:StatsDefault.values()) {
            this.stats.put(stat.getName(),stat.getValue());
        }
    }

    public void setStats(HashMap<String, Integer> stats) {
        this.stats = stats;
    }
}

enum StatsDefault {
    Stamina("Endurance", 0,0), Power("Puissance",0,0),
    Health("Santé", 0,0);

    private String name;
    private int value;
    private int valuePerLevel;

    StatsDefault(String name, int value,int valuePerLevel) {
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