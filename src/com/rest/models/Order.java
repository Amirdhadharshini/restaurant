
package com.rest.models;

import java.util.ArrayList;

public class Order {

    private int orderId;
    private int tableNumber;
    private ArrayList<OrderItem> items;
    private String status;

    public Order(int orderId, int tableNumber) {
        this.orderId = orderId;
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
        this.status = "PLACED";
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public ArrayList<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public String getStatus() {
        return status;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId
                + " | Table: " + tableNumber
                + " | Items: " + items.size()
                + " | Total: Rs. " + String.format("%.2f", getTotal())
                + " | Status: " + status;
    }
}
