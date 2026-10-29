
package com.rest.service;

import com.rest.models.Table;
import com.rest.repository.TableRepository;
import java.util.ArrayList;

public class TableService {

    private final TableRepository repository;
    private int nextId = 1;

    public TableService(TableRepository repository) {
        this.repository = repository;
    }

    public String addTable(int tableNumber, int capacity, String status) {

        if (tableNumber <= 0 || capacity <= 0) {
            return "Table number and capacity must be positive.";
        }

        if (!isValidStatus(status)) {
            return "Invalid status. Use AVAILABLE, OCCUPIED, or RESERVED.";
        }

        Table table = new Table(
                nextId, tableNumber, capacity, status.toUpperCase());

        if (!repository.add(table)) {
            return "Table number already exists.";
        }

        nextId++;
        return "Table added successfully. ID: " + table.getId();
    }

    public ArrayList<Table> getAllTables() {
        return repository.getAll();
    }

    public Table getTableById(int id) {
        return repository.findById(id);
    }

    public String updateTable(int id, int tableNumber,
                              int capacity, String status) {

        if (tableNumber <= 0 || capacity <= 0) {
            return "Table number and capacity must be positive.";
        }

        if (!isValidStatus(status)) {
            return "Invalid status. Use AVAILABLE, OCCUPIED, or RESERVED.";
        }

        boolean updated = repository.update(
                id, tableNumber, capacity, status.toUpperCase());

        return updated
                ? "Table updated successfully."
                : "Table not found or table number already exists.";
    }

    public String deleteTable(int id) {
        return repository.delete(id)
                ? "Table deleted successfully."
                : "Table not found.";
    }

    public ArrayList<Table> getTablesByStatus(String status) {
        if (!isValidStatus(status)) {
            return new ArrayList<>();
        }

        return repository.findByStatus(status);
    }

    private boolean isValidStatus(String status) {
        return status != null &&
                (status.equalsIgnoreCase("AVAILABLE")
                || status.equalsIgnoreCase("OCCUPIED")
                || status.equalsIgnoreCase("RESERVED"));
    }
}
