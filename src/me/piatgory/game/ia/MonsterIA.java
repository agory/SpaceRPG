package me.piatgory.game.ia;

import me.grea.antoine.utils.Dice;
import me.piatgory.game.core.CoreIA;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Entity.Monster;
import me.piatgory.game.Action.Action;
import me.piatgory.model.StatsBuilder;
import me.piatgory.model.state.Buff;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern pattern = Pattern.compile("Boss");
        Matcher matcher = pattern.matcher(getMonster().getName());
        if(matcher.find() && getMonster().getCurrentHealth() < getMonster().computeMaxHealth()/2 )
            getMonster().applyBuff(new Buff("Berserker", StatsBuilder.make(0,-5,5),2));
        return action;
    }

    public Monster getMonster(){
        return (Monster) entity;
    }

}
