
package com.rest.CLI;

import com.rest.models.Bill;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.service.BillService;
import com.rest.service.OrderService;

import java.util.Scanner;

public class BillCli {

    private final BillService billService;
    private final OrderService orderService;
    private final Scanner scanner;

    public BillCli(BillService billService,
                   OrderService orderService,
                   Scanner scanner) {
        this.billService = billService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== BILL MANAGEMENT =====");
            System.out.println("1. Generate Bill");
            System.out.println("2. View Bill");
            System.out.println("3. Mark Bill as Paid");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(
                        scanner.nextLine().trim());

                switch (choice) {
                    case 1 -> generateBill();
                    case 2 -> viewBill();
                    case 3 -> markPaid();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            } catch (IllegalArgumentException |
                     IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void generateBill() {
        System.out.print("Enter order ID: ");
        int orderId = Integer.parseInt(
                scanner.nextLine().trim());

        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        Bill bill = billService.generateBill(order);
        printBill(bill, order);
    }

    private void viewBill() {
        System.out.print("Enter bill ID: ");
        int billId = Integer.parseInt(
                scanner.nextLine().trim());

        Bill bill = billService.getBillById(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        Order order = orderService.getOrderById(
                bill.getOrderId());

        printBill(bill, order);
    }

    private void markPaid() {
        System.out.print("Enter bill ID: ");
        int billId = Integer.parseInt(
                scanner.nextLine().trim());

        billService.markAsPaid(billId);
        System.out.println("Payment marked as PAID.");
    }

    private void printBill(Bill bill, Order order) {
        System.out.println("\n========================================");
        System.out.println("           RESTAURANT BILL");
        System.out.println("========================================");
        System.out.println("Bill ID      : " + bill.getBillId());
        System.out.println("Order ID     : " + bill.getOrderId());
        System.out.println("Table Number : " + bill.getTableNumber());
        System.out.println("----------------------------------------");

        if (order != null) {
            for (OrderItem item : order.getItems()) {
                System.out.printf(
                        "%-20s x%-3d Rs. %8.2f%n",
                        item.getItemName(),
                        item.getQuantity(),
                        item.getSubtotal());
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("Subtotal     : Rs. %8.2f%n",
                bill.getSubtotal());
        System.out.printf("GST (5%%)     : Rs. %8.2f%n",
                bill.getGst());
        System.out.printf("TOTAL        : Rs. %8.2f%n",
                bill.getTotal());
        System.out.println("----------------------------------------");
        System.out.println("Payment      : " + bill.getPaymentStatus());
        System.out.println("========================================");
        System.out.println("          Thank you! Visit again.");
    }
}

