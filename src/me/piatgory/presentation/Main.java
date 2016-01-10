package me.piatgory.presentation;

import me.grea.antoine.utils.Log;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Item.*;
import me.piatgory.model.Stats;
import me.piatgory.model.StatsBuilder;
import me.piatgory.persistance.DataGame;
import me.piatgory.persistance.JAXBserializer;
import me.piatgory.presentation.Manager.CharacterManager;

import java.util.*;

public class Main {

    public static DataGame dataGame;
    public static CharacterManager characterManager;

    public static void main(String[] args) {
	    Log.i("init project");
        dataGame = JAXBserializer.Read();
        characterManager = new CharacterManager(dataGame.getCharacter());
        characterManager.show();


    }

    public static void combat(Character character,Monster monster){
        for (int i = 0; i < 4; i++) {
            Log.i("Attaque du personnage : " + character.attack(monster) + " domages");
            Log.i("Attaque du Monstre : " +monster.attack(character) + " domages");
            Log.i(character.showName());
            Log.i(character.showHealth());
            Log.i(monster.showName());
            Log.i(monster.showHealth());
        }
    }

    public static void resetDataGame(String name){
        dataGame = new DataGame();
        Item.resetID();
        Monster.resetID();
        addItems();
        buildCharacter(name);
        addMonster();

        JAXBserializer.Save(dataGame);
    }

    public static void buildCharacter(String name){
        Character character = new Character(name);
        character.equipChestArmor((ChestArmor)dataGame.itemFind(0));
        character.equipFootArmor((FootArmor)dataGame.itemFind(1));
        character.equipHandArmor((HandArmor)dataGame.itemFind(2));
        character.equipHeadArmor((HeadArmor)dataGame.itemFind(3));
        character.equipLegsArmor((LegsArmor)dataGame.itemFind(4));
        character.equipWeapon((Weapon)dataGame.itemFind(5));
        dataGame.setCharacter(character);
    }
    public static void addMonster(){
        List<Monster> monsters =new ArrayList<Monster>();
        for (int i=1;i < 100;i++){
            monsters.add(new Monster("Gansters",i,StatsBuilder.make(0,0,0)));
            monsters.add(new Monster("Gansters blindé",i,StatsBuilder.make(3*i,3*i,0*i)));
            monsters.add(new Monster("Gansters puissant",i,StatsBuilder.make(1*i,1*i,3*i)));
            monsters.add(new Monster("Homme bêtes",i,StatsBuilder.make(3*i,3*i,3*i)));
            monsters.add(new Monster("Alien intelligent",i,StatsBuilder.make(-1*i,-1*i,5*i)));
            monsters.add(new Monster("Alien vorace",i,StatsBuilder.make(1*i,0*i,2*i)));
            monsters.add(new Monster("Alien blindé",i,StatsBuilder.make(2*i,2*i,0*i)));
            monsters.add(new Monster("Licorne",i,StatsBuilder.make(15*i,15*i,15*i)));
            monsters.add(new Monster("Vehicule : Tank",i,StatsBuilder.make(10*i,10*i,0*i)));
            monsters.add(new Monster("Vehicule : Chasseur de la mort",i,StatsBuilder.make(0*i,0*i,10*i)));
            monsters.add(new Monster("Vehicule : drone",i,StatsBuilder.make(5*i,10*i,5*i)));
            monsters.add(new Monster("Policier deserteur",i,StatsBuilder.make(1*i,1*i,1*i)));
            monsters.add(new Monster("Policier deserteur blinder",i,StatsBuilder.make(3*i,3*i,1*i)));
        }
        dataGame.setMonster(monsters);
    }

    public static void addItems(){
        List<Item> items = new ArrayList<Item>();

        String description = "Vétement de départ légérement pourrie.";
        items.add(new ChestArmor("T-Shirt usé",description,2,StatsBuilder.make(0,0,0)));
        items.add(new FootArmor("Sandales usé",description,2,StatsBuilder.make(0,0,0)));
        items.add(new HandArmor("Gants usé",description,2,StatsBuilder.make(0,0,0)));
        items.add(new HeadArmor("Casquette de sacha",description + "\n Elément de collection porté par sacha lui-même !!!",2,StatsBuilder.make(0,0,0)));
        items.add(new LegsArmor("Jean usé",description,2,StatsBuilder.make(0,0,0)));
        items.add(new Weapon("Pistolet à bille","Arme de départ légérement pourrie.",2,StatsBuilder.make(0,0,0),2));

        description = "Vétement de cuire moulant permettant de ressembler à un sadomasochiste(gangster).";
        items.add(new ChestArmor("Veste de gangster",description,2,StatsBuilder.make(0,2,1)));
        items.add(new FootArmor("Bottes de gangster",description,2,StatsBuilder.make(0,1,0)));
        items.add(new HandArmor("Gants de gangster",description,2,StatsBuilder.make(0,1,1)));
        items.add(new HeadArmor("Corne de gangster",description,2,StatsBuilder.make(0,1,0)));
        items.add(new LegsArmor("Jean de gangster",description,2,StatsBuilder.make(0,2,1)));

        items.add(new Weapon("Fouet laser de gangster","L'arme idéale du gangster sadique",2,StatsBuilder.make(10,0,5),5));
        items.add(new Weapon("Bouclier  anti-emeute volé","Bouclier volé anti-emeute par un gangster plutot bourrin. Arme idéal pour ceux qui aime prendre des coups.\n ",2,StatsBuilder.make(30,5,0),2));
        items.add(new Weapon("Blaster laser","Utiliser par les tapettes qui tapent à distant. Utiliser par les gangster qui n'assume par leur coté masochiste.",2,StatsBuilder.make(0,0,10),5));

        description = "Vétement utilisé en général par les forces de l'ordre.";
        items.add(new ChestArmor("Veste de policier",description,2,StatsBuilder.make(10,4,1)));
        items.add(new FootArmor("Bottes de policier",description,2,StatsBuilder.make(5,2,0)));
        items.add(new HandArmor("Gants de policier",description,2,StatsBuilder.make(5,2,1)));
        items.add(new HeadArmor("Casque de policier",description,2,StatsBuilder.make(5,2,0)));
        items.add(new LegsArmor("Jambières de policier",description,2,StatsBuilder.make(10,4,1)));

        items.add(new Weapon("Bouclier anti-emeute ","Utilisé en général par les forces de l'ordre.\nArme idéal pour ceux qui aime prendre des coups.",2,StatsBuilder.make(60,10,0),5));
        items.add(new Weapon("Matraque telescopique","Utilisé en général par les forces de l'ordre.\nPermet de se défouler pendant les manifestations civile.",2,StatsBuilder.make(20,0,10),10));
        items.add(new Weapon("Sniper","Utilisé en général par les forces de l'ordre.\nPermet de creuser le corp des gens.",2,StatsBuilder.make(0,0,10),10));

        description = "Vétement utilisé par les assassins.";
        items.add(new ChestArmor("Manteau d'assasin",description,2,StatsBuilder.make(0,4,10)));
        items.add(new FootArmor("Bottes d'assasin",description,2,StatsBuilder.make(0,2,5)));
        items.add(new HandArmor("Gants d'assasin",description,2,StatsBuilder.make(0,2,5)));
        items.add(new HeadArmor("Masque d'assasin",description,2,StatsBuilder.make(0,2,5)));
        items.add(new LegsArmor("Jean d'assasin",description,2,StatsBuilder.make(0,4,10)));

        description = "Arme utilisée par les assassins.";
        items.add(new Weapon("Lame caché",description +"\nFacile de se couper les doigts avec !!",2,StatsBuilder.make(20,5,10),15));
        items.add(new Weapon("Sniper silencieux",description +"\nArme extrémement létal!!",2,StatsBuilder.make(0,0,20),20));


        description = "Vétement utilisé par les mercenaires.";
        items.add(new ChestArmor("Manteau de mercenaire",description,2,StatsBuilder.make(20,8,5)));
        items.add(new FootArmor("Bottes de mercenaire",description,2,StatsBuilder.make(10,4,2)));
        items.add(new HandArmor("Gants de mercenaire",description,2,StatsBuilder.make(10,4,2)));
        items.add(new HeadArmor("Casque de mercenaire",description,2,StatsBuilder.make(10,4,2)));
        items.add(new LegsArmor("Jean de mercenaire",description,2,StatsBuilder.make(20,8,5)));

        description = "Arme utilisée par les mercenaires.";
        items.add(new Weapon("Fusil laser",description +"\nPermet de tirer en rafales(malheureusement sa ne sert rien dans ce jeu :-) ).",2,StatsBuilder.make(10,5,15),15));
        items.add(new Weapon("Blaster laser",description +"\nPermet de ressembler à cowboy de l'espace!!",2,StatsBuilder.make(10,2,15),15));

        description = "Vétement utilisé par les licornes de l'espace.\n Apparament utilisable sur les humanoïdes, on ne sait comment mais ça marche.";
        items.add(new ChestArmor("Manteau de licornes",description,2,StatsBuilder.make(40,15,10)));
        items.add(new FootArmor("fers arriéres de licornes",description,2,StatsBuilder.make(20,10,5)));
        items.add(new HandArmor("fers avants de licornes",description,2,StatsBuilder.make(20,10,5)));
        items.add(new HeadArmor("Extension de corne de licornes",description,2,StatsBuilder.make(20,10,5)));
        items.add(new LegsArmor("Jean à 4 pattes de licornes",description,2,StatsBuilder.make(40,15,10)));

        description = "Arme utilisée par les licornes. \nApparament utilisable sur les humanoïdes, on ne sait comment mais ça marche.";
        items.add(new Weapon("Support dorsale à licorne équipé de canon",description,2,StatsBuilder.make(20,5,40),40));
        items.add(new Weapon("Accélérateur de charge de licorne",description,2,StatsBuilder.make(40,10,25),25));

        description = "Armure cybernétique ultime.\nAurait été utlisé par nono le petit robot.";
        items.add(new ChestArmor("Torse cybernétique ultime",description,2,StatsBuilder.make(100,30,30)));
        items.add(new FootArmor("Bottes cybernétique ultime",description,2,StatsBuilder.make(50,20,15)));
        items.add(new HandArmor("Gants cybernétique ultime",description,2,StatsBuilder.make(50,20,15)));
        items.add(new HeadArmor("Casque cybernétique ultime",description,2,StatsBuilder.make(50,20,15)));
        items.add(new LegsArmor("Jambières cybernétique ultime",description,2,StatsBuilder.make(100,30,30)));

        description = "Arme ultime de l'armure cybernétique ultime. \n Ça envoie vraiment du paté !!";
        items.add(new Weapon("Canon plasma",description,2,StatsBuilder.make(40,10,80),80));
        items.add(new Weapon("Duo Sabre laser/Bouclier à champs de force",description,2,StatsBuilder.make(80,20,50),50));

        dataGame.setItems(items);
    }


}
