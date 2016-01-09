package me.piatgory.model;

import java.util.HashMap;

/**
 * Created by Alexandre on 09/01/2016.
 */
public class StatsBuilder {
    public static Stats make(int health, int Stamina ,int power){
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        stats.put("Puissance",power);
        stats.put("Endurance",Stamina);
        stats.put("Santé",health);
        return new Stats(stats);
    }
}
