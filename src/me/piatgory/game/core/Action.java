package me.piatgory.game.core;

import me.piatgory.model.Entity.Entity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class Action {
    private static int maxPriority = 5;
    private Entity source;
    private Entity target;
    private String action;
    private int priority;
    private Object element;

    public Action(Entity source, Entity target, String action, int priority) {
        this(source,target,action,priority,null);
    }

    public Action(Entity source, Entity target, String action, int priority, Object element) {
        this.source = source;
        this.target = target;
        this.action = action;
        this.priority = priority;
        this.element=null;
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

            if (element != null)
                message = (String) source.getMethodAction(action).invoke(source,target,element);
            else
                message = (String) source.getMethodAction(action).invoke(source,target);
        } catch (Exception e){
            e.printStackTrace();
            message ="Un probléme est survenu !!!";
        }
        return message;
    }

    public static int getMaxPriority() {
        return maxPriority;
    }
}
