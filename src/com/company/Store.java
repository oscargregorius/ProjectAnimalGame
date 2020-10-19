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
            showInfoAboutAnimals();
            System.out.println(Player.ANSI_YELLOW + "Welcome to the pet shop");
            System.out.println("Your balance: " + p.money);
            System.out.println("Which animal do you want to buy?" + Player.ANSI_RESET);
            int co = 1;
            for (Animal a : animals) {
                System.out.println(co + ": " + a.name + " price: " + a.price);
                co++;
            }
                while (true) {
                    try {
                        input = scanner.next();
                        if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) {
                            System.out.println("Not a valid move, type a number between 1-5");
                        }
                        else if(Store.animals.get(Integer.parseInt(input) -1).price > p.money){
                            System.out.println("You don't have enough money for this animal.");
                        }
                        else{
                            break;
                        }
                    }catch (Exception e){
                        System.out.println("Not a valid move");
                    }
                }

        if(input.equals("1")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?\n1: Male \n2: Female");
            while (true) {
                input = scanner.next();
                if (input.equals("1")) {
                    return new Dog(input2, "male");
                }
                if(input.equals("2")) {
                    return new Dog(input2, "female");
                }
                System.out.println("Not a valid move. Type 1 or 2");
            }
        }
        if(input.equals("2")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?\n1: Male \n2: Female");
            while (true) {
                input = scanner.next();
                if (input.equals("1")) {
                    return new Horse(input2, "male");
                }
                if(input.equals("2")) {
                    return new Horse(input2, "female");
                }
                System.out.println("Not a valid move. type 1 or 2");
            }
        }
        if(input.equals("3")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?\n1: Male \n2: Female");
            while (true) {
                input = scanner.next();
                if (input.equals("1")) {
                    return new Cow(input2, "male");
                }
                if(input.equals("2")) {
                    return new Cow(input2, "female");
                }
                System.out.println("Not a valid move. Type 1 or 2");
            }
        }
        if(input.equals("4")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?\n1: Male \n2: Female");
            while (true) {
                input = scanner.next();
                if (input.equals("1")) {
                    return new Cat(input2, "male");
                }
                if(input.equals("2")) {
                    return new Cat(input2, "female");
                }
                System.out.println("Not a valid move. Type 1 or 2");
            }
        }
        if(input.equals("5")){
            p.money -= animals.get(Integer.parseInt(input) - 1).price;
            System.out.println("What do you want to call your " + animals.get(Integer.parseInt(input) - 1).name);
            input2 = scanner.next();
            System.out.println("What gender?\n1: Male \n2: Female");
            while (true) {
                input = scanner.next();
                if (input.equals("1")) {
                    return new Pig(input2, "male");
                }
                if(input.equals("2")) {
                    return new Pig(input2, "female");
                }
                System.out.println("Not a valid move. Type 1 or 2.");
            }
        }
        return null;
    }

    public static void showFoodForSale(Player p){
        System.out.println("\n".repeat(50));
        showInfoAboutFood();
        System.out.println(Player.ANSI_YELLOW + "Welcome to the pet shop");
        System.out.println("Your balace: " + p.money);
        System.out.println("What food do you want to buy?" + Player.ANSI_RESET);
        int co = 1;
        for(Food f : food){
            if(f.name.equals("Meat")) {
                System.out.println(co + ": " + f.name + " price: " + f.price);
            }
            if(f.name.equals("Apple")) {
                System.out.println(co + ": " + f.name + " price: " + f.price);
            }
            if(f.name.equals("Grass")) {
                System.out.println(co + ": " + f.name + " price: " + f.price);
            }
            co++;
        }
        while(true) {
            try {
                input = scanner.next();
                if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 3) {
                    System.out.println("Not a valid move");
                }
                else if (Store.food.get(Integer.parseInt(input) - 1).price > p.money) {
                    System.out.println("You dont have enough money for that.");
                } else {
                    break;
                }
            }catch (Exception e){
                System.out.println("Not a valid move");
            }
        }

        if(input.equals("1")){
            System.out.println("How many kilos? Minimum 1kg");
            while (true) {
                try {
                    input2 = scanner.next();
                    if (Integer.parseInt(input2) <= 0){
                        System.out.println("You have to buy at least 1KG");
                        }
                        else if (p.money < food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2)) {
                            System.out.println("You dont have that much money. Try again.");
                        }
                        else {
                            break;
                        }
                    }catch (Exception e){
                        System.out.println("Not a valid move..");
                    }
            }
            p.money -= food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2);
            if(checkIfAllreadyBought(p, "Meat")){return;}
            p.food.add(new Meat("Meat",Integer.parseInt(input2)));
            return;
        }
        if(input.equals("2")){
            System.out.println("How many kilos? Minimum 1kg");
            while (true) {
                try {
                    input2 = scanner.next();
                    if (Integer.parseInt(input2) <= 0){
                        System.out.println("You have to buy at least 1KG");
                    }
                    else if (p.money < food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2)) {
                        System.out.println("You dont have that much money. Try again.");
                    }
                    else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Not a valid move..");
                }
            }
            p.money -= food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2);
            if(checkIfAllreadyBought(p,"Apple")){return;}
            p.food.add(new Apple("Apple",Integer.parseInt(input2)));
            return;
        }
        if(input.equals("3")){
            System.out.println("How many kilos? Minimum 1kg");
            while (true) {
                try {
                    input2 = scanner.next();
                    if (Integer.parseInt(input2) <= 0){
                        System.out.println("You have to buy at least 1KG");
                    }
                    else if (p.money < food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2)) {
                        System.out.println("You dont have that much money. Try again.");
                    }
                    else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Not a valid move..");
                }
            }
            p.money -= food.get(Integer.parseInt(input) - 1).price * Integer.parseInt(input2);
            if(checkIfAllreadyBought(p,"Grass")){return;}
            p.food.add(new Grass("Grass",Integer.parseInt(input2)));
            return;
        }
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
    public static boolean checkIfAllreadyBought(Player p, String type){
        for(int i = 0; i < p.food.size(); i++){
            for(int j = 0; j < p.food.size(); j++){
                if(p.food.get(i).getName().equals(type)){
                    p.food.get(i).kg += Integer.parseInt(input2);
                    return true;
                }
            }
        }
        return false;
    }
    public static void showInfoAboutAnimals(){
        System.out.println(Player.ANSI_PURPLE + "-".repeat(70));
        System.out.println("INFO ABOUT THE ANIMALS");
        System.out.println("1: DOG: Maximum age = 14 | Possible children: 4 | Food: Meat\n" +
                "2: HORSE: Maximum age = 10 | Possible children: 1 | Food: Grass\n" +
                "3: COW: Maximum age = 6 | Possible children: 1 | Food: Grass\n" +
                "4: CAT: Maximum age = 9 | Possible children: 4 | Food: Apple, Meat\n" +
                "5: PIG: Maximum age = 5 | Possible children: 3 | Food: Grass");
        System.out.println("-".repeat(70)+"\n" + Player.ANSI_RESET);
    }

    public static void showInfoAboutFood(){
        System.out.println(Player.ANSI_PURPLE + "-".repeat(50));
        System.out.println("INFO ABOUT THE FOOD");
        System.out.println("1: MEAT: Dogs and cats eats meat.\n" +
                "2: APPLE: Cats eats this.\n" +
                "3: GRASS: Horses, cows and pigs eats this.");
        System.out.println("Every kg food gives 10HP");
        System.out.println("-".repeat(50)+"\n" + Player.ANSI_RESET);
    }

}
