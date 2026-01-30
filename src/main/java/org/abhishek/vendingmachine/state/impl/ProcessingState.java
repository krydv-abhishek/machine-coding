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
        vendingMachine.getPaymentProcessor().acceptCoin(coin, 1);
        vendingMachine.setInsertedAmount(vendingMachine.getInsertedAmount() + coin.getValue());

        Product product = vendingMachine.getProductInventory().getProduct(vendingMachine.getSelectedProductId());

        if (vendingMachine.getInsertedAmount() >= product.getPrice()) {
            int change = vendingMachine.getInsertedAmount() - product.getPrice();
            if(!vendingMachine.getPaymentProcessor().canReturnChange(change)) {
                vendingMachine.getPaymentProcessor().refund(vendingMachine.getInsertedAmount());
                vendingMachine.setState(new IdleState(vendingMachine));
                return;
            }

            vendingMachine.getProductInventory().deduct(vendingMachine.getSelectedProductId());
            System.out.println("Dispensing product");
            vendingMachine.getPaymentProcessor().returnChange(change);
            vendingMachine.setState(new IdleState(vendingMachine));
        }
    }

    @Override
    public void cancel() {
        vendingMachine.getPaymentProcessor().refund(vendingMachine.getInsertedAmount());
        vendingMachine.setState(new IdleState(vendingMachine));
    }
}
