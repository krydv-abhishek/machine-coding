package org.abhishek.vendingmachine.repository;

import org.abhishek.vendingmachine.model.Coin;

import java.util.concurrent.ConcurrentHashMap;

public class CoinInventory {

    private final ConcurrentHashMap<Coin, Integer> coinCount = new ConcurrentHashMap<>();

    public CoinInventory() {
        //initialize
        for(Coin coin: Coin.values()) {
            coinCount.put(coin, 10);
        }
    }

    public void addCoin(Coin coin, Integer quantity) {
        coinCount.put(coin, coinCount.get(coin) + quantity);
    }

    public int getCoinCount(Coin coin) {
        return  coinCount.get(coin);
    }

}
