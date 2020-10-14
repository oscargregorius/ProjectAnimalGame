package com.company;

public class Cow extends Animal {

    public Cow(String name, String gender) {
        super(name, gender);
        this.price = 1000;
        this.maxAge = 6;
        this.possibleChildren = 1;
    }

    @Override
    public boolean eat(Food f) {
        if(!f.name.equals("Grass")){
            return false;
        }else{
            return true;
        }
    }
}
