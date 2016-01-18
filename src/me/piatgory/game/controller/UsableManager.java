package me.piatgory.game.controller;

import me.piatgory.game.Action.Usable;
import me.piatgory.game.core.CoreController;
import me.piatgory.model.Item.Equipment.Equipment;
import me.piatgory.model.Item.Item;
import me.piatgory.model.Item.consumable.Consumable;
import me.piatgory.persistance.DataGame;

import java.util.List;

/**
 * Created by Alexandre on 18/01/2016.
 */
public class UsableManager extends CoreController{

    public UsableManager(DataGame dataGame) {
        super(dataGame);
    }



    public Usable getUsableByMenuSelect(List<? extends Usable> usables) {
        Usable usable = null;
        if(usables.size()> 0) {
            int i = showMenu("Selection d'un item", "Tapez 0 ou un autre nombre pour quitter", convertListUsableToStringTab(usables));
            if (usables.size() > i && i >= 0) {
                usable = usables.get(i);
                if(usable == null)
                    usable = getUsableByMenuSelect(usables);
            } else {
                write("Choix : quitter");
                textSpacer();
            }
        } else {
            write("Aucun élément à disposition");
        }
        return usable;
    }

    public Usable showMenuItemAction(Usable usable){
        int i = showMenu("Selection d'un item", "Tapez un autre nombre pour quitter", usable.getMenuUsableAction());
        switch (i)
        {
            case 0:
                break;
            case 1:
                write(usable);
                usable = showMenuItemAction(usable);
                break;
            default:
                write("Choix : Quitter");
                usable = null;
                textSpacer();
                break;
        }
        return usable;

    }


    public void run(){
        write("Do Nothing");
    }
}
