package me.piatgory.model.Entity.ClassChar;

import me.piatgory.model.Capacity.Capacity;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.StatsBuilder;
import me.piatgory.model.state.Buff;
import me.piatgory.model.state.Effect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Gregoire on 19/01/2016.
 */
@XmlRootElement
public class ChasseurLicorne extends Character{
    public ChasseurLicorne(String name) {
        super(name);
    }

    public ChasseurLicorne() {
    }

    public void learnSpell(int level){
        switch (level){
            case 2:
                this.getCapacities().add(new Capacity("Coup de corne","Attaque qui permet de transpercer l'ennemi.",new Effect(-40),0,true));
                break;
            case 5:
                this.getCapacities().add(new Capacity("Coup de sabots","Attaque qui sonne l'enemi.",new Effect(new Buff("Sonne ennemi", StatsBuilder.make(0,-1,-2),2),-40),4,true));
                break;
            case 10:
                this.getCapacities().add(new Capacity("Unicorn Powa !","Augmente vos degats infligés et subis",new Effect(new Buff("Unicorn Ultime", StatsBuilder.make(0,-7,14),10)),1,false));
                break;
        }
    }
}