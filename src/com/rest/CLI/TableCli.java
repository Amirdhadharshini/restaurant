
package com.rest.CLI;

import com.rest.models.Table;
import com.rest.service.TableService;

import java.util.ArrayList;
import java.util.Scanner;

public class TableCli {

    private final TableService service;
    private final Scanner scanner;

    public TableCli(TableService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        while (true) {
            System.out.println("\n===== RESTAURANT TABLE MANAGEMENT =====");
            System.out.println("1. Add Table");
            System.out.println("2. View All Tables");
            System.out.println("3. Search Table by ID");
            System.out.println("4. Update Table");
            System.out.println("5. Delete Table");
            System.out.println("6. View Tables by Status");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        addTable();
                        break;
                    case 2:
                        viewAllTables();
                        break;
                    case 3:
                        searchTable();
                        break;
                    case 4:
                        updateTable();
                        break;
                    case 5:
                        deleteTable();
                        break;
                    case 6:
                        viewByStatus();
                        break;
                    case 0:
                        System.out.println("Thank you!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numeric values.");
            }
        }
    }

    private void addTable() {
        System.out.print("Enter table number: ");
        int number = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter seating capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter status (AVAILABLE/OCCUPIED/RESERVED): ");
        String status = scanner.nextLine().trim();

        System.out.println(
                service.addTable(number, capacity, status));
    }

    private void viewAllTables() {
        ArrayList<Table> tables = service.getAllTables();

        if (tables.isEmpty()) {
            System.out.println("No tables found.");
            return;
        }

        for (Table table : tables) {
            System.out.println(table);
        }
    }

    private void searchTable() {
        System.out.print("Enter table ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Table table = service.getTableById(id);

        if (table == null) {
            System.out.println("Table not found.");
        } else {
            System.out.println(table);
        }
    }

    private void updateTable() {
        System.out.print("Enter table ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter new table number: ");
        int number = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter new seating capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter new status: ");
        String status = scanner.nextLine().trim();

        System.out.println(
                service.updateTable(id, number, capacity, status));
    }

    private void deleteTable() {
        System.out.print("Enter table ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.println(service.deleteTable(id));
    }

    private void viewByStatus() {
        System.out.print("Enter status: ");
        String status = scanner.nextLine().trim();

        ArrayList<Table> tables = service.getTablesByStatus(status);

        if (tables.isEmpty()) {
            System.out.println("No tables found for this status.");
            return;
        }

        for (Table table : tables) {
            System.out.println(table);
        }
    }
}
