package com.company;

public abstract class Animal {

    String name;
    String gender;
    // Set all animals health to 100 from the beginning
    int health = 100;
    int price;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
    }
}
