package me.piatgory.presentation.manager;

import me.grea.antoine.utils.Dice;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class TurnManagement extends Manager{
    public List<Event> events;
    private Monster monster;

    public TurnManagement(Character character, Monster monster, List<Event> events) {
        super(character);
        this.events = events;
        this.monster = monster;
    }

    public void run(){
        for (int i = 0; i <= Event.getMaxPriority(); i++){
            if(!character.isDead()&& !monster.isDead())
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
        while (events.size() > 0 && !character.isDead()&& !monster.isDead() ){
            int i = Dice.roll(events.size()-1);
            Event event  = events.get(i);
            write(event.getSource().getName() + " : " + event.run());
            events.remove(event);
        }
    }

}
