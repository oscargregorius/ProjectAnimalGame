package com.company;

public class Horse extends Animal {

    public Horse(String name, String gender) {
        super(name, gender);
        this.price = 2000;
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
