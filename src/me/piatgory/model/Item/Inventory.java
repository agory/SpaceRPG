package me.piatgory.model.Item;

import me.piatgory.model.Item.consumable.Consumable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class Inventory {


    private List<Item> myItems;

    public Inventory() {
        myItems = new ArrayList<Item>();
    }

    public int getWeight(){
        int totalWeight=0;
        for(int i=0;i<myItems.size();++i){
            totalWeight += myItems.get(i).getWeight();
        }
        return totalWeight;
    }

    public void removeItem(Item item) {
        myItems.remove(item);
    }

    public void addItem(Item item){
        this.myItems.add(item);
    }

    public List<Item> getMyItems() {
        return myItems;
    }

    public void setMyItems(List<Item> myItems) {
        this.myItems = myItems;
    }

    public Item findItem(int i){
        return this.getMyItems().get(i);
    }

    public List<Item> getConsumable(){
        List<Item> consumables = new ArrayList<Item>();
        for (Item item : myItems){
            if (item instanceof Consumable){
                consumables.add(item);
            }
        }
        return consumables;
    }

    public String showItems(){
        String message ="";
        int i = 0;
        if(myItems.isEmpty()){
            message+="\n Pas d'item dans l'inventaire.";
            message+="\n――――――――――――――――――――――――――――";
        } else {
            for (Item item : getMyItems()) {
                message += "\nNumero : " + i++;
                message += "\n" + item;
                message += "\n――――――――――――――――――――――――――――";
            }
        }
        return message;
    }

    @Override
    public String toString() {
        String message ="\n――――――――――――――――――――――――――――";
        message+="\n――――――――― Inventaire ――――――――――――";
        message+="\n――――――――――――――――――――――――――――";
        message+="\n――― Poids de l'inventaire : " + getWeight();
        message+="\n――――――――――――――――――――――――――――";
        message+= showItems();
        return message;
    }

    public List<String> getItemsMenuInventory(){
        List<String> items = new ArrayList<String>();
        items.add("Selectionner un equipement");
        items.add("Voir le sac");
        return items;
    }
}
