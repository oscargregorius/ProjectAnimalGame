package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    ArrayList<Player> players = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    boolean check = false;
    int rounds = 0;
    int amountOfPlayers = 0;
    String input = "";

    public Game(){
        try {
            while (true) {
                System.out.println("Welcome, how many rounds do u want to play? 5-30?");
                rounds = scanner.nextInt();

                if (rounds < 5 || rounds > 30) {
                    System.out.println("please enter a number between 5 - 30.");
                } else {
                    break;
                }
            }
            while (true) {
                System.out.println("How many players do you wanna play with 1-4?");
                amountOfPlayers = scanner.nextInt();
                if (amountOfPlayers < 1 || amountOfPlayers > 4) {
                    System.out.println("Please enter a number between 1-4 ");
                } else {
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Not a valid input");
            new Game();
        }
        for(int i = 0; i < amountOfPlayers; i++){
            System.out.println("Player " + (i + 1) + " enter your name");
            String name = scanner.next();
            players.add(new Player(name));
        }
        System.out.println("\n".repeat(50));
        Store.buyInAllTheAnimalsAndFood();
        start();
    }

    public void start(){
        for(int i = 0; i < rounds; i++) {
            for(int j = 0; j < players.size(); j++) {
                do {
                    check = false;
                    players.get(j).checkLifeStatus();
                    System.out.println("Round: " + (i + 1));
                    System.out.println("What do you want to do " + players.get(j).name + "?");
                    System.out.println("1: Buy a animal");
                    System.out.println("2: Buy food");
                    System.out.println("3: Feed animal");
                    System.out.println("4: Sell animals");
                    System.out.println("5: Mate animals");
                    showAnimals(players.get(j));
                    input = scanner.next();

                    if(Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5){
                        System.out.println("invalid move enter a key following by enter to continue.");
                        scanner.next();
                        check = true;
                    }

                    switch (input) {
                        case "1":
                            if(players.get(j).checkBalance()){check = true;}
                            players.get(j).buyAnimal();
                            break;
                        case "2":
                            if(players.get(j).checkBalance()){check = true;}
                            players.get(j).buyFood();
                            break;
                        case "3":
                            if(players.get(j).checkAnimalsToFeed()){check = true;}
                            players.get(j).feedAnimal(players.get(j));
                            break;
                        case "4":
                            if(players.get(j).animals.size() <= 0){check = true;}
                            players.get(j).sellAnimal();
                            break;
                        case "5":
                            if(!players.get(j).checkMatePossibility()){
                                System.out.println("You dont have the animals/gender required to mate");
                                System.out.println("Press a key following by enter to go back to the menu");
                                scanner.next();
                                check = true;
                            }
                            if(players.get(j).checkMatePossibility()){players.get(j).mateAnimals();}
                    }
                    System.out.println("\n".repeat(50));
                }while(check);
            }
            looseHealth();
        }
        checkTheWinner();
    }


    public void looseHealth(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).minHealth();
        }
    }
    public void showAnimals(Player p){
        System.out.println("-".repeat(30));
        System.out.println("Current balance: " + p.money);
        System.out.println("-".repeat(30));
        System.out.println(p.name + "s animals:");

        for(int i = 0; i < p.animals.size(); i++){
            System.out.println(p.animals.get(i).name + " " + p.animals.get(i).gender + " health status: " + p.animals.get(i).health);
        }
        System.out.println("-".repeat(30));
        System.out.println(p.name + "s food list:");

        for(int i = 0; i < p.food.size();i++){
            System.out.println(p.food.get(i).name + " " + p.food.get(i).kg + "kg");
        }
        System.out.println("-".repeat(30));
    }
    public void checkTheWinner(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).sellAllMyAnimals();
        }
        Player winner = null;
        for(int i = 0; i < players.size(); i++) {
            winner = players.get(0);
            if (players.get(i).money > winner.money) {
                winner = players.get(i);
            }
        }
        System.out.println("-".repeat(30));
        System.out.println("The winner is: " + winner.name);
        System.out.println("-".repeat(30));
    }

}
