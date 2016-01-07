package me.piatgory.model;

import java.util.HashMap;

/**
 * Created by Alexandre Gory on 23/12/2015.
 */
public class Stats {
    HashMap<String, Integer> stats = new HashMap<>();

    public Stats() {
        for (enumStat stat : enumStat.values()) {
            stats.put(stat.getName(), stat.getValue());
        }
    }
    public void merge(){

    }


}
