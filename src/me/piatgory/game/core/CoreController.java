package me.piatgory.game.core;

import me.grea.antoine.utils.Dice;
import me.grea.antoine.utils.Log;
import me.piatgory.game.Action.Usable;
import me.piatgory.game.Generator.MonsterGenerator;
import me.piatgory.game.controller.CharacterController;
import me.piatgory.game.controller.CombatController;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Item.Item;
import me.piatgory.persistance.DataGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 10/01/2016.
 */
public abstract class CoreController extends Game{

    public CoreController(DataGame dataGame) {
        super(dataGame);
    }

    public static List<String> convertListItemToStringTab(List<Item> items){
        List<String> itemsMenu = new ArrayList<String>();
        for (Item item:items) {
            itemsMenu.add(item.getName());
        }
        return itemsMenu;
    }

    public static List<String> convertListUsableToStringTab(List<? extends Usable> usables){
        List<String> itemsMenu = new ArrayList<String>();
        for (Usable usable:usables) {
            itemsMenu.add(usable.getName());
        }
        return itemsMenu;
    }

    public boolean isInInventory(Item item){
        return getCharacter().getInventory().getMyItems().contains(item);
    }

    public List<Item> removeDoubloonItem(List<Item> items){
        for(int i =0; i<items.size();i++){
            if (isInInventory(items.get(i)))
                items.remove(items.get(i));
        }
        return items;
    }

    public void breakMenu(){
        save();

        switch (showMenu("Menu ", "tapez 0 pour quitter.",itemBreakMenu())){
            case 0:
                getCharacter().heal(getCharacter().computeMaxHealth());
                breakMenu();
                break;
            case 1:
                new CharacterController(dataGame).run();
                breakMenu();
                break;
            case 2:
                save();
                System.exit(0);
                break;
        }
    }

    public List<String> itemBreakMenu(){
        List<String> items = new ArrayList<String>();

        items.add("Se reposer");
        items.add("Menu Charater");
        items.add("Arrêter le jeu");
        return items;
    }

    public String addSpaceCharacter(String text, int size){
        while(text.length() < size){
            text+="\u0020";
        }
        return text;
    }

    protected boolean fightRandom(int level){
        MonsterGenerator monsterGenerator = dataGame.getMonsterGenerators().get(Dice.roll(dataGame.getMonsterGenerators().size()-1));
        Monster monster = monsterGenerator.generateRandom(level);
        new CombatController(dataGame,monster).run();
        if(getCharacter().isDead())
            return false;
        return true;
    }

    public abstract void run();

}
