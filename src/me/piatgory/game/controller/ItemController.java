package me.piatgory.game.controller;

import me.grea.antoine.utils.Menu;
import me.piatgory.game.core.CoreController;
import me.piatgory.model.Item.Equipment;
import me.piatgory.model.Item.Item;
import me.piatgory.persistance.DataGame;

import java.util.List;

/**
 * Created by Alexandre on 10/01/2016.
 */
public class ItemController extends CoreController {
    private List<Item>  itemsTemp;
    private Item  itemTemp;

    public ItemController(DataGame dataGame) {
        super(dataGame);
    }

    public void run(){
        write("Do nothing");
    }

    public void showMenuSelectItem(List<Item> items) {
        itemsTemp = items;
        int i = showMenu("Selection d'un item", "Tapez 0 ou un autre nombre pour quitter", convertListItemToStringTab(items));
        if(itemsTemp.size() > i && i >= 0){
            Item item = itemsTemp.get(i);
            textSpacer();
            write(item);
            showMenuItemAction(item);
            textSpacer();
        } else {
            write("Choix : quitter");
            textSpacer();
        }

    }

    public void showMenuItemAction(Item item){
        itemTemp = item;
        List<String> items;
        final int typeTemp;
        if(item instanceof Equipment){
            items = Equipment.getMenuInventaireAction();
            typeTemp = 1;
        } else {
            typeTemp = 0;
            items = Item.getMenuInventaireAction();
        }

        if(!isInInventory(item)){
            items.set(items.size()-1,"Mettre dans le sac");
        }


        int i = showMenu("Selection d'un item", "Tapez un autre nombre pour quitter", items);

        switch (typeTemp){
            case 0:
                actionItemInventaire(i,item);
                break;
            case 1:
                actionEquipementInventaire(i,item);
                break;
        }


    }

    public void actionItemInventaire(int i,Item item){
        switch (i)
        {
            case 0:
                write(itemsTemp);
                break;
            case 1:
                this.itemSwitchActionRemovePut(itemTemp);
                break;
            default:
                write("Choix : Quitter");
                textSpacer();
                break;
        }
    }

    public void actionEquipementInventaire(int i,Item item){
        switch (i)
        {
            case 0:
                write(item);
                break;
            case 1:
                equipAction(item);
            case 2:
                this.itemSwitchActionRemovePut(item);
                break;
            default:
                write("Choix : Quitter.");
                textSpacer();
                break;
        }
    }



    private void itemSwitchActionRemovePut(Item item){
        if (isInInventory(item)){
            if(validation("Etes-vous sûre de vouloir jeter cette item ?")){
                getCharacter().getInventory().removeItem(itemTemp);
            }else {
                write("Action annuler.");
                textSpacer();
            }
        } else {
            try {
                getCharacter().addToInventory(item);
            } catch (Exception e){
                textSpacer();
                write("Attention votre inventaire est plein. Veuillez supprimer des items dans votre inventaire");
                textSpacer();
                showMenuSelectItem(getCharacter().getInventory().getMyItems());
            }

        }
    }

    private void equipAction(Item item){
        write(item);
        textSpacer();
        write("Á la place de :");
        textSpacer();
        write(this.getCharacter().takePlaceOf((Equipment)item));
        if(validation("Etes-vous sûre de vouloir Equiper cette item ?")){
            getCharacter().getInventory().addItem(getCharacter().equip((Equipment) item));
            if(isInInventory(item))
                getCharacter().getInventory().removeItem(item);
        }else {
            write("Action annuler.");
            textSpacer();
        }
    }
}
