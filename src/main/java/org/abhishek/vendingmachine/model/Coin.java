package org.abhishek.vendingmachine.model;

public enum Coin {
    ONE(1),
    TWO(2),
    FIVE(5);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
