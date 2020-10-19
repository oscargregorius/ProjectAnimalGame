package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {

    ArrayList<Player> players = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    boolean check = false;
    int rounds = 0;
    int amountOfPlayers = 0;
    String input = "";

    public Game(){
            while (true) {
                System.out.println(Player.ANSI_YELLOW + "Welcome, how many rounds do u want to play? 5-30?" + Player.ANSI_RESET);
                try {
                    rounds = scanner.nextInt();
                    if (rounds < 5 || rounds > 30) {
                        System.out.println("please enter a number between 5 - 30.");
                    } else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Not a valid move");
                    scanner.next();
                }
            }
            while (true) {
                System.out.println(Player.ANSI_YELLOW + "How many players do you wanna play with 1-4?" + Player.ANSI_RESET);
                try {
                    amountOfPlayers = scanner.nextInt();
                    if (amountOfPlayers < 1 || amountOfPlayers > 4) {
                        System.out.println("Please enter a number between 1-4 ");
                    } else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("Not a valid move");
                    scanner.next();
                }
            }

        for(int i = 0; i < amountOfPlayers; i++){
            System.out.println(Player.ANSI_YELLOW + "Player " + (i + 1) + " enter your name" + Player.ANSI_RESET);
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
                if(i == (rounds - 1)){
                    System.out.println(Player.ANSI_RED + "THIS IS THE LAST ROUND!!" + Player.ANSI_RESET);
                }
                players.get(j).checkIfAnimalsAreSick();
                players.get(j).checkLifeStatus();
                if(checkPlayersStatus(players.get(j))){
                    continue;
                }
                do {
                    check = false;
                    System.out.println(Player.ANSI_PURPLE + "ROUND: " + (i + 1) + Player.ANSI_RESET);
                    System.out.println(Player.ANSI_YELLOW + "What do you want to do " + players.get(j).name + "?"
                            + Player.ANSI_RESET);
                    System.out.println("1: Buy a animal");
                    System.out.println("2: Buy food");
                    System.out.println("3: Feed animal");
                    System.out.println("4: Sell animals");
                    System.out.println("5: Mate animals");
                    System.out.println("6: Trade with players");
                    showAnimalsAndFood(players.get(j));
                    while(true) {
                        try {
                            input = scanner.next();
                            if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 6) {
                                System.out.println("invalid move.");
                            } else {
                                break;
                            }
                        }catch (Exception e){
                            System.out.println("Not a valid move");
                        }
                    }

                    switch (input) {
                        case "1":
                            if(players.get(j).checkBalance() ||
                                    players.get(j).money < players.get(j).checkTheCheapestAnimal()){check = true;}
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
                            break;
                        case "6":
                            if(!checkPlayersAnimal()){check = true;}
                            else{
                                if(tradeWithPlayers(players.get(j))){
                                    check = true;
                                }
                            }
                            break;
                    }
                    System.out.println("\n".repeat(50));
                }while(check);
            }
            increaseAge();
            looseHealth();
        }
        checkTheWinner();
    }


    public void looseHealth(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).minHealth();
        }
    }
    public void showAnimalsAndFood(Player p){
        System.out.println(Player.ANSI_YELLOW + "-".repeat(30) + Player.ANSI_RESET);
        System.out.println(Player.ANSI_YELLOW + "Current balance: " + p.money + Player.ANSI_RESET);
        System.out.println(Player.ANSI_YELLOW + "-".repeat(30) + Player.ANSI_RESET);
        System.out.println(Player.ANSI_YELLOW + p.name + "s animals:" + Player.ANSI_RESET);

        for(int i = 0; i < p.animals.size(); i++){
            System.out.println("[" + p.animals.get(i).getClass().getSimpleName() + "] " + p.animals.get(i).name + " | "
                    + p.animals.get(i).age + " years old | "
                    + p.animals.get(i).gender + " | health status:" + p.animals.get(i).health);
        }
        System.out.println(Player.ANSI_YELLOW + "-".repeat(30) + Player.ANSI_RESET);
        System.out.println(Player.ANSI_YELLOW + p.name + "s food list:" + Player.ANSI_RESET);

        for(int i = 0; i < p.food.size();i++){
            System.out.println(p.food.get(i).name + " " + p.food.get(i).kg + "KG");
        }
        System.out.println(Player.ANSI_YELLOW + "-".repeat(30) + Player.ANSI_RESET);
    }
    public void checkTheWinner(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).sellAllMyAnimals();
        }

        System.out.println(Player.ANSI_YELLOW + "-".repeat(30));
        System.out.println("SCOREBOARD:");
        Collections.sort(players);
        if(players.get(0).money == players.get(1).money){
            System.out.println("ITS A DRAW BETWEEN " + players.get(0).name.toUpperCase() + " & "
                    + players.get(1).name.toUpperCase());
        }else {
            System.out.println("THE WINNER IS: " + players.get(0).name.toUpperCase());
        }
        System.out.println("-".repeat(30));
        int co = 1;
        for(int i = 0; i<players.size(); i++){
            System.out.println(Player.ANSI_YELLOW + co + ": " + players.get(i).name + " " +
                    "" + players.get(i).money + " SEK" + Player.ANSI_RESET);
            co++;
        }
    }
    public void increaseAge(){

        for(int i = 0; i < players.size(); i++){
            for(int j = 0; j < players.get(i).animals.size(); j++){
                players.get(i).animals.get(j).age +=1;
                if(players.get(i).animals.get(j).age >= players.get(i).animals.get(j).maxAge){
                    System.out.println(Player.ANSI_RED + players.get(i).animals.get(j).name.toUpperCase() +
                            " IS NOW DEAD BY AGE.." + Player.ANSI_RESET);
                    players.get(i).animals.remove(players.get(i).animals.get(j));
                }
            }
        }
    }

    public boolean tradeWithPlayers(Player p){
        System.out.println("\n".repeat(50));
        System.out.println(Player.ANSI_YELLOW + "What do you want to do?");
        System.out.println("1: Buy animal from player\n2: Sell animal to player\n3:EXIT" + Player.ANSI_RESET);
        while (true) {
            try {
                input = scanner.next();
                if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 3) {
                    System.out.println("Not a valid move");
                } else {
                    break;
                }
            }catch (Exception e){
                System.out.println("not a valid move");
            }
        }
        if (input.equals("1")) {
            if(!checkOtherPlayersAnimal(p)){
                System.out.println("The other players does not have any animals yet." +
                        " Press any key following by enter to continue");
                scanner.next();
                tradeWithPlayers(p);
            }
            buyFromPlayer(p);
        }
        if (input.equals("2")) {
            if (p.animals.size() <= 0) {
                System.out.println("You dont have any animals. Press any key following by enter to continue");
                scanner.next();
                tradeWithPlayers(p);
            } else {
                sellAnimalToPlayer(p);
            }
        }
        if(input.equals("3")){
            return true;
        }
        return false;
    }

    public void buyFromPlayer(Player p){
        int size;
        int co = 1;
        System.out.println("Which player do you want to buy from?");
        for(int i = 0; i < players.size(); i++){
            if(players.get(players.indexOf(p)).equals(players.get(i))){
                System.out.println(co + ": THIS IS ME.");
                co++;
                continue;
            }
            System.out.println(co + ": " + players.get(i).name);
            co++;
        }
        co = 1;
        while (true) {
            try {
                input = scanner.next();
                if (players.get(Integer.parseInt(input) - 1).equals(p)) {
                    System.out.println("I cant trade with myself..");
                    continue;
                }
                if (players.get(Integer.parseInt(input) - 1).animals.size() <= 0) {
                    System.out.println(players.get(Integer.parseInt(input) - 1).name + " dont have any animals yet.");
                } else {
                    break;
                }
            }catch (Exception e){
                System.out.println("Invalid move");
            }
        }
        size = players.get(Integer.parseInt(input) - 1).animals.size();
        System.out.println("What animal do you want to buy from " + players.get(Integer.parseInt(input) - 1).name);
        for(int i = 0; i < players.get(Integer.parseInt(input) - 1).animals.size(); i++){
            System.out.println(co + ": " + players.get(Integer.parseInt(input) - 1).animals.get(i).name);
            co++;
        }
        String choice;
        while (true) {
            try {
                choice = scanner.next();
                if(Integer.parseInt(choice) <= 0){
                    System.out.println("Invalid move");
                }
                else if(Integer.parseInt(choice) > size){
                    System.out.println("Invalid move");
                }
                else {
                    break;
                }
            }catch (Exception e){
                System.out.println("Invalid move");
            }
        }
        System.out.println("Your balance: " + p.money);
        System.out.println("How much do you want to offer");
        String offer;
        while (true) {
            try {
                offer = scanner.next();
                if(Integer.parseInt(offer) <= 0){
                    System.out.println("You have to at least offer 1SEK");
                }
                else if (p.money < Integer.parseInt(offer)) {
                    System.out.println("You dont have that much money");
                }
                else {
                    break;
                }
            }catch (Exception e){
                System.out.println("Invalid move");
            }
        }
        System.out.println(players.get(Integer.parseInt(input) - 1).name + " " + players.get(players.indexOf(p)).name +
                " wants to buy " + players.get(Integer.parseInt(input) - 1).animals.get(Integer.parseInt(choice) - 1).name +
                " for " + Integer.parseInt(offer) + "SEK do you want to accept the offer?");
        System.out.println("1: Yes\n2: No");
        String option;
        while (true) {
            try {
                option = scanner.next();
                if (Integer.parseInt(option) < 1 || Integer.parseInt(option) > 2) {
                    System.out.println("invalid move");
                } else {
                    break;
                }
            }catch (Exception e){
                System.out.println("Not a valid move");
            }
        }
        if(option.equals("1")){
            players.get(players.indexOf(p)).money -= Integer.parseInt(offer);
            players.get(Integer.parseInt(input) - 1).money += Integer.parseInt(offer);
            players.get(players.indexOf(p)).animals.add
                    (players.get(Integer.parseInt(input) - 1).animals.get(Integer.parseInt(choice) - 1));

            players.get(Integer.parseInt(input) - 1).animals.remove
                    (players.get(Integer.parseInt(input) - 1).animals.get(Integer.parseInt(choice) - 1));
            return;
        }
        if(option.equals("2")){
            return;
        }
    }

    public void sellAnimalToPlayer(Player p){
        System.out.println("What animal do you want to sell?");
        int co = 1;
        for(Animal a : p.animals){
            System.out.println(co + ": " + a.name);
            co++;
        }
        while (true) {
            try {
                input = scanner.next();
                if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > p.animals.size()) {
                    System.out.println("Not a valid move");
                } else {
                    break;
                }
            }catch (Exception e){
                System.out.println("Not a valid move");
            }
        }
        System.out.println("Your balance: " + p.money);
        System.out.println("How much do you want to sell " + p.animals.get(Integer.parseInt(input)-1).name + " for?");
        String offer;
        String choice;
        while(true) {
            while (true) {
                try {
                    offer = scanner.next();
                    if (Integer.parseInt(offer) <= 0) {
                        System.out.println("You have to offer more than 0 SEK");
                    } else {
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("Invalid move");
                }
            }
            System.out.println("Who do you want to sell it to?");
            co = 1;
            for (Player pr : players) {
                if (pr.name.equals(p.name)) {
                    System.out.println(co + ": THIS IS ME");
                    co++;
                    continue;
                }
                System.out.println(co + ": " + pr.name);
                co++;
            }
            while (true) {
                try {
                    choice = scanner.next();
                    if (players.get(Integer.parseInt(choice) - 1).equals(p)) {
                        System.out.println("I cant trade with myself");
                        continue;
                    }
                    if (Integer.parseInt(choice) <= 0 || Integer.parseInt(choice) > players.size()) {
                        System.out.println("Not a valid move");
                        continue;
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Not a valid move");
                }
            }
            if(Integer.parseInt(offer) > players.get(Integer.parseInt(choice) - 1).money){
                System.out.println("The player you want to trade with does not have that much money.");
                System.out.println("The players balance is: " + players.get(Integer.parseInt(choice) - 1).money);
                System.out.println("Enter a new amount");
            }else{
                break;
            }
        }
        System.out.println(players.get(Integer.parseInt(choice)-1).name + " do you want to buy " + p.name + "s animal "
                + p.animals.get(Integer.parseInt(input)-1).name + " for " + offer + "SEK?");
        System.out.println("1: Yes\n2: No");
        String option;
        while(true) {
            try {
                option = scanner.next();
                if (Integer.parseInt(option) < 1 || Integer.parseInt(option) > 2) {
                    System.out.println("Invalid move");
                } else {
                    break;
                }
            }catch (Exception e){
                System.out.println("Invalid move");
            }
        }
        if(option.equals("1")){
            players.get(Integer.parseInt(choice)-1).animals.add(p.animals.get(Integer.parseInt(input)-1));
            p.animals.remove(Integer.parseInt(input)-1);
            p.money += Integer.parseInt(offer);
            players.get(Integer.parseInt(choice)-1).money -= Integer.parseInt(offer);
            return;
        }else{
            return;
        }
    }

    public boolean checkPlayersAnimal(){
        int amount = 0;
        for(Player p : players){
            if(p.animals.size() <= 0){
                amount++;
            }
        }
        if(amount == players.size()){
            System.out.println("The other players does not have any animals yet. " +
                    "Press any key following by enter to continue");
            scanner.next();
            return false;
        }
        if(players.size() == 1){
            System.out.println("You cant trade with yourself. Press any key following by enter to continue");
            scanner.next();
            return false;
        }
        return true;
    }

    public boolean checkOtherPlayersAnimal(Player p){
        int co = 0;
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).equals(p)){
                continue;
            }else{
                co++;
            }
        }
        if(co == players.size() - 1){
            return true;
        }
            return false;

    }

    public boolean checkPlayersStatus(Player p){
        if(p.animals.size() == 0 && p.money <= 0){
            if(!p.dead) {
                System.out.println(Player.ANSI_RED + "GAME OVER " + p.name.toUpperCase() + Player.ANSI_RESET);
                System.out.println("Press any key following by enter to continue");
                scanner.next();
            }
            p.dead = true;
            return true;
        }
        return false;
    }

}
