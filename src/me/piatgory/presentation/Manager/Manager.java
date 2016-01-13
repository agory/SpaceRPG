package me.piatgory.presentation.manager;

import me.piatgory.model.Entity.Character;
import me.piatgory.model.Item.Item;

import java.util.List;

/**
 * Created by Alexandre on 10/01/2016.
 */
public class Manager {

    protected Character character;

    public Manager(Character character) {
        this.character = character;
    }


    public static void textSpacer(){
        System.out.println("――――――――――――――――――――――――――――");
    }

    public static void write(Object text){
        System.out.println(text);
    }

    public static String[] convertListItemToStringTab(List<Item> items){
        String[] itemsMenu = new String[items.size()];
        int i = 0;
        for (Item item:items) {
            itemsMenu[i++]= item.getName();
        }
        return itemsMenu;
    }

}
