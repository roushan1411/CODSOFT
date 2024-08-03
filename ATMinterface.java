/*###### ATM INTERFACE######
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);  // Example initial balance
        ATM atm = new ATM(account);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            atm.displayMenu();
            System.out.print("Select an option (1-4): ");
            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose between 1 and 4.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();  // Clear the invalid input
                choice = 0;  // Set to an invalid choice to prompt the menu again
            }
        } while (choice != 4);

        scanner.close();
    }

    // Nested class for BankAccount
    private static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0) {
                if (amount <= balance) {
                    balance -= amount;
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Withdrawal amount must be positive.");
            }
        }
    }

    // Nested class for ATM
    private static class ATM {
        private BankAccount account;

        public ATM(BankAccount account) {
            this.account = account;
        }

        public void displayMenu() {
            System.out.println("Welcome to the ATM");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
        }

        public void checkBalance() {
            double balance = account.getBalance();
            System.out.printf("Your current balance is: $%.2f%n", balance);
        }

        public void deposit(double amount) {
            account.deposit(amount);
            System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, account.getBalance());
        }

        public void withdraw(double amount) {
            account.withdraw(amount);
            System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, account.getBalance());
        }
    }
}
