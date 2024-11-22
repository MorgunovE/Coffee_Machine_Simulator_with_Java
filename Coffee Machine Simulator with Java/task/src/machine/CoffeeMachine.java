package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private int coffeeCount;

    CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
        this.coffeeCount = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String action = scanner.next();

            switch (action) {
                case "buy":
                    buy(scanner);
                    break;
                case "fill":
                    fill(scanner);
                    break;
                case "take":
                    take();
                    break;
                case "clean":
                    clean();
                    break;
                case "remaining":
                    printState();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown action. Please try again.");
            }
        }
    }

    private void buy(Scanner scanner) {
        if (coffeeCount >= 10) {
            System.out.println("I need cleaning!");
            return;
        }

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.next();

        if (choice.equals("back")) {
            return;
        }

        Coffee coffee;
        switch (choice) {
            case "1":
                coffee = new Coffee(250, 0, 16, 4);
                break;
            case "2":
                coffee = new Coffee(350, 75, 20, 7);
                break;
            case "3":
                coffee = new Coffee(200, 100, 12, 6);
                break;
            default:
                System.out.println("Unknown coffee type. Please try again.");
                return;
        }

        if (water < coffee.water) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffeeBeans < coffee.coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            water -= coffee.water;
            milk -= coffee.milk;
            coffeeBeans -= coffee.coffeeBeans;
            cups -= 1;
            money += coffee.cost;
            coffeeCount++;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    private void fill(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        cups += scanner.nextInt();
    }

    private void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void clean() {
        coffeeCount = 0;
        System.out.println("I have been cleaned!");
    }

    private void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }
}
