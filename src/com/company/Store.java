package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    static ArrayList<Animal> animals = new ArrayList<Animal>();
    static ArrayList<Food> food = new ArrayList<Food>();
    static Scanner scanner = new Scanner(System.in);
    static String input = "";
    static String input2 = "";

    public static Animal showAnimalsForSale(Player p){
        System.out.println("\n".repeat(50));
        System.out.println("Welcome to the pet shop");
        System.out.println("Your balace: " + p.money);
        System.out.println("Which animal do you want to buy?");
        int co = 1;
        for(Animal a : animals){
            System.out.println(co + ": " + a.name + " price: " + a.price );
            co++;
        }
        input = scanner.next();

        if(input.equals("1")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?");
            input = scanner.next();
            return new Dog(input2,input);
        }
        if(input.equals("2")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?");
            input = scanner.next();
            return new Horse(input2,input);
        }
        if(input.equals("3")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?");
            input = scanner.next();
            return new Cow(input2,input);
        }
        if(input.equals("4")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?");
            input = scanner.next();
            return new Cat(input2,input);
        }
        if(input.equals("5")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?");
            input = scanner.next();
            return new Pig(input2,input);
        }
        return null;
    }

    public static Food showFoodForSale(Player p){
        System.out.println("\n".repeat(50));
        System.out.println("Welcome to the pet shop");
        System.out.println("Your balace: " + p.money);
        System.out.println("What food do you want to buy?");

        int co = 1;
        for(Food f : food){
            System.out.println(co + ": " + f.name + " price: " + f.price);
            co++;
        }
        input = scanner.next();

        if(input.equals("1")){
            System.out.println("How many kilos? Minimum 1kg");
            input2 = scanner.next();
            p.money -= food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2);
            return new Meat("Meat",Integer.parseInt(input2));
        }
        if(input.equals("2")){
            p.money -= food.get(Integer.parseInt(input) - 1).price;
            System.out.println("How many kilos? Minimum 1kg");
            input2 = scanner.next();
            return new Apple("Candy",Integer.parseInt(input2));
        }
        if(input.equals("3")){
            p.money -= food.get(Integer.parseInt(input) - 1).price;
            System.out.println("How many kilos? Minimum 1kg");
            input2 = scanner.next();
            return new Grass("Grass",Integer.parseInt(input2));
        }
        return null;
    }

    public static void buyInAllTheAnimalsAndFood(){
        animals.add(new Dog("dog",""));
        animals.add(new Horse("horse",""));
        animals.add(new Cow("cow",""));
        animals.add(new Cat("cat",""));
        animals.add(new Pig("pig",""));

        food.add(new Meat("Meat",0));
        food.add(new Apple("Candy",0));
        food.add(new Grass("Grass",0));
    }

}
