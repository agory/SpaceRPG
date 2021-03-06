package me.piatgory.game.core;

import me.grea.antoine.utils.Log;
import me.piatgory.game.controller.story.PlanetArenaController;
import me.piatgory.game.controller.story.PlanetMazeController;
import me.piatgory.game.controller.story.SpaceShipController;
import me.piatgory.model.Entity.Character;
import me.piatgory.persistance.DataGame;

import java.io.Console;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexandre on 16/01/2016.
 */
public class Game {

    protected DataGame dataGame;
    public static final Scanner scanner;

    public Game(DataGame dataGame) {
        this.dataGame = dataGame;
    }

    public void run(){
        new SpaceShipController(dataGame).run();
        dataGame.save();
    }

    public static void textSpacer(){
        System.out.println("――――――――――――――――――――――――――――");
    }

    public static void write(Object text){
        System.out.println(text);
    }

    public static void write(Object text,int tempo){
        write(text);
        try {
            Thread.sleep(tempo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeTable3(String colunm1, String colunm2, String colunm3){
        try {
            System.out.printf("%1$4s %2$10s %3$10s%n",colunm1,colunm2,colunm3);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Character getCharacter(){
        return dataGame.getCharacter();
    }

    public static final int showMenu(String title, String leave, List<String>  items) {
        write("# " + title + " #");

        int ex = 0;
        for(String item : items) {
            write(ex + 1 + ". " + item);
            ex++;
        }

        write("< " + leave);
        System.out.print("(>");

        try {
            ex = scanner.nextInt() - 1;
            scanner.nextLine();
            return ex;
        } catch (InputMismatchException var2) {
            return -1;
        }
    }

    public static final boolean validation(String question){
        List<String> items = new ArrayList<String>();
        items.add("Oui");
        items.add("Non");
        return showMenu(question, "", items) == 0;
    }

    public static final String readTexteOutput(String question){
        write("\n"+question);
        String text = "";
        try {
            text = scanner.nextLine();
            return text;
        } catch (InputMismatchException var2) {
            Log.e(var2);
            return "Erreur";
        }
    }

    public void save(){
        dataGame.save();
        write("La partie est sauvegardée.");
    }

    static {
        scanner = new Scanner(System.in);
    }
}
