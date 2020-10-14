package com.company;

public class Horse extends Animal {

    public Horse(String name, String gender) {
        super(name, gender);
        this.price = 1200;
        this.maxAge = 10;
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
