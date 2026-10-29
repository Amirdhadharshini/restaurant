
package com.rest.service;

public class CardPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Card payment selected.");
        System.out.println("Processing card payment...");
        System.out.println("Amount paid: Rs. " + amount);
    }
}
