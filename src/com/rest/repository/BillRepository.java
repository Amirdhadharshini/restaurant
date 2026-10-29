
package com.rest.repository;

import com.rest.models.Bill;
import java.util.ArrayList;

public class BillRepository {

    private final ArrayList<Bill> bills = new ArrayList<>();

    public void save(Bill bill) {
        bills.add(bill);
    }

    public ArrayList<Bill> findAll() {
        return new ArrayList<>(bills);
    }

    public Bill findById(int id) {
        for (Bill bill : bills) {
            if (bill.getBillId() == id) {
                return bill;
            }
        }
        return null;
    }
}
