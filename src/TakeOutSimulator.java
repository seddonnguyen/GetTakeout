import java.util.Scanner;

public class TakeOutSimulator {
    private final Customer customer;
    private final FoodMenu menu;
    private final Scanner input;

    public TakeOutSimulator(Customer customer) {
        this.customer = customer;
        this.menu = new FoodMenu();
        this.input = new Scanner(System.in);
    }


    public boolean shouldSimulate() {
        String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program: ";
        int money = this.customer.getMoney();
        int price = this.menu.getLowestCostFood().getPrice();

        IntUserInputRetriever<Boolean> intUserInputRetriever = selection -> {
            if (selection != 0 && selection != 1) {
                throw new IllegalArgumentException();
            }
            return selection == 1 && money >= price;
        };
        boolean output = this.getOutputOnIntInput(userPrompt, intUserInputRetriever);

        if (!output) {
            System.out.println("You don't have enough money to continue shopping :( - ending simulation...");
        }
        return output;
    }

    public Food getMenuSelection() {
        String userPrompt = "Today's Menu Options!\n" + this.menu + "Choose a menu item: ";
        IntUserInputRetriever<Food> intUserInputRetriever = selection -> {
            Food item = this.menu.getFood(selection);
            if (item == null) {
                throw new IllegalArgumentException();
            }
            return item;
        };
        return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public boolean isStillOrderingFood() {
        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT: ";
        IntUserInputRetriever<Boolean> intUserInputRetriever = selection -> {
            if (selection != 0 && selection != 1) {
                throw new IllegalArgumentException();
            }
            return selection == 1;
        };
        return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("Processing payment...");
        System.out.printf("Total Cost: $%d%n", shoppingBag.getTotalPrice());
        this.customer.setMoney(this.customer.getMoney() - shoppingBag.getTotalPrice());
        System.out.printf("Remaining Money: $%d%n", this.customer.getMoney());
        System.out.println("Thank you for shopping with us!");
    }

    public void takeOutPrompt() {
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoney = this.customer.getMoney();
        while (true) {
            System.out.printf("You have $%d left to spend.%n", customerMoney);
            Food item = this.getMenuSelection();

            if (item.getPrice() > customerMoney) {
                System.out.println("Oops! Looks like you don't have enough for that. Choose another item or checkout.");
            } else {
                System.out.printf("You have chosen: %s%n", item);
                shoppingBag.addItem(item);
                customerMoney -= item.getPrice();
                System.out.printf("You have $%d money left.%n", customerMoney);
            }

            if (!this.isStillOrderingFood()) {
                this.checkoutCustomer(shoppingBag);
                break;
            }
        }
        this.input.close();
    }

    public void startTakeOutSimulator() {
        while (this.shouldSimulate()) {
            System.out.println("Hello, welcome to the to my restaurant!");
            System.out.printf("Welcome %s!%n", this.customer.getName());
            this.takeOutPrompt();
        }
    }


    private <T> T getOutputOnIntInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever) {
        while (true) {
            int number = 0;
            try {
                System.out.print(userInputPrompt);
                number = Integer.parseInt(this.input.nextLine());
                return intUserInputRetriever.produceOutputOnIntUserInput(number);
            } catch (NumberFormatException e) {
                System.out.println("Inputs needs to be an `int` type.");
            } catch (IllegalArgumentException e) {
                System.out.printf("%d is not a valid input. Try Again!%n", number);
            }
        }
    }
}