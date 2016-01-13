package me.piatgory.presentation;

import me.grea.antoine.utils.Log;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Item.*;
import me.piatgory.model.StatsBuilder;
import me.piatgory.persistance.DataGame;
import me.piatgory.persistance.JAXBserializer;
import me.piatgory.presentation.manager.CharacterManager;
import me.piatgory.presentation.manager.CombatManager;

import java.util.*;

public class Main {

    public static DataGame dataGame;
    public static CharacterManager characterManager;

    public static void main(String[] args) {
	    Log.i("init project");
        dataGame = JAXBserializer.Read();
        dataGame = new DataGame("phave"); // Pour avoir un jeu toujours clean pour les tests.
        Monster monster = new Monster("Gansters",1,StatsBuilder.make(0,0,0));
        Log.i(dataGame.getCharacter().provokeEvent(monster).run());

        CombatManager combat = new CombatManager(dataGame.getCharacter(),monster);

        // To be removed later
        List<Item> itemsMonster = new ArrayList<Item>();
        String description = "Test";
        itemsMonster.add(new ChestArmor("T-Shirt usé",description,2,StatsBuilder.make(0,0,0)));
        itemsMonster.add(new FootArmor("Sandales usé",description,2,StatsBuilder.make(0,0,0)));
        itemsMonster.add(new HandArmor("Gants usé",description,2,StatsBuilder.make(0,0,0)));
        itemsMonster.add(new HeadArmor("Casquette de sacha",description + "\n Elément de collection porté par sacha lui-même !!!",2,StatsBuilder.make(0,0,0)));
        itemsMonster.add(new LegsArmor("Jean usé",description,2,StatsBuilder.make(0,0,0)));
        itemsMonster.add(new Weapon("Pistolet à bille","Arme de départ légérement pourrie.",2,StatsBuilder.make(0,0,0),2));
        monster.setMonsterItems(itemsMonster);
        ///////////////////////////////////

        combat.run();
        /*dataGame.getCharacter().makeItemIntoInventaire(dataGame.itemFind(12));
        characterManager = new CharacterManager(dataGame.getCharacter());
        characterManager.show();*/


    }







}
