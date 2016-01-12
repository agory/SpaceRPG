package me.piatgory.model.Item;

import java.util.List;

/**
 * Created by Gregoire on 12/01/2016.
 */
// The player can find a chest, open it and find some items inside

public class Chest {

    private List<Item> chestContent;

    public Chest(List<Item> chestContent){
        this.chestContent = chestContent;
    }

    public String openChest(){
        String message = "Contenu du coffre\n";
        Item currentItem;
        for(int i=0;i<chestContent.size();++i){
            currentItem=chestContent.get(i);
            message+= "Nom : "+currentItem.getName()+"\n Description : "+currentItem.getDescritption()+"\n Poids : "+currentItem.getWeight()+"------------------------------\n";
        }
        return message;
    }
}
