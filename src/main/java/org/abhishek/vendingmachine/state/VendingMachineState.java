package org.abhishek.vendingmachine.state;

import org.abhishek.vendingmachine.model.Coin;

public interface VendingMachineState {

    void selectProduct(String productId);
    void insertCoin(Coin coin);
    void cancel();


}
