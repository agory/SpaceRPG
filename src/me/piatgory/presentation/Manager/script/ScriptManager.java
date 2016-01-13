package me.piatgory.presentation.manager.script;

import me.piatgory.model.Entity.Character;
import me.piatgory.persistance.DataGame;
import me.piatgory.presentation.manager.Manager;

/**
 * Created by Alexandre on 13/01/2016.
 */
abstract class ScriptManager {
    protected final DataGame dataGame;

    public ScriptManager(DataGame dataGame) {
        this.dataGame = dataGame;
    }

    //abstract void loadConf();
    abstract void run();

    public static void textSpacer(){
        System.out.println("――――――――――――――――――――――――――――");
    }

    public static void write(Object text){
        System.out.println(text);
    }
}
