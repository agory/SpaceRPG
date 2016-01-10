package me.piatgory.persistance;

import com.sun.xml.internal.txw2.annotation.XmlElement;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Item.*;
import me.piatgory.model.Stats;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
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
        Character.class,
        Entity.class,
        Monster.class,
        Equipment.class,
        ChestArmor.class,
        FootArmor.class,
        HandArmor.class,
        HeadArmor.class,
        LegsArmor.class,
        Weapon.class,
        Stats.class
        })
public class DataGame {
    private List<Item> items;
    private Character character;
    private List<Monster> monster;

    public DataGame(){
        items = new ArrayList<Item>();
        monster = new ArrayList<Monster>();
    }
    public DataGame(List<Item> items,List<Monster> monsters,Character character){
        this.items = items;
        this.character = character;
        this.monster = monsters;
    }


    @XmlElementWrapper(name = "items")
    @XmlElement
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @XmlElementWrapper(name = "Monsters")
    @XmlElement
    public List<Monster> getMonster() {
        return monster;
    }

    public void setMonster(List<Monster> monster) {
        this.monster = monster;
    }

    /*
    * Methode
    * */

    public Item itemFind(int id){
        for (Item item:this.getItems()) {
            if(item.getId() == id)
                return item;
        }
        return null;
    }

    public List<Item> itemFindByText(String text){
        List<Item> items = new ArrayList<Item>();
        Pattern pattern = Pattern.compile(text);

        for (Item item:this.getItems() ) {
            Matcher matcher = pattern.matcher(item.getDescritption());
            Matcher matcher2 = pattern.matcher(item.getName());
            if(matcher2.find()||matcher.find())
                items.add(item);
        }
        return items;
    }

    public Monster monsterFind(int id){
        for (Monster monster:this.getMonster() ) {
            if(monster.getId() == id)
                return monster;
        }
        return null;
    }

    public List<Monster> monsterFindByText(String text){
        List<Monster> monsters = new ArrayList<Monster>();
        Pattern pattern = Pattern.compile(text);

        for (Monster monster:this.getMonster()) {
            Matcher matcher = pattern.matcher(monster.getName());
            if(matcher.find())
                monsters.add(monster);
        }
        return monsters;
    }

    public List<Monster> monsterFindByTextAndLvl(String text,int lvl){
        List<Monster> monsters = new ArrayList<Monster>();
        Pattern pattern = Pattern.compile(text);

        for (Monster monster:this.getMonster()) {
            Matcher matcher = pattern.matcher(monster.getName());
            if(matcher.find() && monster.getLevel() == lvl)
                monsters.add(monster);
        }
        return monsters;
    }

    public List<Monster> monsterFindByLvl(int lvl){
        List<Monster> monsters = new ArrayList<Monster>();
        for (Monster monster:this.getMonster()) {
            if(monster.getLevel() == lvl)
                monsters.add(monster);
        }
        return monsters;
    }
}
