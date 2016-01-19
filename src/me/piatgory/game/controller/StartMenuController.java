package me.piatgory.game.controller;

import me.piatgory.game.core.CoreController;
import me.piatgory.game.core.Game;
import me.piatgory.model.Entity.ClassChar.*;
import me.piatgory.persistance.DataGame;
import me.piatgory.persistance.JAXBserializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 19/01/2016.
 */
public class StartMenuController extends CoreController{

    public StartMenuController(DataGame dataGame) {
        super(dataGame);
    }

    @Override
    public void run() {
        write("Do nothing");
    }

    public static void startMenu(){
        List<String> items = new ArrayList<String>();
        items.add("Reprendre la précédente partie.");
        items.add("Recommencer une nouvelle partie.");
        int i = Game.showMenu("Menu jeu : " , "Appuyer sur 0 pour quitter le jeu",items);
        switch (i){
            case 0 :
                DataGame dataGame = JAXBserializer.Read();
                new Game(dataGame).run();
                break;
            case 1:
                newGame();
                break;
        }
    }

    private static Class classMenu(){
        List<String> items = new ArrayList<String>();
        items.add("Berserker.");
        items.add("Alchimiste.");
        items.add("Mécanicien.");
        items.add("Pretre.");
        items.add("Chasseur de licorne.");
        int i = Game.showMenu("Choix de classe : " , "",items);
        Class classChar = null;
        switch (i){
            case 0 :
                classChar = Berserker.class;
                break;
            case 1:
                classChar = Alchimiste.class;
                break;
            case 2:
                classChar = Mecanicien.class;
                break;
            case 3:
                classChar = Priest.class;
                break;
            case 4:
                classChar = ChasseurLicorne.class;
                break;
            default:
                classMenu();
                break;
        }
        return classChar;
    }

    public static void newGame(){
        String question = "Nom de votre personnage : ";
        String name = Game.readTexteOutput(question);
        Class classChar = classMenu();
        new Game(new DataGame(name,classChar)).run();
    }
}
