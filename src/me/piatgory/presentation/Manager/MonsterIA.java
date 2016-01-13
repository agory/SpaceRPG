package me.piatgory.presentation.manager;

import me.grea.antoine.utils.Dice;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Event;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class MonsterIA {
    private Monster monster;

    public MonsterIA(Monster monster) {
        this.monster = monster;
    }

    public Event getAction(Character character){
        Event event = null;
        if(character.getCurrentHealth() < monster.getDamage()){
            if(Dice.roll(100)<25){
                event = monster.provokeEvent(character);
            } else if (Dice.roll(100)<10){
                event = monster.passEvent(character);
            } else {
                event = monster.attackEvent(character);
            }
        } else {
            if(Dice.roll(100)<5){
                event = monster.provokeEvent(character);
            } else  if (Dice.roll(100)<5){
                event = monster.passEvent(character);
            } else {
                event = monster.attackEvent(character);
            }
        }
        return event;
    }
}
