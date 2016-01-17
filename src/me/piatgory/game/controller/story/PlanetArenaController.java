package me.piatgory.game.controller.story;

import jdk.nashorn.internal.objects.annotations.Where;
import me.piatgory.game.controller.CharacterController;
import me.piatgory.game.controller.CombatController;
import me.piatgory.game.core.CoreController;
import me.piatgory.model.Entity.Character;
import me.piatgory.persistance.DataGame;
import me.piatgory.game.Generator.MonsterGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 13/01/2016.
 */
public class PlanetArenaController extends CoreController {
    private static String name ="Arés";

    public PlanetArenaController(DataGame dataGame) {
        super(dataGame);
    }


    @Override
    public void run() {
        introMessage();
        MonsterGenerator monsterGenerator = menuArena();
        if (monsterGenerator != null){
            startStage(monsterGenerator);
        }
        if (validation("Voulez vous continuer ?")){
            getCharacter().heal(200);// Pour être simpas
            breakMenu();
            run();
        }
    }


    private MonsterGenerator menuArena(){
        MonsterGenerator monsterGenerator = null;
        int i = showMenu("Quelle arène voulez-vous choisir ?","Entrer 0 pour quitter la planète" + name, dataGame.getStageMonster());
        if(i >= dataGame.getStageMonster().size() ) {
            write("Choix incorrecte");
            this.menuArena();
        } else if(i < 0) {
            write("Choix : quitter la planéte");
        } else {
            monsterGenerator = dataGame.getMonsterGenerators().get(i);
        }

        return monsterGenerator;
    }

    private void introMessage(){
        textSpacer();
        write("――― Bienvenue sur la planète " + name);
        textSpacer();
        write("Ici, vous pourrez affronter plein d'adversaires différents dans de multiples arènes.", 200);
        textSpacer();
    }

    private void startStage(MonsterGenerator monsterGenerator){

        int i = 0;
        List<CombatController> combatControllers = buildBattleList(monsterGenerator);
        while (startBattle(combatControllers.get(i)) && i < combatControllers.size() - 1  ){
            i++;
            getCharacter().heal(((getCharacter().computeMaxHealth()/10)*4));
            if(i == 3 || i == 5){
                write("Voici une pause durement mérité !" , 2000);
                breakMenu();
            }
        }

    }


    private boolean startBattle(CombatController combatController){
        combatController.run();
        return !getCharacter().isDead();
    }



    private List<CombatController> buildBattleList(MonsterGenerator monsterGenerator){
        List<CombatController> combatControllers = new ArrayList<CombatController>();
        combatControllers.add(new CombatController(dataGame,monsterGenerator.generateMonsterWeak(getCharacter().getLevel())));
        combatControllers.add(new CombatController(dataGame,monsterGenerator.generateMonster(getCharacter().getLevel())));
        combatControllers.add(new CombatController(dataGame,monsterGenerator.generateMonsterRobust(getCharacter().getLevel())));
        combatControllers.add(new CombatController(dataGame,monsterGenerator.generateMonsterPowerfull(getCharacter().getLevel())));
        combatControllers.add(new CombatController(dataGame,monsterGenerator.generateMonsterVicious(getCharacter().getLevel())));
        combatControllers.add(new CombatController(dataGame,monsterGenerator.generateMonsterBoss(getCharacter().getLevel())));
        return combatControllers;
    }





}
