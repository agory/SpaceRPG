package me.piatgory.game.controller;

import me.grea.antoine.utils.Dice;
import me.piatgory.game.Action.Action;
import me.piatgory.game.Action.ActionUsable;
import me.piatgory.game.core.CoreController;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Item.consumable.Consumable;
import me.piatgory.persistance.DataGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class TurnController extends CoreController {
    public List<Action> actions;
    private Monster monster;

    public TurnController(DataGame dataGame, Monster monster, List<Action> actions) {
        super(dataGame);
        this.actions = actions;
        this.monster = monster;
    }

    public void run(){
        for (int i = 0; i <= Action.getMaxPriority(); i++){
            if(!getCharacter().isDead()&& !monster.isDead())
                runEventRandom(this.getEventByPriority(i));
        }
        getCharacter().decreasingNbTurnBuffs();
        monster.decreasingNbTurnBuffs();
    }

    private List<Action> getEventByPriority(int i){
        List<Action> actions = new ArrayList<Action>();
        for (Action action :this.actions) {
            if(action.getPriority()==i)
                actions.add(action);
        }
        return actions;
    }

    private void runEventRandom(List<Action> actions){
        while (actions.size() > 0 && !getCharacter().isDead()&& !monster.isDead() ){
            int i = Dice.roll(actions.size()-1);
            Action action = actions.get(i);
            write(action.getSource().getName() + " : " + action.run());
            if(action instanceof ActionUsable && ((ActionUsable)action).getUsable() instanceof Consumable){
                getCharacter().removeFromInventory((Consumable)((ActionUsable)action).getUsable());
            }
            actions.remove(action);
        }
    }

}
