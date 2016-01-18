package me.piatgory.game.core;

import me.piatgory.model.Entity.Entity;

/**
 * Created by Alexandre on 12/01/2016.
 */
public abstract class CoreAction {
    protected static int maxPriority = 5;
    protected Entity source;
    protected Entity target;
    protected String action;
    protected int priority;


    public CoreAction(Entity source, Entity target, String action, int priority) {
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

    abstract public String run();

    public static int getMaxPriority() {
        return maxPriority;
    }
}
