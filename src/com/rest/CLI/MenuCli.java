
package com.rest.CLI;

import com.rest.models.MenuItem;
import com.rest.service.MenuService;
import java.util.Scanner;

public class MenuCli {

    private final MenuService service;
    private final Scanner scanner;

    public MenuCli(MenuService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== MENU MANAGEMENT =====");
            System.out.println("1. Add Food Item");
            System.out.println("2. View All Items");
            System.out.println("3. Search Item");
            System.out.println("4. Update Item");
            System.out.println("5. Delete Item");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1 -> addItem();
                    case 2 -> viewItems();
                    case 3 -> searchItem();
                    case 4 -> updateItem();
                    case 5 -> deleteItem();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private void addItem() {
        System.out.print("Food name: ");
        String name = scanner.nextLine();

        System.out.print("Category (Starter/Main Course/Dessert/Drink): ");
        String category = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Available? (true/false): ");
        boolean available = Boolean.parseBoolean(scanner.nextLine().trim());

        System.out.println(service.addItem(name, category, price, available));
    }

    private void viewItems() {
        for (MenuItem item : service.getAllItems()) {
            System.out.println(item);
        }
    }

    private void searchItem() {
        System.out.print("Enter item ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        MenuItem item = service.getItemById(id);

        System.out.println(item == null ? "Item not found." : item);
    }

    private void updateItem() {
        System.out.print("Enter item ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("New food name: ");
        String name = scanner.nextLine();

        System.out.print("New category: ");
        String category = scanner.nextLine();

        System.out.print("New price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Available? (true/false): ");
        boolean available = Boolean.parseBoolean(scanner.nextLine().trim());

        System.out.println(service.updateItem(
                id, name, category, price, available));
    }

    private void deleteItem() {
        System.out.print("Enter item ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.println(service.deleteItem(id));
    }
}
