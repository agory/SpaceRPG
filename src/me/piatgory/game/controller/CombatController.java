package me.piatgory.game.controller;

import me.piatgory.game.Action.Usable;
import me.piatgory.game.core.CoreController;
import me.piatgory.game.ia.MonsterIA;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Entity.Monster;
import me.piatgory.game.Action.Action;
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
        combatShowEntity();
        textSpacer();
    }

    private void showBuff(){
        for(int i = 0; i < getCharacter().listBuff().size() || i < monster.listBuff().size(); i++){
            String buff1 = "";
            String buff2 = "";
            if(i < getCharacter().listBuff().size())
                buff1 = getCharacter().listBuff().get(i).toString();
            if(i < monster.listBuff().size())
                buff2 = monster.listBuff().get(i).toString();
            writeTable3(addSpaceCharacter(buff1,43)+" |","",addSpaceCharacter(buff2,35));
        }
    }

    public void combatShowEntity(){
        textSpacer();
        writeTable3(addSpaceCharacter(getCharacter().showName(),43)+" |","",addSpaceCharacter(monster.showName(),35));
        writeTable3(addSpaceCharacter(getCharacter().showHealth(),43)+" |","",addSpaceCharacter(monster.showHealth(),35));
        showBuff();
    }



    public void combatBeginTurn(int i){
        textSpacer();
        write("――― Tour n°" + i);
        textSpacer();
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
                combatShowEntity();
            } else {
                win = true;
            }
            i++;
        }

        if(win){
            write("GG vous avez gagné");
            // Logique victoire
            getCharacter().upExperience(monster.giveExperience());
            Chest chest = monster.generateChest(dataGame);
            removeDoubloonItem(chest.openChest());
            if(chest.haveItem()) {
                write(chest.showChest());
                getCharacter().makeItemsIntoInventaire(chest.openChest());
                new ItemController(dataGame).showMenuSelectItem(chest.openChest());
            }
        } else {
            write("Bouh tu as loose! au fait t'as une tache pistache !!!!!!!!!!!!");
            getCharacter().removeLevel();
        }
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

    private Action getCombatAction(int i, Entity entity){
        Action action = null;
        Usable usable = null;
        switch (i){
            case 0:
                action = getCharacter().attackAction(entity);
                break;
            case 1:
                usable = new UsableManager(dataGame).getUsableByMenuSelect(getCharacter().getCapacities());
                if(usable != null) {
                    action = getCharacter().useAction(getTargetUsable(usable),
                            usable);
                } else {
                    action = menuCombatAction();
                }
                break;
            case 2:
                usable = new UsableManager(dataGame).getUsableByMenuSelect(getCharacter().getConsumable());
                if(usable != null) {

                    action = getCharacter().useAction(getTargetUsable(usable),
                            usable);
                } else {
                    action = menuCombatAction();
                }
                break;
            case 3:
                action = getCharacter().passAction(entity);
                break;
            case 4:
                action = getCharacter().provokeAction(entity);
                break;
        }
        return action;
    }

    private Entity getTargetUsable(Usable usable){
        if(usable.isHarmful()){
            return monster;
        } else {
            return getCharacter();
        }
    }

    private static List<String> itemsCombatAction(){
        List<String> actions = new ArrayList<String>();
        actions.add("Attaquer");
        actions.add("Utiliser une capacité");
        actions.add("Utiliser un consommable");
        actions.add("Passer");
        actions.add("Provoquer");
        return  actions;
    }


}
