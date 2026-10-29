
package com.rest.repository;

import com.rest.models.MenuItem;
import java.util.ArrayList;

public class MenuRepository {

    private final ArrayList<MenuItem> items = new ArrayList<>();

    public boolean add(MenuItem item) {
        for (MenuItem existing : items) {
            if (existing.getName().equalsIgnoreCase(item.getName())) {
                return false;
            }
        }

        items.add(item);
        return true;
    }

    public ArrayList<MenuItem> getAll() {
        return new ArrayList<>(items);
    }

    public MenuItem findById(int id) {
        for (MenuItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean update(int id, String name, String category,
                         double price, boolean available) {
        MenuItem item = findById(id);

        if (item == null) {
            return false;
        }

        for (MenuItem existing : items) {
            if (existing.getName().equalsIgnoreCase(name)
                    && existing.getId() != id) {
                return false;
            }
        }

        item.setName(name);
        item.setCategory(category);
        item.setPrice(price);
        item.setAvailable(available);

        return true;
    }

    public boolean delete(int id) {
        MenuItem item = findById(id);

        if (item == null) {
            return false;
        }

        items.remove(item);
        return true;
    }
}
