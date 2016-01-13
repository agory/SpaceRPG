package me.piatgory.model.Item;

import me.grea.antoine.utils.Dice;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.Inventory;
import me.piatgory.persistance.DataGame;
import me.piatgory.presentation.Manager.CharacterManager;

import java.util.List;

/**
 * Created by Gregoire on 12/01/2016.
 */

// The player can find a chest, open it and find some items inside

public class Chest {

    private List<Item> chestContent;
    private List<Item> generatedContent;

    public Chest(List<Item> generatedContent){
        this.generatedContent = generatedContent;
    }

    public void generateChestContent(){
        Dice dice = new Dice();
        int value = dice.roll(1,3);
        for (int i=0;i<generatedContent.size();++i){
            
        }

    }

    public String openChest(){

        String message = "――――――――――――――――――――――\n"
                        +"――――――Contenu du coffre――――――\n"+
                         "――――――――――――――――――――――\n";
        message += "Poids total du coffre : "+this.getChestWeight()+"\n";
        message += "Nombre d'objets : "+ chestContent.size()+"\n";
        Item currentItem;
        for(int i=0;i<chestContent.size();++i){
            currentItem=chestContent.get(i);
            message+= "――ITEM "+i+" ――\nNom : "+currentItem.getName()+
                    "\n Description : "+currentItem.getDescritption()+
                    "\n Poids : "+currentItem.getWeight()+
                    "――――――――――――――――――――――\n";
        }
        return message;
    }

    //Return the whole content of the chest
    public List<Item> pickupWholeChest() {
        return chestContent;
    }

    //Return the item of chestContent with the rank itemRank
    public Item pickUpItemNbr(int itemRank){
        Item item = null;
        if(chestContent.get(itemRank)!=null){
            item = chestContent.get(itemRank);
        }
        return item;
    }

    public int getChestWeight(){
        int chestWeight=0;
        for (Item item : chestContent){
            chestWeight+=item.getWeight();
        }
        return chestWeight;
    }
}
