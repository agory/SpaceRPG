package me.piatgory.model.Item.consumable;

import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Item.Item;
import me.piatgory.model.state.Effect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Alexandre on 17/01/2016.
 */
@XmlRootElement
public class Consumable extends Item{
    private Effect effect;

    public Consumable(String name, String descritption, int weight, Effect effect) {
        super(name, descritption, weight);
    }

    public Consumable() {
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public void setOn(Entity target){
        effect.setOn(target);
    }
}
