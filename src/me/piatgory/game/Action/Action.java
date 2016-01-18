package me.piatgory.game.Action;

import me.piatgory.game.core.CoreAction;
import me.piatgory.model.Entity.Entity;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class Action extends CoreAction{

    public Action(Entity source, Entity target, String action, int priority) {
        super(source, target, action, priority);
    }

    public String run(){
        String message = null;
        try {
            message = (String) source.getMethodAction(action).invoke(source,target);
        } catch (Exception e){
            e.printStackTrace();
            message ="Un probléme est survenu !!!";
        }
        return message;
    }


}
