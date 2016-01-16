package me.piatgory.game.controller.story;

import me.grea.antoine.utils.Menu;
import me.piatgory.game.core.CoreController;
import me.piatgory.persistance.DataGame;
import me.piatgory.persistance.MonsterGenerator;

/**
 * Created by Alexandre on 13/01/2016.
 */
public class PlanetArenaManager extends CoreController {
    private static MonsterGenerator monsterGeneratorTemps;
    private static String name ="Arés";

    public PlanetArenaManager(DataGame dataGame) {
        super(dataGame);
    }


    @Override
    public void run() {
        introMessage();
        menuArena();

    }


    public MonsterGenerator menuArena(){
        monsterGeneratorTemps = null;
        int i = showMenu("Quelle arène voulez-vous choisir ?","Entrer 0 pour quitter la planète" + name, dataGame.getStageMonster());
        if(i >= dataGame.getStageMonster().size() ) {
            write("Choix incorrecte");
            this.menuArena();
        } else if(i < 0) {
            write("Choix : quitter la planéte");
        } else {
            monsterGeneratorTemps = dataGame.getMonsterGenerators().get(i);
        }

        return monsterGeneratorTemps;
    }

    public void introMessage(){
        textSpacer();
        write("――― Bienvenue sur la planète " + name);
        textSpacer();
        write("Ici, vous pourrez affronter plein d'adversaires différents dans de multiples arènes.");
        textSpacer();
    }






}
