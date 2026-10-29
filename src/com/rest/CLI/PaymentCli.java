
package com.rest.CLI;

import com.rest.service.PaymentMethod;
import com.rest.service.CashPayment;
import com.rest.service.CardPayment;
import com.rest.service.UpiPayment;

import java.util.Scanner;

public class PaymentCli {

    private final Scanner scanner;

    public PaymentCli() {
        scanner = new Scanner(System.in);
    }

    public void start() {

        while (true) {
            System.out.println("\n===== PAYMENT MANAGEMENT =====");
            System.out.println("1. Cash Payment");
            System.out.println("2. Card Payment");
            System.out.println("3. UPI Payment");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (choice == 0) {
                return;
            }

            PaymentMethod payment;

            switch (choice) {
                case 1:
                    payment = new CashPayment();
                    break;

                case 2:
                    payment = new CardPayment();
                    break;

                case 3:
                    payment = new UpiPayment();
                    break;

                default:
                    System.out.println("Invalid payment choice.");
                    continue;
            }

            System.out.print("Enter payment amount: ");

            double amount;

            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
                continue;
            }

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                continue;
            }

            payment.pay(amount);
        }
    }
}
