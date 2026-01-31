package org.abhishek.vendingmachine.state.impl;

import org.abhishek.vendingmachine.model.Coin;
import org.abhishek.vendingmachine.model.Product;
import org.abhishek.vendingmachine.service.VendingMachine;
import org.abhishek.vendingmachine.state.VendingMachineState;

public class ProcessingState implements VendingMachineState {

    private final VendingMachine vendingMachine;

    public ProcessingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(String productId) {
        throw new IllegalStateException("Product already selected");
    }

    @Override
    public void insertCoin(Coin coin) {
        if (vendingMachine.getInsertedAmount() == 0) {
            vendingMachine.beginTransaction();
        }
        vendingMachine.getPaymentProcessor().acceptCoin(coin, 1);
        vendingMachine.setInsertedAmount(vendingMachine.getInsertedAmount() + coin.getValue());
        Product product = vendingMachine.getProductInventory().getProduct(vendingMachine.getSelectedProductId());

        if (vendingMachine.getInsertedAmount() < product.getPrice()) {
            return;
        }

        int change = vendingMachine.getInsertedAmount() - product.getPrice();
        boolean canProceed = vendingMachine.getPaymentProcessor().canPrepareChange(change)
                && vendingMachine.getProductInventory().canReserve(vendingMachine.getSelectedProductId(), 1);

        if(!canProceed) {
            vendingMachine.rollback();
            return;
        }
        vendingMachine.getProductInventory().reserve(vendingMachine.getSelectedProductId(), 1);
        vendingMachine.getPaymentProcessor().prepareChange(change);

        vendingMachine.commit(change);

    }

    @Override
    public void cancel() {
        vendingMachine.rollback();
    }
}
