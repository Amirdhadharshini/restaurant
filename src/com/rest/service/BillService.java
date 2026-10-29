
package com.rest.service;

import com.rest.models.Bill;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.repository.BillRepository;

public class BillService {

    private final BillRepository repository;
    private int nextBillId = 1;

    private static final double GST_RATE = 0.05;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public Bill generateBill(Order order) {

        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot generate a bill for an empty order.");
        }

        double subtotal = 0;

        for (OrderItem item : order.getItems()) {
            subtotal += item.getSubtotal();
        }

        double gst = subtotal * GST_RATE;
        double total = subtotal + gst;

        Bill bill = new Bill(
                nextBillId++,
                order.getOrderId(),
                order.getTableNumber(),
                subtotal,
                gst,
                total
        );

        repository.save(bill);

        return bill;
    }

    public Bill getBillById(int id) {
        return repository.findById(id);
    }

    public void markAsPaid(int id) {
        Bill bill = repository.findById(id);

        if (bill == null) {
            throw new IllegalArgumentException("Bill not found.");
        }

        if (bill.getPaymentStatus().equals("PAID")) {
            throw new IllegalStateException("Bill is already paid.");
        }

        bill.setPaymentStatus("PAID");
    }
}
