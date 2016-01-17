package me.piatgory.model.state;

import me.piatgory.model.Entity.Entity;

/**
 * Created by Alexandre on 17/01/2016.
 */
public class Effect {
    private Buff buff;
    private int healthGive;

    public Effect(Buff buff, int healthGive) {
        this.buff = buff;
        this.healthGive = healthGive;
    }

    public Effect(int healthGive) {
        this(null,healthGive);
    }

    public Effect(Buff buff) {
        this(buff,0);
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public int getHealthGive() {
        return healthGive;
    }

    public void setHealthGive(int healthGive) {
        this.healthGive = healthGive;
    }

    public void setOn(Entity target){
        if(healthGive > 0)
            target.heal(healthGive);
        else
            target.damage(healthGive);
        if(buff != null)
            buff.makeBuff(target);
    }
}
