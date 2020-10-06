package com.company;

public class Cat extends Animal {

    public Cat(String name, String gender) {
        super(name, gender);
        this.price = 2000;
    }

    public boolean eat(Food f) {
        if(!f.name.equals("Meat") || f.name.equals("Apple")){
            return false;
        }else{
            return true;
        }
    }
}
