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

    public String use(Entity target){
        String message = "";

        if(healthGive > 0) {
            message+= " Soin : " + target.heal(100 + healthGive + target.getLevel());
        } else {
            message+= " Degat : " + target.damage(10 + healthGive * target.getLevel() * (-1));
        }

        if(buff != null) {
            if(buff.getStats() != null)
                target.heal(buff.getStats().getStats().get("Santé"));
            message += buff.makeBuff(target);
        }
        return message;
    }

    public Effect() {
    }

}
