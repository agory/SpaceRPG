package me.piatgory.presentation.manager;

import me.grea.antoine.utils.Menu;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Event;
import me.piatgory.model.Item.Chest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 12/01/2016.
 */
public class CombatManager extends Manager{
    private Monster monster;
    private MonsterIA monsterIA;
    private static Event eventTemp;

    public CombatManager(Character character, Monster monster) {
        super(character);
        this.monster = monster;
        this.monsterIA = new MonsterIA(monster);
    }


    private void combatIntroMessage(){
        textSpacer();
        write("――― Combat");
        textSpacer();
        write(character.showName());
        write(character.showHealth());
        textSpacer();
        write("――― Contre");
        textSpacer();
        write(monster.showName());
        write(monster.showHealth());
    }

    public void combatEndTurn(){

        textSpacer();
        write(character.showName());
        write(character.showHealth());
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
        (new Menu("Veuillez choisir une action","", Character.getCombatAction()) {
            @Override
            protected void on(int i) {
                eventTemp = character.getCombatAction(i, monster);
                if (eventTemp == null){
                    write("Action incorrecte !!!");
                    this.display();
                }
            }
        }).display();
        return eventTemp;
    }

    public void run(){
        int i = 0;
        boolean win =false;
        combatIntroMessage();
        while (!character.isDead()&& !monster.isDead()) {
            combatBeginTurn(i);
            List<Event> events = new ArrayList<Event>();
            Event evc = getCombatAction();
            Event evm = monsterIA.getAction(character);
            write("debug : " + evc.getAction());
            write("debug : " + evm.getAction());
            events.add(evc);
            events.add(evm);
            (new TurnManagement(character, monster,events)).run();
            if(!monster.isDead()){
                combatEndTurn();
            } else {
                win = true;

            }
            ++i;
        }

        if(win){
            write("GG vous avez gagné");
            // Logique victoire
            character.upExperience(monster.giveExperience());
            // Fonctionnera une fois la génération des objets du monstre faite
            Chest chest = monster.generateChest();
            if(chest.getChestWeight()!=0){
                write("Ouverture du coffre");
                write(chest.openChest());
            }

        } else {
            // logique defaite
        }
    }


}
