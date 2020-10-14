package com.company;

public class Pig extends Animal {

    public Pig(String name, String gender) {
        super(name, gender);
        this.price = 2000;
        this.maxAge = 5;
        this.possibleChildren = 3;
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
