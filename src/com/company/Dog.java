package com.company;

public class Dog extends Animal {

    public Dog(String name, String gender) {
        super(name, gender);
        this.price = 2000;
    }

    public boolean eat(Food f) {
        if(!f.name.equals("Meat")){
            return false;
        }else{
            return true;
        }
    }
}
