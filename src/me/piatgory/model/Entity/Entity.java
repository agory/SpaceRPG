package me.piatgory.model.Entity;

import me.piatgory.game.core.Action;
import me.piatgory.model.Item.consumable.Consumable;
import me.piatgory.model.Stats;
import me.grea.antoine.utils.Dice;
import me.piatgory.model.state.Buff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 09/01/2016.
 */
@XmlRootElement
public abstract class Entity {

    private String name;
    @XmlElement
    private Integer level;
    @XmlElement
    protected Integer currentHealth;
    protected Stats stats;
    protected List<Buff> buffs;

    public Entity(String name,int level){
        this();
        this.level = level;
        this.name = name;
        this.buildStats();
    }

    public Entity() {
        buffs = new ArrayList<Buff>();
    }


    public int computeMaxHealth(){
        return this.computeAllStats().getStats().get("Santé");
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        int maxHealth = computeMaxHealth();
        if(currentHealth > maxHealth) {
            this.currentHealth = maxHealth;
        } else if(currentHealth < 0){
            this.currentHealth = 0;
        } else {
            this.currentHealth = currentHealth;
        }
    }

    public void setLevel(int level){
        this.level = level;
    }

    protected void balanceCurrentHealth(Stats statsRemove, Stats statsAdd){
        if(statsRemove != null)
            this.damageBalance(statsRemove.getStats().get("Santé"));
        if(statsAdd != null)
            this.heal(statsAdd.getStats().get("Santé"));
    }

    public boolean isDead(){
        return currentHealth == 0;
    }

    public String attack(Entity entity){
        int damage = this.getDamage() + (int)((float)this.getDamage()*((float)Dice.roll(-20,20)/40));
        return "Frappe " + entity.getName()+ " et lui inflige " + entity.damage(damage) + " point(s) de dégats";
    }

    public Action attackAction(Entity entity){
        return new Action(this,entity,"attack",3);
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

    public Action passAction(Entity entity){
        return new Action(this,entity,"pass",0);
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

    public Action provokeAction(Entity entity){
        return new Action(this,entity,"provoke",0);
    }

    public String consume(Entity entity, Consumable consumable){
        consumable.setOn(entity);
        return "Utilise : " + consumable.getName();
    }

    public Action consumeAction(Entity entity,Consumable consumable){
        return new Action(this,entity,"consume",1,consumable);
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
        return "Points de vie : " + currentHealth + "/" + this.computeMaxHealth();
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

    public void applyBuff(Buff buff){
        buffs.add(buff);
    }

    public Method getMethodAction(String action) throws Exception{
        Method method = null;
        if(action == "attack")
            method = this.getClass().getMethod("attack",Entity.class);
        else if(action == "provoke")
            method = this.getClass().getMethod("provoke",Entity.class);
        else if(action == "pass")
            method = this.getClass().getMethod("pass",Entity.class);
        else if(action == "consume")
            method = this.getClass().getMethod("pass",Entity.class,Consumable.class);
        else
            throw new Exception("Action not found!");
        return method;
    }


}
