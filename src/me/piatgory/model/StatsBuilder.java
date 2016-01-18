package me.piatgory.model;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alexandre on 09/01/2016.
 */
public class StatsBuilder {
    public static Stats make(int health, int stamina ,int power){
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        stats.put("Puissance",power);
        stats.put("Endurance",stamina);
        stats.put("Santé",health);
        return new Stats(stats);
    }

    public static Stats make(int health, int Stamina ,int power,int level){
        return make(health*level,Stamina*level,power*level);
    }

    public static Stats make(Stats stats, int level){
        HashMap<String,Integer> dStats = stats.getStats();
        return make(dStats.get("Santé")*level,dStats.get("Endurance")*level,dStats.get("Puissance")*level);
    }
}
