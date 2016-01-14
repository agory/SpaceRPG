package me.piatgory.presentation.script;

import me.piatgory.persistance.DataGame;

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
