package me.piatgory.model;

import me.piatgory.model.Item.Item;

import java.util.List;

/**
 * Created by Grégoire on 17/12/2015.
 */
public class Inventory {


    private List<Item> myItems;

    public Inventory() {
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
        this.myItems[myItems.size()]=item;
    }

    public List<Item> getMyItems() {
        return myItems;
    }

    public void setMyItems(List<Item> myItems) {
        this.myItems = myItems;
    }


}
