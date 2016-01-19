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
public class Berserker extends Character{
    public Berserker(String name) {
        super(name);
    }

    public Berserker() {
    }

    public void learnSpell(int level){
        switch (level){
            case 2:
                this.getCapacities().add(new Capacity("Attaque rapide","Attaque qui permet d'agir en premier.",new Effect(-40),0,true));
                break;
            case 5:
                this.getCapacities().add(new Capacity("Coup puissant","Attaque qui sonne légérement l'enemi.",new Effect(new Buff("Affaibli 2", StatsBuilder.make(0,-1,-2),2),-40),4,true));
                break;
            case 10:
                this.getCapacities().add(new Capacity("Berserker","Augmente vos degats infligés et subis",new Effect(new Buff("Berserker Ultime", StatsBuilder.make(0,-7,14),10)),1,false));
                break;
            case 15:
                this.getCapacities().add(new Capacity("Même pas mal","Reduit les degats subis",new Effect(new Buff("Même pas mal", StatsBuilder.make(0,5,0),10)),1,false));
                break;
        }

    }
}
