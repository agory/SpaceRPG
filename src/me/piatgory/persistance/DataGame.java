package me.piatgory.persistance;

import me.grea.antoine.utils.Dice;
import me.piatgory.game.Generator.MonsterGenerator;
import me.piatgory.model.Capacity.Capacity;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Item.*;
import me.piatgory.model.Item.Equipment.*;
import me.piatgory.model.Item.consumable.Consumable;
import me.piatgory.model.Stats;
import me.piatgory.model.StatsBuilder;
import me.piatgory.model.state.Buff;
import me.piatgory.model.state.Effect;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexandre on 09/01/2016.
 */
@XmlRootElement
@XmlSeeAlso({
        me.piatgory.model.Entity.Character.class,
        me.piatgory.model.Entity.Entity.class,
        me.piatgory.model.Entity.Monster.class,
        Consumable.class,
        Capacity.class,
        Equipment.class,
        ChestArmor.class,
        FootArmor.class,
        HandArmor.class,
        HeadArmor.class,
        LegsArmor.class,
        Weapon.class,
        me.piatgory.model.Stats.class
        })
public class DataGame {
    private Entity character;
    private List<Item> items;
    private List<MonsterGenerator> monsterGenerators;
    private List<Buff> buffs;
    private List<Consumable> consumables;


    public DataGame(){
        initDataGame();
    }
    public DataGame(String name){
        this();
        buildCharacter(name);
        JAXBserializer.Save(this);
    }

    public void save(){
        JAXBserializer.Save(this);
    }

    public Character getCharacter() {
        return (Character) character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Item itemFind(int id){
        for (Item item : this.items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    public List<Item> itemAll(){
        return items;
    }

    public List<Item> itemFindByText(String text){
        List<Item> items = new ArrayList<Item>();
        Pattern pattern = Pattern.compile(text);
        for (Item item : this.items) {
            Matcher matcher = pattern.matcher(item.getDescritption());
            Matcher matcher2 = pattern.matcher(item.getName());
            if (matcher2.find() || matcher.find())
                items.add(item);
        }

        return items;
    }

    public Buff findBuff(String name){
        Pattern pattern = Pattern.compile(name);
        for (Buff buff : this.buffs) {
            Matcher matcher = pattern.matcher(buff.getName());
            if (matcher.find() )
                return buff;
        }
        return null;
    }

    public MonsterGenerator monsterFindByText(String text){
        MonsterGenerator monsterGenerator = null;
        Pattern pattern = Pattern.compile(text);
        for (MonsterGenerator m:this.monsterGenerators) {
            Matcher matcher = pattern.matcher(m.getName());
            if(matcher.find())
                monsterGenerator = m;
        }

        return monsterGenerator;
    }

    public List<String> getStageMonster(){
        List<String> stages = new ArrayList<String>();
        for (MonsterGenerator monsterGenerator : this.monsterGenerators ) {
            if(!(monsterGenerator.getName() == "Licorne" && this.character.getLevel() < 50)) {
                stages.add(monsterGenerator.getName());
            }
        }
        return  stages;
    }

    public Consumable getRandomConsumable(){
        return consumables.get(Dice.roll(consumables.size()-1)).getNewInstance();
    }

    public List<MonsterGenerator> getMonsterGenerators() {
        return monsterGenerators;
    }

    public void initDataGame(){
        Item.resetID();
        addBuff();
        addItems();
        addConsumable();
        addMonsterGenerator();

    }

    public void buildCharacter(String name){
        this.character = new Character(name);
        getCharacter().equipChestArmor((ChestArmor)this.itemFind(0));
        getCharacter().equipFootArmor((FootArmor)this.itemFind(1));
        getCharacter().equipHandArmor((HandArmor)this.itemFind(2));
        getCharacter().equipHeadArmor((HeadArmor)this.itemFind(3));
        getCharacter().equipLegsArmor((LegsArmor)this.itemFind(4));
        getCharacter().equipWeapon((Weapon)this.itemFind(5));
    }

    public void addMonsterGenerator(){
        List<MonsterGenerator> MonsterGenerator =new ArrayList<MonsterGenerator>();
        MonsterGenerator.add(new MonsterGenerator("Gansters",StatsBuilder.make(5,2,2),this.itemFindByText("gangster")));
        MonsterGenerator.add(new MonsterGenerator("Policier",StatsBuilder.make(10,3,3),this.itemFindByText("forces de l'ordre")));
        MonsterGenerator.add(new MonsterGenerator("Mercenaire",StatsBuilder.make(15,4,4),this.itemFindByText("mercenaire")));
        MonsterGenerator.add(new MonsterGenerator("Inquisiteur Sith",StatsBuilder.make(10,5,7),this.itemFindByText("assassin")));
        MonsterGenerator.add(new MonsterGenerator("Assemblage de Replicateur ",StatsBuilder.make(30,10,10),this.itemFindByText("cybernétique")));
        MonsterGenerator.add(new MonsterGenerator("Licorne",StatsBuilder.make(40,10,10),this.itemFindByText("licorne")));

        this.monsterGenerators = MonsterGenerator;
    }

    public void addItems(){
        List<Item> items = new ArrayList<Item>();

        String description = "Vétement de départ légérement pourrie.";
        items.add(new ChestArmor("T-Shirt usé",description,2, StatsBuilder.make(0,0,0)));
        items.add(new FootArmor("Sandales usées",description,2,StatsBuilder.make(0,0,0)));
        items.add(new HandArmor("Gants usés",description,2,StatsBuilder.make(0,0,0)));
        items.add(new HeadArmor("Casquette de sacha",description + "\n Elément de collection porté par sacha lui-même !!!",2,StatsBuilder.make(0,0,0)));
        items.add(new LegsArmor("Jean usé",description,2,StatsBuilder.make(0,0,0)));
        items.add(new Weapon("Pistolet à bille","Arme de départ légérement pourrie.",2,StatsBuilder.make(0,0,0),2));

        description = "Vétement de cuire moulant permettant de ressembler à un sadomasochiste(gangster).";
        items.add(new ChestArmor("Veste de gangster",description,2,StatsBuilder.make(2,4,1)));
        items.add(new FootArmor("Bottes de gangster",description,2,StatsBuilder.make(1,2,0)));
        items.add(new HandArmor("Gants de gangster",description,2,StatsBuilder.make(1,2,1)));
        items.add(new HeadArmor("Corne de gangster",description,2,StatsBuilder.make(1,2,0)));
        items.add(new LegsArmor("Jean de gangster",description,2,StatsBuilder.make(0,4,1)));

        items.add(new Weapon("Fouet laser de gangster","L'arme idéale du gangster sadique",2,StatsBuilder.make(10,0,5),5));
        items.add(new Weapon("Bouclier  anti-emeute volé","Bouclier anti-emeute volé par un gangster plutot bourrin. Arme idéal pour ceux qui aime prendre des coups.\n ",2,StatsBuilder.make(30,5,0),2));
        items.add(new Weapon("Blaster laser","Utiliser par les tapettes qui tapent à distant. Utiliser par les gangster qui n'assume par leur coté masochiste.",2,StatsBuilder.make(0,0,10),5));

        description = "Vétement utilisé en général par les forces de l'ordre.";
        items.add(new ChestArmor("Veste de policier",description,2,StatsBuilder.make(10,4,1)));
        items.add(new FootArmor("Bottes de policier",description,2,StatsBuilder.make(5,2,0)));
        items.add(new HandArmor("Gants de policier",description,2,StatsBuilder.make(5,2,1)));
        items.add(new HeadArmor("Casque de policier",description,2,StatsBuilder.make(5,2,0)));
        items.add(new LegsArmor("Jambières de policier",description,2,StatsBuilder.make(10,4,1)));

        description = "Utilisé en général par les forces de l'ordre";
        items.add(new Weapon("Bouclier anti-emeute ",description +"\nArme idéal pour ceux qui aime prendre des coups.",2,StatsBuilder.make(60,10,0),5));
        items.add(new Weapon("Matraque telescopique",description +"\nPermet de se défouler pendant les manifestations civile.",2,StatsBuilder.make(20,0,10),10));
        items.add(new Weapon("Sniper de caitlyn  ",description +"\nPermet de creuser le corp des gens.",2,StatsBuilder.make(0,0,15),15));

        description = "Vétement utilisé par les mercenaires.";
        items.add(new ChestArmor("Manteau de mercenaire",description,2,StatsBuilder.make(20,8,5)));
        items.add(new FootArmor("Bottes de mercenaire",description,2,StatsBuilder.make(10,4,2)));
        items.add(new HandArmor("Gants de mercenaire",description,2,StatsBuilder.make(10,4,2)));
        items.add(new HeadArmor("Casque de mercenaire",description,2,StatsBuilder.make(10,4,2)));
        items.add(new LegsArmor("Jean de mercenaire",description,2,StatsBuilder.make(20,8,5)));

        description = "Arme utilisée par les mercenaires.";
        items.add(new Weapon("Fusil laser",description +"\nPermet de tirer en rafales(malheureusement sa ne sert rien dans ce jeu :-) ).",2,StatsBuilder.make(10,5,15),15));
        items.add(new Weapon("Blaster laser",description +"\nPermet de ressembler à cowboy de l'espace!!",2,StatsBuilder.make(10,2,15),15));


        description = "Vétement utilisé par les assassins.";
        items.add(new ChestArmor("Manteau d'assasin",description,2,StatsBuilder.make(0,4,10)));
        items.add(new FootArmor("Bottes d'assasin",description,2,StatsBuilder.make(0,2,5)));
        items.add(new HandArmor("Gants d'assasin",description,2,StatsBuilder.make(0,2,5)));
        items.add(new HeadArmor("Masque d'assasin",description,2,StatsBuilder.make(0,2,5)));
        items.add(new LegsArmor("Jean d'assasin",description,2,StatsBuilder.make(0,4,10)));

        description = "Arme utilisée par les assassins.";
        items.add(new Weapon("Lame caché",description +"\nFacile de se couper les doigts avec !!",2,StatsBuilder.make(20,5,10),15));
        items.add(new Weapon("Lame caché",description +"\n A était utilisé par les inquisiteur sith contre les jedi !!",2,StatsBuilder.make(20,10,15),20));
        items.add(new Weapon("Sniper silencieux",description +"\nArme extrémement létal!!",2,StatsBuilder.make(0,0,30),30));



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




        this.items = items;
    }

    private void addBuff(){
        List<Buff> buffs = new ArrayList<Buff>();

        buffs.add(new Buff("Berserker", StatsBuilder.make(0,-5,5),4));
        buffs.add(new Buff("Shield", StatsBuilder.make(0,2,0),5));
        buffs.add(new Buff("Transcendance", StatsBuilder.make(10,2,2),4));
        buffs.add(new Buff("Steroid", StatsBuilder.make(0,2,2),5));
        buffs.add(new Buff("AttaquePlus", StatsBuilder.make(0,0,2),10));
        buffs.add(new Buff("DefensePlus", StatsBuilder.make(0,2,0),10));
        buffs.add(new Buff("SantéPlus", StatsBuilder.make(10,0,0),10));

        buffs.add(new Buff("Cécité", StatsBuilder.make(0,0,-999),2));
        buffs.add(new Buff("Brise Armure", StatsBuilder.make(0,-2,0),5));
        buffs.add(new Buff("Affaibli", StatsBuilder.make(0,0,-2),5));
        buffs.add(new Buff("Endormi", StatsBuilder.make(0,999,-999),4));


        this.buffs = buffs;
    }

    private void addConsumable(){
        List<Consumable> consumables = new ArrayList<Consumable>();
        consumables.add(new Consumable("Potion du Berserker","Permet de rentrer dans une puissante rage.",new Effect(findBuff("Berserker"))));
        consumables.add(new Consumable("Potion sac à pv","Augmente la vie de l'utilisateur.",new Effect(findBuff("SantéPlus"))));
        consumables.add(new Consumable("Potion de defense","Augmente l'endurance.",new Effect(findBuff("DefensePlus"))));
        consumables.add(new Consumable("Steroid","Permet de taper plus fort et d'encaisser plus fort.",new Effect(findBuff("Steroid"))));
        consumables.add(new Consumable("Potion d'attaque","Augmente la puissance.",new Effect(findBuff("AttaquePlus"))));
        consumables.add(new Consumable("Potion de Transcendance","Augmente énormément les stats.",new Effect(findBuff("AttaquePlus"))));
        consumables.add(new Consumable("Générateur de bouclier portable","Fait apparaître un bouclier autour de vous.",new Effect(findBuff("Shield"))));
        consumables.add(new Consumable("Potion de soin","Rend des points de vie",new Effect(20)));
        consumables.add(new Consumable("Potion de soin supérieur","Rend beaucoups de points de vie",new Effect(30)));
        consumables.add(new Consumable("Potion de soin ultime","Rend tout les points de vie",new Effect(999)));

        consumables.add(new Consumable("Grenade","Inflige des dégats",new Effect(-5),true));
        consumables.add(new Consumable("Bombe saint","Inflige beaucoups de dégats",new Effect(-15),true));
        consumables.add(new Consumable("Grenade flash","Rend la cible aveugle un cour instant",new Effect(findBuff("Cécité")),true));
        consumables.add(new Consumable("Gaz soporifique","Rend la cible la cible endormie",new Effect(findBuff("Endormi")),true));
        consumables.add(new Consumable("Gaz affaiblissant","Rend la cible la cible Affaiblie",new Effect(findBuff("Affaibli")),true));
        consumables.add(new Consumable("Belier","Reduit l'armure de la cible",new Effect(findBuff("Brise Armure")),true));

        this.consumables = consumables;

    }
}
