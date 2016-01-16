package me.piatgory.game.controller;

import me.grea.antoine.utils.Menu;
import me.piatgory.game.core.CoreController;
import me.piatgory.game.ia.MonsterIA;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Event;
import me.piatgory.model.Item.Chest;
import me.piatgory.model.Item.Item;
import me.piatgory.persistance.DataGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class CombatController extends CoreController {
    private Monster monster;
    private MonsterIA monsterIA;
    private static Event eventTemp;

    public CombatController(DataGame dataGame, Monster monster) {
        super(dataGame);
        this.monster = monster;
        this.monsterIA = new MonsterIA(monster);
    }


    private void combatIntroMessage(){
        textSpacer();
        write("――― Combat");
        textSpacer();
        write(getCharacter().showName());
        write(getCharacter().showHealth());
        textSpacer();
        write("――― Contre");
        textSpacer();
        write(monster.showName());
        write(monster.showHealth());
    }

    public void combatEndTurn(){

        textSpacer();
        write(getCharacter().showName());
        write(getCharacter().showHealth());
        textSpacer();
        write(monster.showName());
        write(monster.showHealth());
    }



    public void combatBeginTurn(int i){
        textSpacer();
        write("――― Tour n°" + i);
        textSpacer();
    }

    private Event getCombatAction() {

        eventTemp = getCharacter().getCombatAction(
                showMenu("Veuillez choisir une action","", getCharacter().getCombatAction())
                , monster
        );
        if (eventTemp == null){
            write("Action incorrecte !!!");
            return this.getCombatAction();
        }else {
            return eventTemp;
        }
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
            write("Bouh vous avez loose! au faite ta une tache pistache !!!!!!!!!!!!");
        }
    }




}
