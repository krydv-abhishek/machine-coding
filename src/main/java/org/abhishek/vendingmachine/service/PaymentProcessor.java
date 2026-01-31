package org.abhishek.vendingmachine.service;

import org.abhishek.vendingmachine.model.Coin;

import java.util.EnumMap;
import java.util.Map;

public class PaymentProcessor {

    private final Map<Coin, Integer> machineCoins = new EnumMap<>(Coin.class);
    private final Map<Coin, Integer> insertedCoins = new EnumMap<>(Coin.class);
    private final Map<Coin, Integer> changeCoins = new EnumMap<>(Coin.class);

    public PaymentProcessor() {
        //initialize
        for (Coin coin : Coin.values()) {
            machineCoins.put(coin, 0);
        }
    }

    public void acceptCoin(Coin coin, Integer quantity) {
        insertedCoins.put(coin, insertedCoins.getOrDefault(coin, 0) + quantity);
    }

    public void refund(int amount) {
        System.out.println("Refunding amount " + amount);
    }

    public void returnChange(int change) {
        System.out.println("Returning change " + change);
    }

    public void begin() {
        insertedCoins.clear();
        changeCoins.clear();
    }

    public boolean canPrepareChange(int change) {
        int remaining = change;
        for (Coin coin : Coin.descending()) {
            int available = machineCoins.getOrDefault(coin, 0);
            int needed = Math.min(available, remaining / coin.getValue());
            remaining -= needed * coin.getValue();
        }
        System.out.println("No change available for amount: " + change);
        return remaining == 0;
    }

    public void prepareChange(int change) {
        int remaining = change;
        for (Coin coin : Coin.descending()) {
            int available = machineCoins.getOrDefault(coin, 0);
            int used = Math.min(available, remaining / coin.getValue());
            if (used > 0) {
                changeCoins.put(coin, changeCoins.getOrDefault(coin, 0) + used);
                remaining -= used * coin.getValue();
            }
        }
    }

    public void commit() {
        //add inserted coins
        insertedCoins.forEach((coin, quantity) -> {
            machineCoins.put(coin, machineCoins.getOrDefault(coin, 0) + quantity);
        });

        //remove change coins
        changeCoins.forEach((coin, quantity) -> {
            machineCoins.put(coin, machineCoins.get(coin) - quantity);
        });
        rollback();
    }

    public void rollback() {
        insertedCoins.clear();
        changeCoins.clear();
    }

}
