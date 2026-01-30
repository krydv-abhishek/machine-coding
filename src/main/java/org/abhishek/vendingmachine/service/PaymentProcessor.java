package org.abhishek.vendingmachine.service;

import org.abhishek.vendingmachine.model.Coin;
import org.abhishek.vendingmachine.repository.CoinInventory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PaymentProcessor {

    private final CoinInventory coinInventory;

    public PaymentProcessor(CoinInventory coinInventory) {
        this.coinInventory = coinInventory;
    }

    public void acceptCoin(Coin coin, int quantity) {
        coinInventory.addCoin(coin, quantity);
    }

    public void refund(int amount) {
        System.out.println("Refunding amount " + amount);
    }

    public void returnChange(int change) {
        System.out.println("Returning change " + change);
    }

    public boolean canReturnChange(int change) {
        int remaining = change;
        List<Coin> coins = Arrays.stream(Coin.values())
                .sorted(Comparator.comparingInt(Coin::getValue).reversed())
                .toList();
        for (Coin coin : coins) {
            int count = coinInventory.getCoinCount(coin);
            while (count > 0 && remaining >= coin.getValue()) {
                remaining -= coin.getValue();
                count--;
            }
        }

        return remaining == 0;
    }


}
