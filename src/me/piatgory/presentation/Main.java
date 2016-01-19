package me.piatgory.presentation;

import me.grea.antoine.utils.Log;
import me.piatgory.game.controller.CombatController;
import me.piatgory.game.controller.StartMenuController;
import me.piatgory.game.core.Game;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.StatsBuilder;
import me.piatgory.persistance.DataGame;
import me.piatgory.persistance.JAXBserializer;
import me.piatgory.game.controller.CharacterController;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StartMenuController.startMenu();
    }
}
