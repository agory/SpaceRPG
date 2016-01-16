package me.piatgory.game.controller;

import me.grea.antoine.utils.Dice;
import me.piatgory.game.core.CoreController;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Event;
import me.piatgory.persistance.DataGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class TurnManagement extends CoreController {
    public List<Event> events;
    private Monster monster;

    public TurnManagement(DataGame dataGame, Monster monster, List<Event> events) {
        super(dataGame);
        this.events = events;
        this.monster = monster;
    }

    public void run(){
        for (int i = 0; i <= Event.getMaxPriority(); i++){
            if(!getCharacter().isDead()&& !monster.isDead())
                runEventRandom(this.getEventByPriority(i));
        }
    }

    private List<Event> getEventByPriority(int i){
        List<Event> events = new ArrayList<Event>();
        for (Event event:this.events) {
            if(event.getPriority()==i)
                events.add(event);
        }
        return events;
    }

    private void runEventRandom(List<Event> events){
        while (events.size() > 0 && !getCharacter().isDead()&& !monster.isDead() ){
            int i = Dice.roll(events.size()-1);
            Event event  = events.get(i);
            write(event.getSource().getName() + " : " + event.run());
            events.remove(event);
        }
    }

}
