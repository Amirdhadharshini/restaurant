
package com.rest.service;

public class UpiPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("UPI payment selected.");
        System.out.println("Processing UPI payment...");
        System.out.println("Amount paid: Rs. " + amount);
    }
}
