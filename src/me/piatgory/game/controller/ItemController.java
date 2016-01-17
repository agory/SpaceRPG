package me.piatgory.game.controller;

import me.piatgory.game.core.CoreController;
import me.piatgory.model.Item.Equipment.Equipment;
import me.piatgory.model.Item.Item;
import me.piatgory.persistance.DataGame;

import java.util.List;

/**
 * Created by Alexandre on 10/01/2016.
 */
public class ItemController extends CoreController {


    public ItemController(DataGame dataGame) {
        super(dataGame);
    }

    public void run(){
        write("Do nothing");
    }

    public void showMenuSelectItem(List<Item> items) {
        Item item = getItemByMenuSelectItem(items);
        if(item != null){
            textSpacer();
            write(item);
            showMenuItemAction(item);
            textSpacer();
        }
    }

    public Item getItemByMenuSelectItem(List<Item> items) {
        Item item = null;
        int i = showMenu("Selection d'un item", "Tapez 0 ou un autre nombre pour quitter", convertListItemToStringTab(items));
        if(items.size() > i && i >= 0){
            item = items.get(i);
        } else {
            write("Choix : quitter");
            textSpacer();
        }
        return item;
    }

    public void showMenuItemAction(Item item){

        List<String> items;
        int typeTemp;
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
                write(item);
                showMenuItemAction(item);
                break;
            case 1:
                this.itemSwitchActionRemovePut(item);
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
                showMenuItemAction(item);
                break;
            case 1:
                equipAction(item);
                break;
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
                getCharacter().getInventory().removeItem(item);
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
        write("A la place de :");
        textSpacer();
        write(this.getCharacter().takePlaceOf((Equipment)item));
        if(validation("Etes-vous sûre de vouloir Equiper cette item ?")){
            getCharacter().getInventory().addItem(getCharacter().equip((Equipment) item));
            getCharacter().getInventory().removeItem(item);
        }else {
            write("Action annuler.");
            textSpacer();
        }
    }
}
