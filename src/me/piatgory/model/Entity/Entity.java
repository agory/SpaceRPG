package me.piatgory.model.Entity;

import me.piatgory.model.Stats;
import me.grea.antoine.utils.Dice;

/**
 * Created by Alexandre on 09/01/2016.
 */
abstract class Entity {
    private String name;
    private int level;
    protected int currentHealth;
    protected Stats stats;


    public Entity(String name,int level){
        this.level = level;
        this.name = name;
        this.buildStats();
    }



    public int computeMaxHealth(){
        return this.computeAllStats().getStats().get("Santé");
    }

    public int getLevel() {
        return level;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    protected void balanceCurrentHealth(Stats statsRemove, Stats statsAdd){
        if(statsRemove != null)
            this.damageBalance(statsRemove.getStats().get("Santé"));
        if(statsAdd != null)
            this.heal(statsAdd.getStats().get("Santé"));
    }

    protected void setCurrentHealth(int currentHealth) {
        int maxHealth = computeMaxHealth();
        if(currentHealth > maxHealth) {
            this.currentHealth = maxHealth;
        } else if(currentHealth < 0){
            this.currentHealth = 0;
        } else {
            this.currentHealth = currentHealth;
        }
    }

    public int attack(Entity entity){
        int damage = this.getDamage()/**(Dice.roll(0,100)/100)*/;
        return entity.damage(damage);
    }

    public void heal(int value){
        this.setCurrentHealth(this.currentHealth + value);
    }

    public int damage(int value){
        value = value - this.computeAllStats().getStats().get("Endurance");
        if(value <= 0)
            value = 1;
        this.setCurrentHealth(this.currentHealth - value);
        return value;
    }
    public void damageBalance(int value){
        this.setCurrentHealth(this.currentHealth - value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void setLevel(int level){
        this.level = level;
    }
    protected abstract Stats computeAllStats();
    public abstract void buildStats();
    public abstract int getDamage();

    public String showStats() {
        String message =  "Stats : ";
        Stats allStats = computeAllStats();
        for (String stats: this.stats.getStats().keySet()) {
            int value = this.stats.getStats().get(stats);
            int bonus = allStats.getStats().get(stats) - this.stats.getStats().get(stats);
            message = message + "\n - " + stats +" : "+ value + " + bonus " + bonus;
        }
        return message;
    }
    public String showHealth(){
        String message ="Point de vie total  : " + this.computeMaxHealth();
        message = message + "\nPoint de vie actuel : " + currentHealth;
        return message;
    }

    public String showName(){
        return  this.name + " : " + this.getClass().getSimpleName() + " ( "+ this.level+" lvl )";
    }
    @Override
    public String toString() {
        String message = showName();
        message = message + "\n" + showStats();
        message = message + "\n" + showHealth();
        return message;
    }
}
