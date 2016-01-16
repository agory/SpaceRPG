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

}
