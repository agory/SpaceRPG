package me.piatgory.presentation;

import me.grea.antoine.utils.Log;
import me.piatgory.game.controller.CombatController;
import me.piatgory.game.core.Game;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.StatsBuilder;
import me.piatgory.persistance.DataGame;
import me.piatgory.persistance.JAXBserializer;
import me.piatgory.game.controller.CharacterController;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataGame dataGame;
        List<String> items = new ArrayList<String>();
        items.add("Reprendre la précédente partie.");
        items.add("Recommencer une nouvelle partie.");
        int i = Game.showMenu("Menu jeu : " , "Appuyer sur 0 pour quitter le jeu",items);
        switch (i){
            case 0 :
                dataGame = JAXBserializer.Read();
                new Game(dataGame).run();
                break;
            case 1:
                String question = "Nom de votre personnage : ";
                new Game(new DataGame(Game.readTexteOutput(question))).run();
        }


    }
}
