
package com.rest.CLI;

import com.rest.models.MenuItem;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.service.MenuService;
import com.rest.service.OrderService;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderCli{

    private final MenuService menuService;
    private final OrderService orderService;
    private final Scanner scanner;

    public OrderCli(MenuService menuService,
                    OrderService orderService,
                    Scanner scanner) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== ORDER MANAGEMENT =====");
            System.out.println("1. Place New Order");
            System.out.println("2. View All Orders");
            System.out.println("3. Search Order");
            System.out.println("4. Update Order Status");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1 -> placeOrder();
                    case 2 -> viewOrders();
                    case 3 -> searchOrder();
                    case 4 -> updateStatus();
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

    private void placeOrder() {
        System.out.print("Enter table number: ");
        int tableNumber = Integer.parseInt(scanner.nextLine().trim());

        Order order = orderService.createOrder(tableNumber);

        if (order == null) {
            System.out.println("Invalid table number.");
            return;
        }

        ArrayList<MenuItem> availableItems =
                menuService.getAvailableItems();

        if (availableItems.isEmpty()) {
            System.out.println("No food items are available.");
            return;
        }

        System.out.println("\n===== AVAILABLE MENU =====");

        for (MenuItem item : availableItems) {
            System.out.println(item);
        }

        while (true) {
            System.out.print("\nEnter food item ID (0 to finish): ");
            int itemId = Integer.parseInt(scanner.nextLine().trim());

            if (itemId == 0) {
                break;
            }

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            System.out.println(
                    orderService.addItemToOrder(order, itemId, quantity));
        }

        if (order.getItems().isEmpty()) {
            System.out.println("No items selected. Order not placed.");
            return;
        }

        System.out.println(orderService.saveOrder(order));
        printBill(order);
    }

    private void printBill(Order order) {
        System.out.println("\n========== RESTAURANT BILL ==========");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Table Number: " + order.getTableNumber());
        System.out.println("-------------------------------------");

        for (OrderItem item : order.getItems()) {
            System.out.println(item);
        }

        System.out.println("-------------------------------------");
        System.out.printf("TOTAL: Rs. %.2f%n", order.getTotal());
        System.out.println("Status: " + order.getStatus());
        System.out.println("=====================================");
    }

    private void viewOrders() {
        ArrayList<Order> orders = orderService.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private void searchOrder() {
        System.out.print("Enter order ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Order order = orderService.getOrderById(id);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        printBill(order);
    }

    private void updateStatus() {
        System.out.print("Enter order ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter status (PREPARING/READY/SERVED/CANCELLED): ");
        String status = scanner.nextLine().trim();

        System.out.println(
                orderService.updateOrderStatus(id, status));
    }
}
