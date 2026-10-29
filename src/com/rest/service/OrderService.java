
package com.rest.service;

import com.rest.models.MenuItem;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.repository.OrderRepository;

import java.util.ArrayList;

public class OrderService {

    private final OrderRepository repository;
    private final MenuService menuService;
    private int nextOrderId = 1;

    public OrderService(OrderRepository repository,
                        MenuService menuService) {
        this.repository = repository;
        this.menuService = menuService;
    }

    public Order createOrder(int tableNumber) {
        if (tableNumber <= 0) {
            return null;
        }

        Order order = new Order(nextOrderId, tableNumber);
        nextOrderId++;

        return order;
    }

    public String addItemToOrder(Order order,
                                 int menuItemId, int quantity) {

        if (order == null || !"PLACED".equals(order.getStatus())) {
            return "Invalid or closed order.";
        }

        if (quantity <= 0) {
            return "Quantity must be greater than zero.";
        }

        MenuItem menuItem = menuService.getItemById(menuItemId);

        if (menuItem == null || !menuItem.isAvailable()) {
            return "Food item not found or unavailable.";
        }

        order.addItem(new OrderItem(
                menuItem.getId(),
                menuItem.getName(),
                quantity,
                menuItem.getPrice()
        ));

        return "Item added to order.";
    }

    public String saveOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            return "Cannot place an empty order.";
        }

        repository.add(order);
        return "Order placed successfully. Order ID: "
                + order.getOrderId();
    }

    public ArrayList<Order> getAllOrders() {
        return repository.getAll();
    }

    public Order getOrderById(int id) {
        return repository.findById(id);
    }

    public String updateOrderStatus(int id, String status) {
        if (status == null) {
            return "Invalid status.";
        }

        String normalized = status.trim().toUpperCase();

        if (!normalized.equals("PREPARING")
                && !normalized.equals("READY")
                && !normalized.equals("SERVED")
                && !normalized.equals("CANCELLED")) {
            return "Use PREPARING, READY, SERVED, or CANCELLED.";
        }

        Order order = repository.findById(id);

        if (order == null) {
            return "Order not found.";
        }

        if (!order.getStatus().equals("PLACED")) {
            return "This order can no longer be updated.";
        }

        repository.updateStatus(id, normalized);
        return "Order status updated successfully.";
    }
}

