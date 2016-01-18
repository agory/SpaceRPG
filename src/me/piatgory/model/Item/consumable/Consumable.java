package me.piatgory.model.Item.consumable;

import me.piatgory.game.Action.Usable;
import me.piatgory.model.Entity.Entity;
import me.piatgory.model.Item.Item;
import me.piatgory.model.state.Effect;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 17/01/2016.
 */
@XmlRootElement
public class Consumable extends Item implements Usable{
    private Effect effect;
    private boolean harmful;


    public Consumable(String name, String descritption, Effect effect) {
        super(name, descritption, 1);
        this.effect = effect;
        this.harmful = false;
    }
    public Consumable(String name, String descritption, Effect effect,boolean harmful) {
        super(name, descritption, 1);
        this.effect = effect;
        this.harmful = harmful;
    }

    public Consumable getNewInstance(){
        return new Consumable(getName(),getDescritption(),getEffect(),isHarmful());
    }

    public Consumable() {
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public String  use(Entity target){
        return effect.use(target);
    }

    public boolean isHarmful() {
        return harmful;
    }

    public void setHarmful(boolean harmful) {
        this.harmful = harmful;
    }

    public int getPriority() {
        return 1;
    }

    public static List<String> getMenuInventaireAction(){
        List<String> actions = new ArrayList<String>();
        actions.add("Utiliser");
        actions.add("Voir la description");
        actions.add("Jeter");
        return  actions;
    }

    public List<String> getMenuUsableAction() {
        List<String> actions = getMenuInventaireAction();
        actions.remove(2);
        return actions;
    }


}
