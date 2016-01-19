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
public class Alchimiste extends Character {
    public Alchimiste(String name) {
        super(name);
    }

    public Alchimiste() {
    }

    public void learnSpell(int level){
        switch (level){
            case 2:
                this.getCapacities().add(new Capacity("Mixture corrosif ","Attaque qui reduit l'armure",new Effect(new Buff("Brise Armure 2", StatsBuilder.make(0,-5,0),10),-10),3,true));
                break;
            case 5:
                this.getCapacities().add(new Capacity("Mixture affaiblissante","Attaque qui affaibli l'ennemi ",new Effect(new Buff("Affaibli 2", StatsBuilder.make(0,0,-4),10)),1,true));
                break;
            case 10:
                this.getCapacities().add(new Capacity("Mixture de transcendance ","Augmente vos degats infligés et subis",new Effect(new Buff("Transcendance", StatsBuilder.make(15,4,4),10),15),1,false));
                break;
            case 15:
                this.getCapacities().add(new Capacity("Mixture de Transcendance inverse","Inflige de gros dégat",new Effect(new Buff("Transcendance inverse", StatsBuilder.make(0,-8,-8),5),-20),1,true));
                break;
        }

    }
}
