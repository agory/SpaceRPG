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
    public void merge(Stats stats){
        for (String key : stats.getStats().keySet()) {
            if(this.stats.containsKey(key)){
                this.stats.replace(key,this.stats.get(key) + stats.getStats().get(key));
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
}
