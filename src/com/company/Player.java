package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
    String name;
    int money;
    Random random = new Random();
    ArrayList<Animal> animals = new ArrayList<Animal>();
    ArrayList<Food> food = new ArrayList<Food>();
    Scanner scanner = new Scanner(System.in);
    String input = "";
    String input2 = "";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Player(String name){
        this.name = name;
        this.money = 10000;
    }

    public void buyAnimal(){
        if(checkBalance()){
            System.out.println("You dont have enough money. Press any key following by enter to continue");
            scanner.next();
            return;
        }
        do {
            animals.add(Store.showAnimalsForSale(this));
            System.out.println("Buy another one? y/n");
            String input = scanner.next();
            if(input.equals("n")){
                return;
            }
        }while (money !=0);
    }

    public void buyFood(){
        if(checkBalance()){
            System.out.println("You dont have enough money. Press any key following by enter to continue");
            scanner.next();
            return;
        }
        do {
            food.add(Store.showFoodForSale(this));
            System.out.println("Buy another one? y/n");
            String input = scanner.next();
            if(input.equals("n")){
                return;
            }
        }while (money !=0);
    }

    public void feedAnimal(){
        while (true) {
            if (animals.size() == 0) {
                System.out.println("You dont have any animals to feed.");
                System.out.println("Do you want to buy one? y/n");
                input = scanner.next();
                if (input.equals("y")) {
                    buyAnimal();
                    return;
                } else {
                    return;
                }
            }
            if (food.size() == 0) {
                System.out.println("You dont have food left, press any key and enter to continue");
                scanner.next();
                return;
            }
            int co = 1;
            System.out.println("What animal do you want to feed?");
            for (Animal a : animals) {
                System.out.println(co + ": " + a.name);
                co++;
            }
            input = scanner.next();
            System.out.println("What do you want to feed " + animals.get(Integer.parseInt(input) - 1).name + " with?");
            co = 1;
            for (Food f : food) {
                System.out.println(co + ": " + f.name + " " + f.kg + "kg");
                co++;
            }
            input2 = scanner.next();
            if(food.get(Integer.parseInt(input2) - 1).kg != 0) {
                food.get(Integer.parseInt(input2) - 1).kg -= 1;
            }
            if(food.get(Integer.parseInt(input2) - 1).kg == 0){
                food.remove(Integer.parseInt(input2) - 1);
            }
            animals.get(Integer.parseInt(input) - 1).health += 10;
            System.out.println(animals.get(Integer.parseInt(input) - 1).name + "s current health is: "
                    + animals.get(Integer.parseInt(input) - 1).health);
            System.out.println("Feed another animal? y/n");
            input = scanner.next();
            if(input.equals("n")){
                return;
            }
        }
    }

    public void sellAnimal(){
        if(animals.size() <= 0){
            System.out.println("You dont have any animals to sell yet. Press any key following by enter to continue.");
            scanner.next();
            return;
        }
        while (true) {
            System.out.println("What animal do you want to sell?");
            System.out.println("Current balance: " + this.money);
            int co = 1;
            for (Animal a : animals) {
                System.out.println(co + ": " + a.name);
                co++;
            }
            input = scanner.next();
            int calc = animals.get(Integer.parseInt(input) - 1).health * animals.get(Integer.parseInt(input) - 1).price;
            this.money += calc / 100;
            animals.remove(Integer.parseInt(input) - 1);
            System.out.println("Do you want to sell another animal? y/n");
            input = scanner.next();
            if (input.equals("n")) {
                return;
            }
        }

    }

    public void mateAnimals(){

        String type = "";
        do {
            System.out.println("What animals do you want to mate? (It has to be the same sort)");
            System.out.println("Pick a male animal to begin with: ");
            int co = 1;
            while (true) {
                co = 1;
                for (Animal a : animals) {
                    System.out.println(co + ": " + a.getClass().getSimpleName() + ": " + a.name + " " + a.gender);
                    co++;
                }
                input = scanner.next();
                if (!animals.get(Integer.parseInt(input) - 1).gender.equals("male")) {
                    System.out.println("Not a female, pick a male to begin with");
                } else {
                    break;
                }
            }

            System.out.println("Now pick a female animal(Remember - of the same sort): ");
            while (true) {
                co = 1;
                for (Animal a : animals) {
                    System.out.println(co + ": " + a.getClass().getSimpleName() + ": " + a.name + " " + a.gender);
                    co++;
                }
                input2 = scanner.next();
                if (!animals.get(Integer.parseInt(input2) - 1).gender.equals("female")) {
                    System.out.println("Not a male, pick a female..");
                } else {
                    break;
                }
            }
            if(!animals.get(Integer.parseInt(input) -1).getClass().getSimpleName().equals(animals.get(Integer.parseInt(input2) - 1).getClass().getSimpleName())){
                System.out.println("Sorry, it has to be the same sort..");
            }else{
                type = animals.get(Integer.parseInt(input2) - 1).getClass().getSimpleName();
            }
        }while (!animals.get(Integer.parseInt(input) -1).getClass().getSimpleName().equals(animals.get(Integer.parseInt(input2) - 1).getClass().getSimpleName()));

        int chance = 1 + random.nextInt(2);

        if(chance == 1){
            chance = 1 + random.nextInt(2);
            if(chance == 1){
                System.out.println("Congratz it is a boy!");
                System.out.println("What name is the baby going to have?");
                input = scanner.next();
                if(type.equals("Dog")){animals.add(new Dog(input,"male"));}
                if(type.equals("Cow")){animals.add(new Cow(input,"male"));}
                if(type.equals("Cat")){animals.add(new Cat(input,"male"));}
                if(type.equals("Pig")){animals.add(new Pig(input,"male"));}
                if(type.equals("Horse")){animals.add(new Horse(input,"male"));}

            }
            else{
                System.out.println("Congratz it is a girl!");
                System.out.println("What name is the baby going to have?");
                input = scanner.next();
                if(type.equals("Dog")){animals.add(new Dog(input,"female"));}
                if(type.equals("Cow")){animals.add(new Cow(input,"female"));}
                if(type.equals("Cat")){animals.add(new Cat(input,"female"));}
                if(type.equals("Pig")){animals.add(new Pig(input,"female"));}
                if(type.equals("Horse")){animals.add(new Horse(input,"female"));}
            }
        }else{
            System.out.println("Sorry, it did not work this time..");
            System.out.println("Press any key following by enter to continue");
            scanner.next();
        }
    }

    public void minHealth(){
        for(int i = 0; i < animals.size();i++){
            int temp =  1 + random.nextInt(30);
            animals.get(i).health -= temp;
        }
    }

    public void checkLifeStatus(){
        for(int i = 0; i < animals.size();i++){
            if(animals.get(i).health <= 0){
                System.out.println(ANSI_RED + animals.get(i).name + " is dead.." + ANSI_RESET);
                animals.remove(animals.get(i));
            }
        }
    }

    public boolean checkMatePossibility(){
        int male = 0;
        int female = 0;
        int sort = 0;
        for(int i = 0; i < animals.size(); i++){
            if(animals.get(i).gender.equals("male")){male++;}
            if(animals.get(i).gender.equals("female")){female++;}
            for(int j = 0; j < animals.size(); j++){
                if(animals.get(i).hashCode() == animals.get(j).hashCode()){continue;}
                if(animals.get(i).getClass().getSimpleName().equals(animals.get(j).getClass().getSimpleName())){sort ++;}
            }
        }
        if(male > 0 && female > 0 && sort > 0){
            return true;
        }
        return false;
    }

    public boolean checkBalance(){
        if(this.money <= 0){
            return true;
        }
        return false;
    }

    public void sellAllMyAnimals(){
        for(int i = 0; i < animals.size(); i++){
            int calc = animals.get(i).health * animals.get(i).price;
            this.money += calc / 100;
        }
    }

}
