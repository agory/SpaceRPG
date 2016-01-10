package me.piatgory.model;

import me.piatgory.model.Item.Item;
import me.piatgory.model.Inventory;

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
        for (int i = 0; i < myItems.size(); ++i) {
            if (myItems.get(i) == item) {
                myItems.remove(i);
            }

            // Faire le décalage dans la liste
        }
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

    public String showItems(){
        String message ="\n――――――――――――――――――――――――――――";
        int i = 0;
        for (Item item : getMyItems()){
            message+="\nNumero : "+ i++;
            message+="\n"+item;
            message+="\n――――――――――――――――――――――――――――";
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
}
