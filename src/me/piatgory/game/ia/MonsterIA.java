package me.piatgory.game.ia;

import me.grea.antoine.utils.Dice;
import me.piatgory.game.core.CoreIA;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Entity.Monster;
import me.piatgory.game.Action.Action;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class MonsterIA extends CoreIA{


    public MonsterIA(Monster monster) {
        super(monster);
    }

    public Action getAction(Entity target){
        Action action = null;
        if(target.getCurrentHealth() < getMonster().getDamage()){
            if(Dice.roll(100)<25){
                action = getMonster().provokeAction(target);
            } else if (Dice.roll(100)<10){
                action = getMonster().passAction(target);
            } else {
                action = getMonster().attackAction(target);
            }
        } else {
            if(Dice.roll(100)<5){
                action = getMonster().provokeAction(target);
            } else  if (Dice.roll(100)<5){
                action = getMonster().passAction(target);
            } else {
                action = getMonster().attackAction(target);
            }
        }
        return action;
    }

    public Monster getMonster(){
        return (Monster) entity;
    }

}
