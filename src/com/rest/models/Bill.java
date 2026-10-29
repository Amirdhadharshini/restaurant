
package com.rest.models;

public class Bill {

    private int billId;
    private int orderId;
    private int tableNumber;
    private double subtotal;
    private double gst;
    private double total;
    private String paymentStatus;

    public Bill(int billId, int orderId, int tableNumber,
                double subtotal, double gst, double total) {
        this.billId = billId;
        this.orderId = orderId;
        this.tableNumber = tableNumber;
        this.subtotal = subtotal;
        this.gst = gst;
        this.total = total;
        this.paymentStatus = "PENDING";
    }

    public int getBillId() {
        return billId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getGst() {
        return gst;
    }

    public double getTotal() {
        return total;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Bill ID: " + billId
                + " | Order ID: " + orderId
                + " | Table: " + tableNumber
                + " | Total: Rs. " + String.format("%.2f", total)
                + " | Payment: " + paymentStatus;
    }
}
