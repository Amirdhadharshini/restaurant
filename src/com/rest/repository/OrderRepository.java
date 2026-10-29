
package com.rest.repository;

import com.rest.models.Order;
import java.util.ArrayList;

public class OrderRepository {

    private final ArrayList<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getAll() {
        return new ArrayList<>(orders);
    }

    public Order findById(int id) {
        for (Order order : orders) {
            if (order.getOrderId() == id) {
                return order;
            }
        }

        return null;
    }

    public boolean updateStatus(int id, String status) {
        Order order = findById(id);

        if (order == null) {
            return false;
        }

        order.setStatus(status);
        return true;
    }
}
