package me.piatgory.game.ia;

import me.grea.antoine.utils.Dice;
import me.piatgory.game.core.CoreIA;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Event;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class MonsterIA extends CoreIA{


    public MonsterIA(Monster monster) {
        super(monster);
    }

    public Event getAction(Entity target){
        Event event = null;
        if(target.getCurrentHealth() < getMonster().getDamage()){
            if(Dice.roll(100)<25){
                event = getMonster().provokeEvent(target);
            } else if (Dice.roll(100)<10){
                event = getMonster().passEvent(target);
            } else {
                event = getMonster().attackEvent(target);
            }
        } else {
            if(Dice.roll(100)<5){
                event = getMonster().provokeEvent(target);
            } else  if (Dice.roll(100)<5){
                event = getMonster().passEvent(target);
            } else {
                event = getMonster().attackEvent(target);
            }
        }
        return event;
    }

    public Monster getMonster(){
        return (Monster) entity;
    }

}
