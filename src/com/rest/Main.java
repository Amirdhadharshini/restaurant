
package com.rest;

import com.rest.CLI.MenuCli;
import com.rest.CLI.OrderCli;
import com.rest.CLI.BillCli;
import com.rest.CLI.PaymentCli;
import com.rest.CLI.TableCli;

import com.rest.repository.MenuRepository;
import com.rest.repository.OrderRepository;
import com.rest.repository.BillRepository;
import com.rest.repository.TableRepository;

import com.rest.service.MenuService;
import com.rest.service.OrderService;
import com.rest.service.BillService;
import com.rest.service.TableService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MenuRepository menuRepository = new MenuRepository();
        OrderRepository orderRepository = new OrderRepository();
        BillRepository billRepository = new BillRepository();
        TableRepository tableRepository = new TableRepository();

        MenuService menuService =
                new MenuService(menuRepository);

        OrderService orderService =
                new OrderService(orderRepository, menuService);

        BillService billService =
                new BillService(billRepository);

        TableService tableService =
                new TableService(tableRepository);

        MenuCli menuCli =
                new MenuCli(menuService, scanner);

        OrderCli orderCli =
                new OrderCli(menuService, orderService, scanner);

        BillCli billCli =
                new BillCli(billService, orderService, scanner);

        PaymentCli paymentCli = new PaymentCli();

        TableCli tableCli = new TableCli(tableService);

        while (true) {

            System.out.println("\n===== RESTAURANT MANAGEMENT SYSTEM =====");
            System.out.println("1. Menu Management");
            System.out.println("2. Order Management");
            System.out.println("3. Bill Management");
            System.out.println("4. Payment Management");
            System.out.println("5. Table Management");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    menuCli.start();
                    break;

                case 2:
                    orderCli.start();
                    break;

                case 3:
                    billCli.start();
                    break;

                case 4:
                    paymentCli.start();
                    break;

                case 5:
                    tableCli.start();
                    break;

                case 0:
                    System.out.println("Thank you for using the Restaurant Management System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
