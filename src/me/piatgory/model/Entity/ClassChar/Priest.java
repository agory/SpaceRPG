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
public class Priest extends Character {
    public Priest(String name) {
        super(name);
    }

    public Priest() {
    }

    public void learnSpell(int level){
        switch (level){
            case 2:
                this.getCapacities().add(new Capacity("Prière divine ","Soigne",new Effect(30),4,false));
                break;
            case 5:
                this.getCapacities().add(new Capacity("Mot de faiblesse","affaibli l'ennemi ",new Effect(new Buff("Affaibli 2", StatsBuilder.make(0,0,-8),5)),1,true));
                break;
            case 10:
                this.getCapacities().add(new Capacity("Mot de douleur","Inflige des degats",new Effect(-20),1,true));
                break;
            case 15:
                this.getCapacities().add(new Capacity("Jugement divin","Inflige de gros dégat et donne un gros malus à l'énnemi",new Effect(new Buff("Transcendance inverse", StatsBuilder.make(0,-8,-8),5),-45),1,true));
                break;
        }

    }
}
