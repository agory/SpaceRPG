package me.piatgory.game.controller.story;

import me.grea.antoine.utils.Dice;
import me.piatgory.game.core.CoreController;
import me.piatgory.persistance.DataGame;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 13/01/2016.
 */
public class PlanetMazeController extends CoreController {
    private List<String> mazes;

    public PlanetMazeController(DataGame dataGame) {
        super(dataGame);
        mazes = new ArrayList<String>();
        mazes.add("cnJsbHV1cg==");
        mazes.add("bHJ1bHJ1cg==");
        mazes.add("cnVsbHVydQ==");
        mazes.add("bHJybHVycmxy");
        mazes.add("cnJ1bGx1cmxy");
        mazes.add("bHJscmxybHJsdXV1");
        mazes.add("ZGRkbHJscnVsbGQ=");// 12 variation
    }

    @Override
    public void run() {

    }

    private void generateMaze(){
        String maze = "";
        for (int i = 0; i < mazes.size();i++){
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
    }

}
