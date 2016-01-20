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
                this.getCapacities().add(new Capacity("Coup de corne","Attaque qui permet de transpercer l'ennemi.",new Effect(-20),0,true));
                break;
            case 5:
                this.getCapacities().add(new Capacity("Coup de sabots","Attaque qui sonne l'enemi.",new Effect(new Buff("Sonne ennemi", StatsBuilder.make(0,-1,-2),2),-20),4,true));
                break;
            case 15:
                this.getCapacities().add(new Capacity("Flash !","Aveugle l'ennemi",new Effect(new Buff("Cécité", StatsBuilder.make(0,0,-999),3),-10),1,true));
                break;
            case 20:
                this.getCapacities().add(new Capacity("Unicorn Powa !","Augmente vos degats infligés et la resistance. Rend des points vie.",new Effect(new Buff("Unicorn Ultime", StatsBuilder.make(0,6,14),20)),1,false));
                break;
        }
    }
}