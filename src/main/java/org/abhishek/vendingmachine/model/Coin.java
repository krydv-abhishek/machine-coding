package org.abhishek.vendingmachine.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    public static List<Coin> descending() {
        return Arrays.stream(Coin.values())
                .sorted(Comparator.comparingInt(Coin::getValue).reversed())
                .toList();
    }

}
