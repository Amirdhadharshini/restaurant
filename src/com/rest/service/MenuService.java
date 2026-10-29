
package com.rest.service;

import com.rest.models.MenuItem;
import com.rest.repository.MenuRepository;
import java.util.ArrayList;

public class MenuService {

    private final MenuRepository repository;
    private int nextId = 1;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public String addItem(String name, String category,
                          double price, boolean available) {

        if (name == null || name.isBlank()
                || category == null || category.isBlank()) {
            return "Name and category cannot be empty.";
        }

        if (!Double.isFinite(price) || price <= 0) {
            return "Price must be a positive number.";
        }

        MenuItem item = new MenuItem(
                nextId, name.trim(), category.trim(), price, available);

        if (!repository.add(item)) {
            return "An item with this name already exists.";
        }

        nextId++;
        return "Menu item added successfully. ID: " + item.getId();
    }

    public ArrayList<MenuItem> getAllItems() {
        return repository.getAll();
    }

    public ArrayList<MenuItem> getAvailableItems() {
        ArrayList<MenuItem> result = new ArrayList<>();

        for (MenuItem item : repository.getAll()) {
            if (item.isAvailable()) {
                result.add(item);
            }
        }

        return result;
    }

    public MenuItem getItemById(int id) {
        return repository.findById(id);
    }

    public String updateItem(int id, String name, String category,
                             double price, boolean available) {

        if (name == null || name.isBlank()
                || category == null || category.isBlank()) {
            return "Name and category cannot be empty.";
        }

        if (!Double.isFinite(price) || price <= 0) {
            return "Price must be a positive number.";
        }

        boolean updated = repository.update(
                id, name.trim(), category.trim(), price, available);

        return updated
                ? "Menu item updated successfully."
                : "Item not found or item name already exists.";
    }

    public String deleteItem(int id) {
        return repository.delete(id)
                ? "Menu item deleted successfully."
                : "Menu item not found.";
    }
}
