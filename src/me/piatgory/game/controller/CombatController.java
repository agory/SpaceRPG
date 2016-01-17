package me.piatgory.game.controller;

import me.piatgory.game.core.CoreController;
import me.piatgory.game.ia.MonsterIA;
import me.piatgory.model.Entity.Monster;
import me.piatgory.game.core.Action;
import me.piatgory.model.Item.Chest;
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

    private Action getCombatAction() {

        Action action = getCharacter().getCombatAction(
                showMenu("Veuillez choisir une action","", getCharacter().getCombatAction())
                , monster
        );
        if (action == null){
            write("Action incorrecte !!!");
            return this.getCombatAction();
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
            Action evc = getCombatAction();
            Action evm = monsterIA.getAction(getCharacter());
            write("debug : " + evc.getAction());
            write("debug : " + evm.getAction());
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
            write("Bouh tu as loose! au faite ta une tache pistache !!!!!!!!!!!!");
            getCharacter().removeLevel();
        }
    }




}
