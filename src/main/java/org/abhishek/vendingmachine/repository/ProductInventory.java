package org.abhishek.vendingmachine.repository;

import org.abhishek.vendingmachine.model.Product;
import org.abhishek.vendingmachine.model.Slot;

import java.util.concurrent.ConcurrentHashMap;

public class ProductInventory {

    private final ConcurrentHashMap<String, Slot> slotMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> stock = new ConcurrentHashMap<>();

    public void addSlot(Slot slot, int quantity) {
        slotMap.put(slot.getProduct().getId(), slot);
        stock.put(slot.getProduct().getId(), quantity);
    }

    public boolean isAvailable(String productId) {
        return stock.getOrDefault(productId, 0) > 0;
    }

    public void deduct(String productId) {
        stock.put(productId, stock.get(productId) -1);
    }

    public Product getProduct(String productId) {
        return slotMap.get(productId).getProduct();
    }
}


