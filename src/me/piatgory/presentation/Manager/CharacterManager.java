package me.piatgory.presentation.Manager;

import me.grea.antoine.utils.Log;
import me.grea.antoine.utils.Menu;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Item.Item;

import java.awt.event.ItemListener;
import java.util.List;

/**
 * Created by Alexandre on 10/01/2016.
 */
public class CharacterManager extends Manager{
    private Menu menu;
    private Menu menuInventaire;

    public CharacterManager(Character character){
        super(character);
    }


    public void show(){
        getMenu().display();
    }

    public Menu getMenu() {
        if(menu == null) {
            String[] items = {"Menu l'inventaire","Voir Equipement","Voir les stats","voir Character all description"};
            menu = new Menu("Character Menu", "Tapez un autre nombre pour quitter", items) {
                @Override
                protected void on(int i) {
                    switch (i)
                    {
                        case 0:
                            getMenuInventaire().display();
                            this.display();
                            break;
                        case 1:
                            write(character.showEquipement());
                            this.display();
                            break;
                        case 2:
                            write(character.showStats());
                            this.display();
                            break;
                        case 3:
                            write(character);
                            this.display();
                            break;
                        default:
                            write("Choix : Quitter");
                            break;
                    }
                }
            };
        }
        return menu;
    }

    public Menu getMenuInventaire() {
        if(menuInventaire == null) {
            String[] items = {"Selectionner un equipement","Voir le sac"};
            menuInventaire = new Menu("Character Menu", "Tapez un autre nombre pour quitter", items) {
                @Override
                protected void on(int i) {
                    switch (i)
                    {
                        case 0:
                            new ItemManager(character).getMenuSelectItem(character.getInventory().getMyItems()).display();
                            this.display();
                            break;
                        case 1:
                            System.out.println(character.getInventory());
                            this.display();
                            break;
                        default:
                            System.out.println("Choix : Quitter");
                            break;
                    }
                }
            };
        }
        return menuInventaire;
    }



}
