package me.piatgory.game.controller;

import me.piatgory.game.core.CoreController;
import me.piatgory.game.ia.MonsterIA;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Entity.Monster;
import me.piatgory.game.core.Action;
import me.piatgory.model.Item.Chest;
import me.piatgory.model.Item.Item;
import me.piatgory.model.Item.consumable.Consumable;
import me.piatgory.persistance.DataGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class CombatController extends CoreController {
    private Monster monster;
    private MonsterIA monsterIA;

    public CombatController(DataGame dataGame, Monster monster) {
        super(dataGame);
        this.monster = monster;
        this.monsterIA = new MonsterIA(monster);
    }


    private void combatIntroMessage(){
        textSpacer();
        write("――― Combat");
        textSpacer();
        writeTable3(addSpaceCharacter(getCharacter().showName(),43)+" |",addSpaceCharacter("",6),"| " + addSpaceCharacter(monster.showName(),35));
        writeTable3(addSpaceCharacter(getCharacter().showHealth(),43)+" |","Contre  ","| " + addSpaceCharacter(monster.showHealth(),35));
        textSpacer();
    }

    public void combatEndTurn(){
        textSpacer();
        writeTable3(addSpaceCharacter(getCharacter().showName(),35)+" |","",addSpaceCharacter(monster.showName(),35));
        writeTable3(addSpaceCharacter(getCharacter().showHealth(),35)+" |","",addSpaceCharacter(monster.showHealth(),35));
    }



    public void combatBeginTurn(int i){
        textSpacer();
        write("――― Tour n°" + i);
        textSpacer();
    }

    private Action menuCombatAction() {

        Action action = getCombatAction(
                showMenu("Veuillez choisir une action","", itemsCombatAction()),
                monster);
        if (action == null){
            write("Action incorrecte !!!");
            return this.menuCombatAction();
        }
        return action;

    }

    public void run(){
        int i = 0;
        boolean win =false;
        combatIntroMessage();
        while (!getCharacter().isDead()&& !monster.isDead()) {
            combatBeginTurn(i);
            List<Action> actions = new ArrayList<Action>();
            Action evc = menuCombatAction();
            Action evm = monsterIA.getAction(getCharacter());
            actions.add(evc);
            actions.add(evm);
            (new TurnController(dataGame, monster, actions)).run();
            if(!monster.isDead()){
                combatEndTurn();
            } else {
                win = true;

            }
            i++;
        }

        if(win){
            write("GG vous avez gagné");
            // Logique victoire
            getCharacter().upExperience(monster.giveExperience());
            Chest chest = monster.generateChest();
            removeDoubloonItem(chest.openChest());
            if(chest.haveItem()) {
                write(chest.showChest());
                new ItemController(dataGame).showMenuSelectItem(chest.openChest());
            }
        } else {
            write("Bouh tu as loose! au fait t'as une tache pistache !!!!!!!!!!!!");
            getCharacter().removeLevel();
        }
    }


    private Action getCombatAction(int i, Entity entity){
        Action action = null;
        switch (i){
            case 0:
                action = getCharacter().attackAction(entity);
                break;
            case 1:
                Item item = new ItemController(dataGame).getItemByMenuSelectItem(getCharacter().getConsumable());
                if(item != null) {
                    action = getCharacter().consumeAction(getCharacter(),
                            (Consumable)item);
                } else {
                    action = menuCombatAction();
                }
                break;
            case 2:
                action = getCharacter().passAction(entity);
                break;
            case 3:
                action = getCharacter().provokeAction(entity);
                break;
        }
        return action;
    }

    private static List<String> itemsCombatAction(){
        List<String> actions = new ArrayList<String>();
        actions.add("Attaquer");
        actions.add("Utiliser un consommable");
        actions.add("Passer");
        actions.add("Provoquer");
        return  actions;
    }


}
