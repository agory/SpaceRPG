package me.piatgory.presentation.Manager;

import me.grea.antoine.utils.Log;
import me.grea.antoine.utils.Menu;
import me.piatgory.model.Entity.Character;

/**
 * Created by Alexandre on 10/01/2016.
 */
public class CharacterManager extends Manager{
    private Character character;
    private Menu menu;
    private Menu menuInventaire;
    private Menu menuEquipment;

    public CharacterManager(Character character){
        this.character = character;
    }


    public void show(){
        getMenu().display();
    }

    public Menu getMenu() {
        if(menu == null) {
            String[] items = {"Menu l'inventaire","Menu Equipement","Character description"};
            menu = new Menu("Character Menu", "Tapez un autre nombre pour quitter", items) {
                @Override
                protected void on(int i) {
                    switch (i)
                    {
                        case 0:
                            getMenuInventaire();
                            break;
                        case 1:
                            getMenuEquipment();
                            break;
                        case 2:
                            System.out.println(character);
                            this.display();
                            break;
                        default:
                            System.out.println("Choix : Quitter");
                            break;
                    }
                }
            };
        }
        return menu;
    }

    public Menu getMenuInventaire() {
        if(menuInventaire == null) {
            String[] items = {"Menu l'inventaire","Menu Equipement","Voir le sac"};
            menuInventaire = new Menu("Character Menu", "Quitter", items) {
                @Override
                protected void on(int i) {
                    switch (i)
                    {
                        case 0:
                            getMenuInventaire();
                            break;
                        case 1:
                            getMenuEquipment();
                            break;
                        case 2:
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

    public Menu getMenuEquipment() {
        if(menuInventaire == null) {
            String[] items = {"Menu l'inventaire","Menu Equipement","Voir les stats"};
            menuInventaire = new Menu("Character Menu", "Quitter", items) {
                @Override
                protected void on(int i) {

                }
            };
        }
        return menuEquipment;
    }
}
