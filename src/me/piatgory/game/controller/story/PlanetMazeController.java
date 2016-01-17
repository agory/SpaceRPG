package me.piatgory.game.controller.story;

import me.grea.antoine.utils.Dice;
import me.piatgory.game.core.CoreController;
import me.piatgory.persistance.DataGame;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by Alexandre on 13/01/2016.
 */
public class PlanetMazeController extends CoreController {
    private static List lost;
    private static String name= "Dédale";
    private List<String> mazes;
    private String currentMaze;
    private Integer position;
    private int level;

    public static String getName() {
        return name;
    }

    public PlanetMazeController(DataGame dataGame) {
        super(dataGame);
        mazes = new ArrayList<String>();

    }

    @Override
    public void run() {
        level =0;
        introMessage();
        boolean leave = true;
        while (leave){
            messageStart();
            if(mazes.size()-3 < level)
                generateMaze();
            leave = startMaze(level);
            level++;
        }

    }


    public void introMessage(){
        textSpacer();
        write("――― Bienvenue sur la planète " + name);
        textSpacer();
        write("L'endroit est joli et on peut facilement trouver de la companie.");
        textSpacer();
    }

    public void messageStart(){
        textSpacer();
        write("――― Labyrinthe level : " + level);
        textSpacer();
    }
    private void generateMaze(){
        String maze = "";
        for (int i = 0; i < mazes.size()+3;i++){
            switch (Dice.roll(3)){
                case 0:
                    maze+="r";
                    break;
                case 1:
                    maze+="l";
                    break;
                case 2:
                    maze+="u";
                    break;
                case 3:
                    maze+="d";
                    break;

            }
        }
        mazes.add(maze);
    }

    private boolean startMaze(int level){
        currentMaze = mazes.get(level).toString();

        position = 0;
        boolean leave = true;
        while(position < currentMaze.length()&& leave){

            char direction = directionMenu();
            if(direction == 's') {
                leave = false;
            } else if(currentMaze.charAt(position) == direction){
                position++;
                battleWay();
            } else {
                position = 0;
                write(lost.get(Dice.roll(lost.size()-1)));
            }
        }
        return leave;
    }

    private char directionMenu(){
        char direction = 's';
        int i = showMenu("Veuillez choisir une direction","Impossible de partir vous ne connaisser pas la sortie.", itemDirectionMenu());
        write("% "+ i +" %");
        switch (i){
            case 0:
                direction = 'r';
                break;
            case 1:
                direction = 'l';
                break;
            case 2:
                direction = 'u';
                break;
            case 3:
                direction = 'd';
                break;
            case 4:
                if(position==0){
                    direction = 's';
                    break;
                }
                if(position % 4 == 3){
                    breakMenu();
                    break;
                }
            default:
                write("Non non non! La direction n'est pas correcte.");
                directionMenu();
        }
        return direction;
    }

    private List<String> itemDirectionMenu(){
        List<String> items = new ArrayList<String>();
        items.add("Aller à droite");
        items.add("Aller à gauche");
        items.add("Aller tout droit");
        items.add("Revenir en arrière");
        if(position % 4 == 3) {
            items.add("Faire une pause.");
        }
        if (position == 0){
            items.add("Sortir du labyrinthe.");
        }
        return items;
    }

    public void battleWay(){
        if(Dice.roll(100) < 25){
            if(!this.fightRandom()){
                getCharacter().heal(((getCharacter().computeMaxHealth()/10)*1));
            } else {
                position = 0;
                level=0;
                currentMaze=mazes.get(level);
                getCharacter().heal(((getCharacter().computeMaxHealth()/10)*5));
                write("La main de dieu vous à extrait du combat juste avant que vous mourrez. Vous avez perdu une part de votre ame ( -1 lvl).");
                write("Vos blessures fatales ont été gueriees(pas toutes ^^) cependant vous êtes de retour à la case départ");
            }
        }
    }

    static {
        lost = new ArrayList();
        lost.add("- Vous entendez un léger courant d'air montrant que vous vous approchez de l'entrée. -");
        lost.add("- De la lumière enfin!! Non !!!!!!!!! C'est l'entrée! -");
        lost.add("- Vous vous trouvez devant une magnifique porte de pierre qui ressemble à celle de l'entrée. -");
    }


}
