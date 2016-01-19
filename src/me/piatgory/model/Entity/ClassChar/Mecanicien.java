package me.piatgory.model.Entity.ClassChar;

import me.piatgory.model.Capacity.Capacity;
import me.piatgory.model.Entity.Character;
import me.piatgory.model.StatsBuilder;
import me.piatgory.model.state.Buff;
import me.piatgory.model.state.Effect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by gorya on 19/01/2016.
 */
@XmlRootElement
public class Mecanicien extends Character {
    public Mecanicien(String name) {
        super(name);
    }

    public Mecanicien() {
    }

    public void learnSpell(int level){
        switch (level){
            case 2:
                this.getCapacities().add(new Capacity("Coup de clé à molette","Attaque qui reduit l'armure",new Effect(new Buff("Brise Armure 2", StatsBuilder.make(0,-4,0),5),-20),3,true));
                break;
            case 5:
                this.getCapacities().add(new Capacity("Shield","Protège le Mecanicien des attaque enemie ",new Effect(new Buff("Shield surpuissant", StatsBuilder.make(0,4,0),10)),1,false));
                break;
            case 10:
                this.getCapacities().add(new Capacity("Canon déphaseur ","Augmente vos degats infligés et subis",new Effect(new Buff("Déphasé", StatsBuilder.make(0,999,-999),4)),1,true));
                break;
            case 15:
                this.getCapacities().add(new Capacity("Rayon mortel ","Inflige de gros dégat",new Effect(-55),1,true));
                break;
        }

    }
}
