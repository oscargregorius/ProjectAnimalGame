package com.company;

public abstract class Animal {

    String name;
    String gender;
    int health = 100;
    int price;
    int age;
    int maxAge;
    int possibleChildren;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
        this.age = 0;
    }

    public abstract boolean eat(Food f);
}
