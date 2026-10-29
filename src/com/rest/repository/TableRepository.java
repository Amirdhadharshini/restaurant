
package com.rest.repository;

import com.rest.models.Table;
import java.util.ArrayList;

public class TableRepository {

    private final ArrayList<Table> tables = new ArrayList<>();

    public boolean add(Table table) {
        for (Table existing : tables) {
            if (existing.getTableNumber() == table.getTableNumber()) {
                return false;
            }
        }

        tables.add(table);
        return true;
    }

    public ArrayList<Table> getAll() {
        return new ArrayList<>(tables);
    }

    public Table findById(int id) {
        for (Table table : tables) {
            if (table.getId() == id) {
                return table;
            }
        }

        return null;
    }

    public boolean update(int id, int tableNumber,
                          int capacity, String status) {

        Table table = findById(id);

        if (table == null) {
            return false;
        }

        for (Table existing : tables) {
            if (existing.getTableNumber() == tableNumber
                    && existing.getId() != id) {
                return false;
            }
        }

        table.setTableNumber(tableNumber);
        table.setCapacity(capacity);
        table.setStatus(status);

        return true;
    }

    public boolean delete(int id) {
        Table table = findById(id);

        if (table == null) {
            return false;
        }

        tables.remove(table);
        return true;
    }

    public ArrayList<Table> findByStatus(String status) {
        ArrayList<Table> result = new ArrayList<>();

        for (Table table : tables) {
            if (table.getStatus().equalsIgnoreCase(status)) {
                result.add(table);
            }
        }

        return result;
    }
}
