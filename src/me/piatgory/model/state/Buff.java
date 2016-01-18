package me.piatgory.model.state;

import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Stats;
import me.piatgory.model.StatsBuilder;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Buff {
    private String name;
    private Stats stats;
    private int nbTurn;

    public Buff(String name,Stats stats,int nbTurn) {
        this.name = name;
        this.stats = stats;
        this.nbTurn = nbTurn;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getNbTurn() {
        return nbTurn;
    }

    public void setNbTurn(int nbTurn) {
        this.nbTurn = nbTurn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String makeBuff(Entity target){
        Stats stats = StatsBuilder.make(this.stats,target.getLevel());
        target.applyBuff(new Buff(name,stats,nbTurn));
        return " Applique : " + name;
    }
    public int decreasingNbTurn(){
        return --nbTurn;
    }

    @Override
    public String toString() {
        return name +  " : " + nbTurn + " tour(s)" ;
    }

    public Buff() {
    }

}
