package com.company;

public class Cow extends Animal {

    public Cow(String name, String gender) {
        super(name, gender);
        this.price = 2000;
    }

    @Override
    public boolean eat(Food f) {
        if(!f.name.equals("Grass")){
            System.out.println("I dont eat that type of food...");
            return false;
        }else{
            return true;
        }
    }
}
