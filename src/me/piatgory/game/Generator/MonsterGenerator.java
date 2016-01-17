package me.piatgory.game.Generator;

import me.grea.antoine.utils.Dice;
import me.grea.antoine.utils.Log;
import me.piatgory.model.Entity.Monster;
import me.piatgory.model.Item.Item;
import me.piatgory.model.Stats;
import me.piatgory.model.StatsBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 13/01/2016.
 */
public class MonsterGenerator {
    private String name;
    private Stats bonus;
    private List<Item> loot;

    public MonsterGenerator(String name, Stats bonus, List<Item> loot) {
        this.name = name;
        this.bonus = bonus;
        this.loot = loot;
    }

    public Monster generateMonsterWeak(int level){
        List<Item> items;
        Stats stats = StatsBuilder.make(
                bonus.getStats().get("Santé")* (level-2),
                bonus.getStats().get("Endurance")* (level-2),
                bonus.getStats().get("Puissance")* (level-2));
        return new Monster(name + " affaiblie",level, stats,loot);
    }

    public Monster generateMonster(int level){
        List<Item> items;
        Stats stats = StatsBuilder.make(
                bonus.getStats().get("Santé")* (level),
                bonus.getStats().get("Endurance")* (level),
                bonus.getStats().get("Puissance")* (level));
        return new Monster(name ,level, stats,loot);
    }

    public Monster generateMonsterPowerfull(int level){
        List<Item> items;
        Stats stats = StatsBuilder.make(
                bonus.getStats().get("Santé")* (level+2),
                bonus.getStats().get("Endurance")* (level+2),
                bonus.getStats().get("Puissance")* (level+2));
        return new Monster(name + " puissant",level, stats,loot);
    }

    public Monster generateMonsterRobust(int level){
        List<Item> items;
        Stats stats = StatsBuilder.make(
                bonus.getStats().get("Santé")* (level+6),
                bonus.getStats().get("Endurance")* (level+6),
                bonus.getStats().get("Puissance")* (level-2));
        return new Monster(name + " robuste",level, stats,loot);
    }

    public Monster generateMonsterVicious(int level){
        List<Item> items;
        Stats stats = StatsBuilder.make(
                bonus.getStats().get("Santé")* (level-2),
                bonus.getStats().get("Endurance")* (level),
                bonus.getStats().get("Puissance")* (level+4));
        return new Monster(name + " vicieux",level, stats,loot);
    }

    public Monster generateMonsterBoss(int level){
        List<Item> items;
        Stats stats = StatsBuilder.make(
                bonus.getStats().get("Santé")* (level+10),
                bonus.getStats().get("Endurance")* (level+10),
                bonus.getStats().get("Puissance")* (level+10));
        return new Monster(name + " Boss",level, stats,loot);
    }

    public Monster generateRandom(int level){
        Monster monster = null;
        List<String> types = new ArrayList<String>();
        types.add("Weak");
        types.add("Powerfull");
        types.add("Robust");
        types.add("Vicious");
        types.add("");
        String type = types.get(Dice.roll(types.size()-1));
        try {
            Method method = this.getClass().getMethod("generateMonsterRobust",int.class);
            monster = (Monster)method.invoke(this,level);
        }catch (Exception e){
            e.printStackTrace();
        }

        return monster;
    }

    public String getName() {
        return name;
    }
}
