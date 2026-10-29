
package com.rest.models;

public class OrderItem {

    private int menuItemId;
    private String itemName;
    private int quantity;
    private double price;

    public OrderItem(int menuItemId, String itemName,
                     int quantity, double price) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return itemName
                + " | Quantity: " + quantity
                + " | Price: Rs. " + String.format("%.2f", price)
                + " | Subtotal: Rs. " + String.format("%.2f", getSubtotal());
    }
}
