package me.piatgory.model;

import java.util.HashMap;

/**
 * Created by Alexandre Gory on 23/12/2015.
 */
public class Stats {
    HashMap<String, Integer> stats = new HashMap<>();

    public Stats() {
        for (Stat stat : Stat.values()) {
            stats.put(stat.getName(), stat.getValue());
        }
    }


}
