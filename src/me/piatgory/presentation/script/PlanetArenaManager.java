package me.piatgory.presentation.script;

import me.grea.antoine.utils.Menu;
import me.piatgory.persistance.DataGame;
import me.piatgory.persistance.MonsterGenerator;

/**
 * Created by Alexandre on 13/01/2016.
 */
public class PlanetArenaManager extends ScriptManager{
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
        (new Menu("Quelle arène voulez-vous choisir ?","Entrer 0 pour quitter la planète" + name, dataGame.getStageMonster().toArray(new String[dataGame.getStageMonster().size()])) {
            @Override
            protected void on(int i) {
                if(i > items.length )
                    this.display();
                else
                    monsterGeneratorTemps = dataGame.getMonsterGenerators().get(i);
            }
        }).display();
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
