package me.piatgory.game.Action;

import me.piatgory.model.Entity.Entity;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 18/01/2016.
 */
public interface Usable {

    String use(Entity target);
    String getName();
    boolean isHarmful();
    int getPriority();
    List<String> getMenuUsableAction();

}
