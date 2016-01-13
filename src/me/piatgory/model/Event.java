package me.piatgory.model;

import me.piatgory.model.Entity.Entity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class Event {
    private static int maxPriority = 5;
    private Entity source;
    private Entity target;
    private String action;
    private int priority;

    public Event(Entity source, Entity target, String action, int priority) {
        this.source = source;
        this.target = target;
        this.action = action;
        this.priority = priority;
    }

    public Entity getSource() {
        return source;
    }

    public Entity getTarget() {
        return target;
    }

    public String getAction() {
        return action;
    }

    public int getPriority() {
        return priority;
    }

    public String run(){
        String message = null;
        try {
            message = (String) source.getMethodAction(action).invoke(source,target);
        } catch (Exception e){
            e.printStackTrace();
            message ="Un probléme s'est passé !!!";
        }
        return message;
    }

    public static int getMaxPriority() {
        return maxPriority;
    }
}
