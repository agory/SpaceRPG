package me.piatgory.model.state;

import me.grea.antoine.utils.Dice;
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
        return use(target,0);
    }

    public String use(Entity target,int power){
        String message = "";

        if(healthGive > 0) {
            message+= " Soin : " + target.heal(100 + power + healthGive + target.getLevel());
        }
        if(healthGive < 0)
        {
            int damage = (10 + ((power==0)? ((int)(power * 1.4) + healthGive * target.getLevel()) : (healthGive * target.getLevel()) )* (-1));
            message+= " Degat : " + target.damage(damage + (int)((float)damage*((float) Dice.roll(-20,20)/40)));
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
