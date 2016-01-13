package me.piatgory.model.Entity;

import me.piatgory.model.Event;
import me.piatgory.model.Stats;
import me.grea.antoine.utils.Dice;

import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Method;

/**
 * Created by Alexandre on 09/01/2016.
 */
@XmlRootElement
public abstract class Entity {

    private String name;
    private int level;
    protected int currentHealth;
    protected Stats stats;

    public Entity(String name,int level){
        this.level = level;
        this.name = name;
        this.buildStats();
    }

    public Entity() {
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

    public boolean isDead(){
        return currentHealth == 0;
    }

    public String attack(Entity entity){
        int damage = this.getDamage() + (int)((float)this.getDamage()*((float)Dice.roll(-20,20)/40));
        return "Frappe " + entity.getName()+ " et lui inflige " + entity.damage(damage) + " point(s) de dégats";
    }

    public Event attackEvent(Entity entity){
        return new Event(this,entity,"attack",5);
    }

    public String pass(Entity entity){
        String[] sentences = {
                "Raconte une blaque à " + entity.getName(),
                "Execute un pas de dance pour " + entity.getName(),
                "Chantonne un air pour " + entity.getName(),
                "Propose de faire la paix à "+ entity.getName(),
                "Prie dieu pour avoir de la chance",
                "Sort ses cartes pokemon et dit à "+ entity.getName() + " : Veux-tu jouer avec moi ?"
        };

        return sentences[Dice.roll(sentences.length-1)];
    }

    public Event passEvent(Entity entity){
        return new Event(this,entity,"pass",5);
    }

    public String provoke(Entity entity){
        String[] sentences = {
                "Montre ses fesses à " + entity.getName(),
                "Se cure le nez tout en ignorant " + entity.getName(),
                "Même si t'as un curseur rouge au dessus de la tête, ben avec toi, je me sens jamais en danger. T'es le moins méchant des méchants !(By sparadrap)",
                "Lance une série d'injures !",
                "Se pose dans un coin de l'aréne et pique un somme",
                "Dis à "+ entity.getName() + " : Même Sparadrap est meilleur que toi",
                "Rigole en regardant " + entity.getName(),
        };

        return sentences[Dice.roll(sentences.length-1)] ;
    }

    public Event provokeEvent(Entity entity){
        return new Event(this,entity,"provoke",0);
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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

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
        String message ="Points de vie total  : " + this.computeMaxHealth();
        message = message + "\nPoints de vie actuel : " + currentHealth;
        return message;
    }

    public String showName(){
        return  this.name + " : " + this.getClass().getSimpleName() + " ( "+ this.level+" lvl )";
    }

    @Override
    public String toString() {
        String message="\n――――――――――――――――――――――――――――";
        message += "\n――― " + showName();
        message+="\n――――――――――――――――――――――――――――";
        message = message + "\n" + showStats();
        message+="\n――――――――――――――――――――――――――――";
        message = message + "\n" + showHealth();
        message+="\n――――――――――――――――――――――――――――";
        return message;
    }



    public Event getCombatAction(int i,Entity entity){
        Event event = null;
        switch (i){
            case 0:
                event = this.attackEvent(entity);
                break;
            case 1:
                event = this.passEvent(entity);
                break;
            case 2:
                event = this.provokeEvent(entity);
                break;
        }
        return  event;
    }

    public Method getMethodAction(String action) throws Exception{
        Method method = null;
        if(action == "attack")
            method = this.getClass().getMethod("attack",Entity.class);
        else if(action == "provoke")
            method = this.getClass().getMethod("provoke",Entity.class);
        else if(action == "pass")
            method = this.getClass().getMethod("pass",Entity.class);
        else
            throw new Exception("Action not found!");
        return method;
    }

    public static String[] getCombatAction(){
        String[] action = {"Attaquer","Passer","Provoquer"};
        return  action;
    }
}
