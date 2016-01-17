package me.piatgory.model.state;

import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Stats;

/**
 * Created by Alexandre Gory on 17/12/2015.
 */
public class Buff {
    private Stats stats;
    private int nbTurn;

    public Buff(Stats stats,int nbTurn) {
        this.stats = stats;
        this.nbTurn = nbTurn;
    }

    private Buff(Buff buff){
        this(buff.getStats(), buff.getNbTurn());
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

    public void makeBuff(Entity target){
        target.applyBuff(new Buff(this));
    }
}
