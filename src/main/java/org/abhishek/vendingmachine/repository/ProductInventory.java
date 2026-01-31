package org.abhishek.vendingmachine.repository;

import org.abhishek.vendingmachine.model.Product;
import org.abhishek.vendingmachine.model.Slot;

import java.util.HashMap;
import java.util.Map;

public class ProductInventory {

    private final Map<String, Slot> slotMap = new HashMap<>();
    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, Integer> reserve = new HashMap<>();


    public void addSlot(Slot slot, int quantity) {
        slotMap.put(slot.getProduct().getId(), slot);
        stock.put(slot.getProduct().getId(), quantity);
    }

    public boolean isAvailable(String productId) {
        return stock.getOrDefault(productId, 0) > 0;
    }

    public Product getProduct(String productId) {
        return slotMap.get(productId).getProduct();
    }

    public void begin() {
        reserve.clear();
    }

    public boolean canReserve(String productId, int quantity) {
        int available = stock.getOrDefault(productId, 0) -
                reserve.getOrDefault(productId, 0);
        return available>=quantity;
    }

    public void reserve(String productId, int quantity) {
        reserve.put(productId, reserve.getOrDefault(productId, 0) + quantity);
    }

    public void commit() {
        for(var entry : reserve.entrySet()) {
            stock.put(entry.getKey(), stock.get(entry.getKey()) - entry.getValue());
        }
        rollback();
    }

    public void rollback() {
        reserve.clear();
    }
}


