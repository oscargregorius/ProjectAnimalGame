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
            for (Animal a : animals) {
                System.out.println(co + ": " + a.name + " price: " + a.price);
                co++;
            }
            while (true){
            input = scanner.next();
            if(Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5){
                System.out.println("Not a valid move, type a number between 1-5");
            }else{
                break;
            }
        }
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
            if(f.name.equals("Meat")) {
                System.out.println(co + ": (Dogs and cats food) " + f.name + " price: " + f.price);
            }
            if(f.name.equals("Apple")) {
                System.out.println(co + ": (cats food) " + f.name + " price: " + f.price);
            }
            if(f.name.equals("Grass")) {
                System.out.println(co + ": (Horses, cows and pigs food) " + f.name + " price: " + f.price);
            }
            co++;
        }
        while (true){
            input = scanner.next();
            if(Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5){
                System.out.println("Not a valid move, type a number between 1-3");
            }else{
                break;
            }
        }

        if(input.equals("1")){
            System.out.println("How many kilos? Minimum 1kg");
            while (true) {
                input2 = scanner.next();
                if(Integer.parseInt(input2) < 1 || p.money < Integer.parseInt(input2) * food.get(Integer.parseInt(input) - 1).price){
                    System.out.println("Check your balance, and make sure that you buy at least 1kg");
                }else{
                    break;
                }
            }
            p.money -= food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2);
            return new Meat("Meat",Integer.parseInt(input2));
        }
        if(input.equals("2")){
            System.out.println("How many kilos? Minimum 1kg");
            while (true) {
                input2 = scanner.next();
                if(Integer.parseInt(input2) < 1 || p.money < Integer.parseInt(input2) * food.get(Integer.parseInt(input) - 1).price){
                    System.out.println("Check your balance, and make sure that you buy at least 1kg");
                }else{
                    break;
                }
            }
            p.money -= food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2);
            return new Apple("Candy",Integer.parseInt(input2));
        }
        if(input.equals("3")){
            System.out.println("How many kilos? Minimum 1kg");
            while (true) {
                input2 = scanner.next();
                if(Integer.parseInt(input2) < 1 || p.money < Integer.parseInt(input2) * food.get(Integer.parseInt(input) - 1).price){
                    System.out.println("Check your balance, and make sure that you buy at least 1kg");
                }else{
                    break;
                }
            }
            p.money -= food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2);
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
        food.add(new Apple("Apple",0));
        food.add(new Grass("Grass",0));
    }

}
