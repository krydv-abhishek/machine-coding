package org.abhishek.vendingmachine.state.impl;

import org.abhishek.vendingmachine.model.Coin;
import org.abhishek.vendingmachine.service.VendingMachine;
import org.abhishek.vendingmachine.state.VendingMachineState;

public class IdleState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(String productId) {

        if(!vendingMachine.getProductInventory().isAvailable(productId)) {
            System.out.println("Product is not available");
        }
        vendingMachine.setSelectedProductId(productId);
        vendingMachine.setInsertedAmount(0);
        vendingMachine.setState(new ProcessingState(vendingMachine));
    }

    @Override
    public void insertCoin(Coin coin) {
        throw new IllegalStateException("Select product first");
    }

    @Override
    public void cancel() {
        // not required
    }
}
