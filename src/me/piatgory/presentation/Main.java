package me.piatgory.presentation;

import me.grea.antoine.utils.Log;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Item.*;
import me.piatgory.model.Stats;
import me.piatgory.model.StatsBuilder;

public class Main {

    public static void main(String[] args) {
	    Log.i("init project");
        Character character = new Character("Gregoire");
        character.equipChestArmor(new ChestArmor("Armure en cuir",2,StatsBuilder.make(0,3,0)));
        character.equipFootArmor(new FootArmor("Bottes en cuir",2,StatsBuilder.make(0,1,0)));
        character.equipHandArmor(new HandArmor("Gantelets en cuir",2,StatsBuilder.make(0,1,0)));
        character.equipHeadArmor(new HeadArmor("Casque en cuir",2,StatsBuilder.make(0,2,0)));
        character.equipLegsArmor(new LegsArmor("Jambiéres en cuir",2,StatsBuilder.make(0,3,0)));
        character.equipWeapon(new Weapon("Blaster",2,new Stats(),5));
        Log.i(character);
        Monster monster = new Monster("Alien", 5);
        Log.i(monster);
        combat(character,monster);
    }

    public static void combat(Character character,Monster monster){
        for (int i = 0; i < 10; i++) {
            Log.i("Attaque du personnage : " + character.attack(monster) + " domages");
            Log.i("Attaque du Monstre : " +monster.attack(character) + " domages");
            Log.i(character.showName());
            Log.i(character.showHealth());
            Log.i(monster.showName());
            Log.i(monster.showHealth());
        }
    }
}
