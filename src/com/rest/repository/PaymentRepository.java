
package com.rest.repository;

import com.rest.models.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private final List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getAllPayments() {
        return payments;
    }

    public Payment getPaymentById(int paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == paymentId) {
                return payment;
            }
        }
        return null;
    }

    public boolean updatePaymentStatus(int paymentId, String status) {
        Payment payment = getPaymentById(paymentId);

        if (payment != null) {
            payment.setStatus(status);
            return true;
        }

        return false;
    }

    public boolean deletePayment(int paymentId) {
        Payment payment = getPaymentById(paymentId);

        if (payment != null) {
            payments.remove(payment);
            return true;
        }

        return false;
    }
}

