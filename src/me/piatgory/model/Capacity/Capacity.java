package me.piatgory.model.Capacity;

import me.piatgory.game.Action.Usable;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.state.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grégoire on 10/12/2015.
 */
public class Capacity implements Usable{
    private String name;
    private String description;
    private Effect effect;
    private int priority;
    private boolean harmful;

    public Capacity(String name, String description, Effect effect,int priority) {
        this(name,description,effect,priority, false);
    }

    public Capacity(String name, String description, Effect effect ,int priority, boolean harmful) {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.harmful = harmful;
        this.priority = priority;
    }

    public Capacity() {
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public String use(Entity target){
        return effect.use(target);
    }

    public boolean isHarmful() {
        return harmful;
    }

    public void setHarmful(boolean harmful) {
        this.harmful = harmful;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<String> getMenuUsableAction(){
        List<String> actions = new ArrayList<String>();
        actions.add("Utiliser");
        actions.add("Voir la description");
        actions.add("Jeter");
        return  actions;
    }

}
