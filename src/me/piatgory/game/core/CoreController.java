package me.piatgory.game.core;

import me.piatgory.model.Entity.Character;
import me.piatgory.model.Item.Item;
import me.piatgory.persistance.DataGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 10/01/2016.
 */
public abstract class CoreController extends Game{

    public CoreController(DataGame dataGame) {
        super(dataGame);
    }

    public static List<String> convertListItemToStringTab(List<Item> items){
        List<String> itemsMenu = new ArrayList<String>();
        int i = 0;
        for (Item item:items) {
            itemsMenu.add(item.getName());
        }
        return itemsMenu;
    }

    public boolean isInInventory(Item item){
        return getCharacter().getInventory().getMyItems().contains(item);
    }

    public List<Item> removeDoubloonItem(List<Item> items){
        for(int i =0; i<items.size();i++){
            if (isInInventory(items.get(i)))
                items.remove(items.get(i));
        }
        return items;
    }

}
