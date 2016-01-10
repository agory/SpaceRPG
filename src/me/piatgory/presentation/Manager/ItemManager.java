package me.piatgory.presentation.Manager;

import me.grea.antoine.utils.Menu;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Item.Equipment;
import me.piatgory.model.Item.Item;

import java.util.List;

/**
 * Created by Alexandre on 10/01/2016.
 */
public class ItemManager extends Manager{
    private List<Item>  itemsTemp;
    private Item  itemTemp;
    private static String[] actionEquipment = {"Afficher","Equiper","Jeter"};
    private static String[] actionItem = {"Afficher","Jeter"};
    private int typeTemp;

    public ItemManager(Character character) {
        super(character);
    }

    public Menu getMenuSelectItem(List<Item> items) {
        itemsTemp = items;
        Menu menu = new Menu("Selection d'un item", "Tapez 0 ou un autre nombre pour quitter", convertListItemToStringTab(items)) {
            @Override
            protected void on(int i) {
                if(itemsTemp.size() > i){
                    Item item = itemsTemp.get(i);
                    textSpacer();
                    write(item);
                    getMenuItemInventaireAction(item).display();
                    textSpacer();
                } else {
                    write("Choix : quitter");
                }
            }
        };
        return menu;
    }

    public Menu getMenuItemInventaireAction(Item item){
        itemTemp = item;
        String[] items;
        typeTemp = 0;
        if(item instanceof Equipment){
            items = actionEquipment;
            typeTemp = 1;
        } else {
            write("C'est un item !!");
            items = actionItem;
        }

        Menu menu = new Menu("Selection d'un item", "Tapez un autre nombre pour quitter", items) {
            @Override
            protected void on(int i) {
                switch (typeTemp){
                    case 0:
                        actionItemInventaire(i);
                        break;
                    case 1:
                        actionEquipementInventaire(i);
                        break;
                }
            }
        };
        return menu;
    }

    public void actionItemInventaire(int i){
        switch (i)
        {
            case 0:
                write(itemsTemp);
                break;
            case 1:
                this.character.getInventory().removeItem(itemTemp);
                break;
            default:
                write("Choix : Quitter");
                break;
        }
    }

    public void actionEquipementInventaire(int i){
        switch (i)
        {
            case 0:
                write(itemTemp);
                break;
            case 1:
                write(itemTemp);
                textSpacer();
                write("Á la place de :");
                textSpacer();
                write(this.character.takePlaceOf((Equipment)itemTemp));
                String[] items = {"Valider"};
                (new Menu("Validation","Tapez un autre nombre",items){
                    @Override
                    protected void on(int i) {
                        switch (i){
                            case 0:
                                character.equip((Equipment) itemTemp);
                                character.getInventory().removeItem(itemTemp);
                                break;
                            default:
                                write("Choix : Annulation");
                                break;
                        }
                    }
                }).display();
            case 2:
                this.character.getInventory().removeItem(itemTemp);
                break;
            default:
                write("Choix : Quitter");
                break;
        }
    }
}
