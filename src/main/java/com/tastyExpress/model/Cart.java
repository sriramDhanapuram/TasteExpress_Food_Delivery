package com.tastyExpress.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    // Add or update quantity
    public void addItem(CartItem item) {
        int itemId = item.getItemId();

        if (items.containsKey(itemId)) {
            CartItem existing = items.get(itemId);
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
        } else {
            items.put(itemId, item);
        }
    }

    // Update quantity directly
    public void updateItem(int itemId, int quantity) {
        if (!items.containsKey(itemId)) return;

        if (quantity <= 0) {
            items.remove(itemId);
        } else {
            items.get(itemId).setQuantity(quantity);
        }
    }

    // Remove item completely
    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    // Calculate total price
    public int getTotalPrice() {
        int total = 0;
        for (CartItem item : items.values()) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    // Calculate total quantity
    public int getTotalQuantity() {
        int totalQty = 0;
        for (CartItem item : items.values()) {
            totalQty += item.getQuantity();
        }
        return totalQty;
    }

    // Clear cart completely
    public void clear() {
        items.clear();
    }
}
