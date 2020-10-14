package com.company;

public class Cat extends Animal {

    public Cat(String name, String gender) {
        super(name, gender);
        this.price = 2000;
        this.maxAge = 9;
        this.possibleChildren = 4;
    }

    public boolean eat(Food f) {
        if(f.name.equals("Apple") || f.name.equals("Meat")){
            return true;
        }
        else{
            return false;
        }
    }
}
