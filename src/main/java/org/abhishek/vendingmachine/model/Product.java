package org.abhishek.vendingmachine.model;

import java.util.UUID;

public class Product {

    public String getId() {
        return id;
    }

    private final String id;
    private final String name;
    private final int price;

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
