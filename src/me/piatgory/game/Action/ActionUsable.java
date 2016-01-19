package me.piatgory.game.Action;

import me.piatgory.game.core.CoreAction;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Item.consumable.Consumable;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class ActionUsable extends Action{

    protected Usable usable;

    public ActionUsable(Entity source, Entity target, String action, int priority, Usable object) {
        super(source,target,action,priority);
        this.usable = object;
    }

    public Usable getUsable() {
        return usable;
    }

    public void setUsable(Usable usable) {
        this.usable = usable;
    }

    public String run(){
        String message = null;
        try {
            message = (String) source.getMethodAction(action).invoke(source,target,usable);
        } catch (Exception e){
            e.printStackTrace();
            message ="Un probléme est survenu !!!";
        }
        return message;
    }


}
