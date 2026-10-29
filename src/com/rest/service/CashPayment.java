
package com.rest.service;

public class CashPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Cash payment selected.");
        System.out.println("Amount paid: Rs. " + amount);
    }
}
