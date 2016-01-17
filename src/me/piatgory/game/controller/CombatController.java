package me.piatgory.game.controller;

import me.piatgory.game.core.CoreController;
import me.piatgory.game.ia.MonsterIA;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Event;
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

    private Event getCombatAction() {

        Event event = getCharacter().getCombatAction(
                showMenu("Veuillez choisir une action","", getCharacter().getCombatAction())
                , monster
        );
        if (event == null){
            write("Action incorrecte !!!");
            return this.getCombatAction();
        }
        return event;

    }

    public void run(){
        int i = 0;
        boolean win =false;
        combatIntroMessage();
        while (!getCharacter().isDead()&& !monster.isDead()) {
            combatBeginTurn(i);
            List<Event> events = new ArrayList<Event>();
            Event evc = getCombatAction();
            Event evm = monsterIA.getAction(getCharacter());
            write("debug : " + evc.getAction());
            write("debug : " + evm.getAction());
            events.add(evc);
            events.add(evm);
            (new TurnManagement(dataGame, monster,events)).run();
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
            write("On vient de cloner pour que tu puisse encore jouer. sa coute chère tu sais...");
            write("Par contre pleure !! tu vien de perdre un level !!! Hahaha !! ");
        }
    }




}
