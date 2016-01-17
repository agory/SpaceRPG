package me.piatgory.game.controller.story;

import me.piatgory.game.controller.CharacterController;
import me.piatgory.game.core.CoreController;
import me.piatgory.persistance.DataGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 17/01/2016.
 */
public class SpaceShipController extends CoreController{

    public SpaceShipController(DataGame dataGame) {
        super(dataGame);
    }
    public void run(){
        introMessage();
        menuSpaceShip();
    }

    private void introMessage(){
        textSpacer();
        write("――― vaisseau spatial de " + getCharacter().getName());
        textSpacer();
    }
    private void menuSpaceShip(){
        getCharacter().heal(getCharacter().computeMaxHealth());
        this.save();
        int i = showMenu("Quel sera votre action ?","O pour quitter le jeu",itemsMenuSpaceShip());
        switch (i){
            case -1:
                break;
            case 0:
                menuDestination();
                menuSpaceShip();
                break;
            case 1:
                new CharacterController(dataGame).run();
                menuSpaceShip();
                break;
            default:
                write("Choix incorrecte !!");
                menuSpaceShip();
        }
    }

    private void menuDestination(){
        int i = showMenu("Quel sera votre prochaine destination ?","O pour quitter",itemsMenuDestination());
        switch (i){
            case -1:
                break;
            case 0:
                new PlanetArenaController(dataGame).run();
                break;
            case 1:
                new PlanetMazeController(dataGame).run();
                break;
            default:
                write("Destination incorrecte !!!");
                menuDestination();
        }
    }

    private List<String> itemsMenuDestination(){
        List<String> items = new ArrayList<String>();
        items.add(PlanetArenaController.getName());
        items.add(PlanetMazeController.getName());
        return items;
    }
    private List<String> itemsMenuSpaceShip(){
        List<String> items = new ArrayList<String>();
        items.add("Choisir une destination");
        items.add("Menu character");
        return items;
    }
}
