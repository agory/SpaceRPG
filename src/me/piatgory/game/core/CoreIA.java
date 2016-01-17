package me.piatgory.game.core;

import me.piatgory.model.Entity.Entity;

/**
 * Created by Alexandre on 16/01/2016.
 */
public abstract class CoreIA {
    protected Entity entity;

    public CoreIA(Entity entity) {
        this.entity = entity;
    }

    abstract public Action getAction(Entity target);
}
