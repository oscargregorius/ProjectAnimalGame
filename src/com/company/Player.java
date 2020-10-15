package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player implements Comparable<Player>{
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
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public Player(String name){
        this.name = name;
        this.money = 10000;
    }

    public void buyAnimal(){
        if(this.money < checkTheCheapestAnimal()){
            System.out.println("You dont have enough money. Press any key following by enter to continue");
            scanner.next();
            return;
        }
        while (money !=0 && this.money >= checkTheCheapestAnimal()){
            animals.add(Store.showAnimalsForSale(this));
            System.out.println("Buy another one? key/n");
            String input = scanner.next();
            if(input.equals("n")){
                return;
            }
        }
        System.out.println("You dont have enough money. Press any key following by enter to continue");
        scanner.next();
        return;

    }

    public void buyFood(){
        while (money !=0){
            Store.showFoodForSale(this);
            System.out.println("Buy another one? key/n");
            String input = scanner.next();
            if(input.equals("n")){
                return;
            }
        }
        if(checkBalance()){
            System.out.println("You dont have enough money. Press any key following by enter to continue");
            scanner.next();
            return;
        }
    }

    public void feedAnimal(Player p){
        while (true) {
            if(checkAnimalsToFeed()){
                System.out.println("You dont have the right/enough type of food to feed your animals.");
                System.out.println("Press any key following by enter to continue");
                scanner.next();
                return;
            }
            int co = 1;
            System.out.println("What animal do you want to feed?");
            for (Animal a : animals) {
                System.out.println(co + ": " + a.name);
                co++;
            }
            while (true) {
                input = scanner.next();
                try {
                    if (Integer.parseInt(input) > animals.size() || Integer.parseInt(input) < 1) {
                        System.out.println("Not a valid move.");
                    } else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Not a valid move.");
                }
            }
            System.out.println("What do you want to feed " + animals.get(Integer.parseInt(input) - 1).name + " with?");
            co = 1;
            for (Food f : food) {
                System.out.println(co + ": " + f.name + " " + f.kg + "kg");
                co++;
            }
            int kg;
            while (true) {
                try {
                    input2 = scanner.next();
                    if (Integer.parseInt(input2) > food.size() || Integer.parseInt(input2) < 1) {
                        System.out.println("Not a valid move.");
                    } else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Not a valid move.");
                }
            }
            System.out.println("How many KG do you want to feed " +
                    animals.get(Integer.parseInt(input) - 1).name + " with?");
            while(true) {
                try {
                    kg = Integer.parseInt(scanner.next());
                    if (kg > food.get(Integer.parseInt(input2) - 1).kg) {
                        System.out.println("You dont have that much..");
                    }
                    else if(kg <= 0){
                        System.out.println("You have to feed your animal with at least 1 KG");
                    }
                    else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Not a valid move.");
                }
            }
            if(animals.get(Integer.parseInt(input) - 1).eat(food.get(Integer.parseInt(input2)-1))) {
                if (food.get(Integer.parseInt(input2) - 1).kg != 0) {
                    food.get(Integer.parseInt(input2) - 1).kg -= kg;
                }
                if (food.get(Integer.parseInt(input2) - 1).kg == 0) {
                    food.remove(Integer.parseInt(input2) - 1);
                }
                animals.get(Integer.parseInt(input) - 1).health += kg * 10;
                if(animals.get(Integer.parseInt(input) - 1).health > 100){
                    animals.get(Integer.parseInt(input) - 1).health = 100;
                }
                System.out.println(animals.get(Integer.parseInt(input) - 1).name + "s current health is: "
                        + animals.get(Integer.parseInt(input) - 1).health);
                System.out.println("Feed another animal? key/n");
                input = scanner.next();
                if (input.equals("n")) {
                    return;
                }
            }else{
                System.out.println("I dont eat that..");
            }

        }
    }

    public void sellAnimal(){
        while (true) {
        if(animals.size() <= 0){
            System.out.println("You dont have any animals to sell yet. Press any key following by enter to continue.");
            scanner.next();
            return;
        }
            System.out.println("What animal do you want to sell?");
            System.out.println("Current balance: " + this.money);
            int co = 1;
            for (Animal a : animals) {
                System.out.println(co + ": " + a.name);
                co++;
            }
            while (true) {
                input = scanner.next();
                try {
                    if (Integer.parseInt(input) > animals.size() || Integer.parseInt(input) < 1) {
                        System.out.println("Not a valid move");
                    } else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Not a valid move");
                }
            }
            int calcAge = animals.get(Integer.parseInt(input) - 1).age * 100;
            int calc = animals.get(Integer.parseInt(input) - 1).health * animals.get(Integer.parseInt(input) - 1).price;
            this.money += calc / 100;
            this.money -= calcAge;
            animals.remove(Integer.parseInt(input) - 1);
            System.out.println("Your current balance: " + this.money);
            System.out.println("Do you want to sell another animal? key/n");
            input = scanner.next();
            if (input.equals("n")){
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
                while(true) {
                    input = scanner.next();
                    try {
                        if (Integer.parseInt(input) > animals.size() || Integer.parseInt(input) < 1) {
                            System.out.println("Not a valid move");
                        } else {
                            break;
                        }
                    }catch (Exception e){
                        System.out.println("Not a valid move");
                    }
                }
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
                while (true) {
                    input2 = scanner.next();
                    try {
                        if (Integer.parseInt(input2) > animals.size() || Integer.parseInt(input2) < 1) {
                            System.out.println("Not a valid move");
                        } else {
                            break;
                        }
                    }catch (Exception e){
                        System.out.println("Not a valid move");
                    }
                }
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

        int times = animals.get(Integer.parseInt(input2) - 1).possibleChildren;

        while (times > 0) {
            int chance = 1 + random.nextInt(2);
            if (chance == 1) {
                chance = 1 + random.nextInt(2);
                if (chance == 1) {
                    System.out.println("Congratz it is a boy!");
                    System.out.println("What name is the baby going to have?");
                    input = scanner.next();
                    if (type.equals("Dog")) {
                        animals.add(new Dog(input, "male"));
                    }
                    if (type.equals("Cow")) {
                        animals.add(new Cow(input, "male"));
                    }
                    if (type.equals("Cat")) {
                        animals.add(new Cat(input, "male"));
                    }
                    if (type.equals("Pig")) {
                        animals.add(new Pig(input, "male"));
                    }
                    if (type.equals("Horse")) {
                        animals.add(new Horse(input, "male"));
                    }

                } else {
                    System.out.println("Congratz it is a girl!");
                    System.out.println("What name is the baby going to have?");
                    input = scanner.next();
                    if (type.equals("Dog")) {
                        animals.add(new Dog(input, "female"));
                    }
                    if (type.equals("Cow")) {
                        animals.add(new Cow(input, "female"));
                    }
                    if (type.equals("Cat")) {
                        animals.add(new Cat(input, "female"));
                    }
                    if (type.equals("Pig")) {
                        animals.add(new Pig(input, "female"));
                    }
                    if (type.equals("Horse")) {
                        animals.add(new Horse(input, "female"));
                    }
                }
            }
            times--;
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

    public boolean checkAnimalsToFeed(){
        int check = 0;
        for(int i = 0; i < animals.size(); i++){
            for(int j = 0; j < food.size(); j++){
                if(animals.get(i).eat(food.get(j))){
                    check++;
                }
            }
        }
        if(check == 0){
            return true;
        }else{
            return false;
        }
    }

    public void sellAllMyAnimals(){
        for(int i = 0; i < animals.size(); i++){
            int calcAge = animals.get(i).age * 100;
            int calc = animals.get(i).health * animals.get(i).price;
            this.money += calc / 100;
            this.money -= calcAge;
        }
    }

    public void checkIfAnimalsAreSick(){
        if(animals.size() <= 0){return;}
        for(int i = 0; i < animals.size(); i++){
            int chance = 1+ random.nextInt(5);
            if(chance == 1){
                System.out.println(ANSI_RED + this.name.toUpperCase() + " YOUR ANIMAL " +
                        animals.get(i).name.toUpperCase() + " IS SICK" + ANSI_RESET);
                System.out.println("Your balance: " + this.money);
                System.out.println("Do you want to try to save " +  animals.get(i).name + "?");
                System.out.println("1: Yes (cost 1500)\n2: No (" + animals.get(i).name + " will die..)");
                input = scanner.next();
                if(input.equals("1")){
                    if(this.money < 1500){
                        System.out.println("Sorry you dont have enough money");
                        System.out.println(animals.get(i).name + " did not made it..");
                        animals.remove(animals.get(i));
                        System.out.println("Press any key following by enter to continue.");
                        scanner.next();
                        return;
                    }
                    this.money -= 1500;
                    chance = 1+ random.nextInt(2);
                    if(chance == 1){
                        System.out.println(animals.get(i).name + " is a lucky one, he made it!");
                        System.out.println("Press any key following by enter to continue.");
                        scanner.next();
                    }else{
                        System.out.println(animals.get(i).name + " did not made it..");
                        animals.remove(animals.get(i));
                        System.out.println("Press any key following by enter to continue.");
                        scanner.next();
                    }
                }else{
                    System.out.println("Rest in peace " + animals.get(i).name);
                    animals.remove(animals.get(i));
                    System.out.println("Press any key following by enter to continue.");
                    scanner.next();
                }
            }
        }
    }

    @Override
    public int compareTo(Player otherPlayer) {
        return otherPlayer.getMoney() - money;
    }

    public int getMoney(){
        return this.money;
    }

    public int checkTheCheapestAnimal(){
        int cheapest = Store.animals.get(0).price;
        for(int i = 0; i < Store.animals.size(); i++){
            if(Store.animals.get(i).price < cheapest) {
                cheapest = Store.animals.get(i).price;
            }
        }
        return cheapest;
    }

}
