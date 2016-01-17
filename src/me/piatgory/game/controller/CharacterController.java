package me.piatgory.game.controller;

import me.piatgory.game.core.CoreController;
import me.piatgory.persistance.DataGame;
/**
 * Created by Alexandre on 10/01/2016.
 */
public class CharacterController extends CoreController {

    public CharacterController(DataGame dataGame) {
        super(dataGame);
    }

    public void run(){
        showMenuCharacter();
    }

    public void showMenuCharacter() {
        switch (showMenu("Character Menu", "Tapez un autre nombre pour quitter", getCharacter().getItemsMenuCharacter()) )
        {
            case 0:
                showMenuInventaire();
                this.showMenuCharacter();
                break;
            case 1:
                write(getCharacter().showEquipement());
                this.showMenuCharacter();
                break;
            case 2:
                write(getCharacter().showStats());
                this.showMenuCharacter();
                break;
            case 3:
                write(getCharacter());
                this.showMenuCharacter();
                break;
            default:
                write("Choix : Quitter");
                textSpacer();
                break;
        }

    }


    public void showMenuInventaire() {
        switch (showMenu("Character Menu", "Tapez un autre nombre pour quitter", getCharacter().getInventory().getItemsMenuInventory())) {
            case 0:
                (new ItemController(dataGame)).showMenuSelectItem(getCharacter().getInventory().getMyItems());
                this.showMenuInventaire();
                break;
            case 1:
                write(getCharacter().getInventory());
                this.showMenuInventaire();
                break;
            default:
                write("Choix : Quitter");
                textSpacer();
                break;
        }

    }


}
