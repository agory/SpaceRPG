package me.piatgory.model;

/**
 * Created by Grégoire on 10/12/2015.
 */
public class Character {

    private String name;
    private double health;
    private double stamina; //Résistance
    private double power;
    private double accurancy;
    private double weight;


    public Character(){

    }

    public Character(String name, double health, double stamina, double power, double accurancy, double weight){
        this.name = name;
        this.health = health;
        this.stamina = stamina;
        this.power = power;
        this.accurancy = accurancy;
        this.weight = weight;
    }
}
