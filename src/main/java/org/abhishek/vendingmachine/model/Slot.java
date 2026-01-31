package org.abhishek.vendingmachine.model;

import java.util.UUID;

public class Slot {

    private final String id;
    private final String code;
    private Product product;

    public Slot(String code) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
