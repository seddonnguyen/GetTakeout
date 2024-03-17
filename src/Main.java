import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = input.nextLine();

        int money = 0;
        while (true) {
            try {
                System.out.print("Enter your money: ");
                money = input.nextInt();
                input.nextLine();
                break;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Money needs to be an `int` type.");
            }
        }

        Customer customer = new Customer(name, money);
        TakeOutSimulator simulator = new TakeOutSimulator(customer);
        simulator.takeOutPrompt();
        System.out.println("Goodbye!");
        input.close();
    }
}
