package org.abhishek.vendingmachine.service;

import org.abhishek.vendingmachine.model.Coin;
import org.abhishek.vendingmachine.repository.ProductInventory;
import org.abhishek.vendingmachine.state.VendingMachineState;
import org.abhishek.vendingmachine.state.impl.IdleState;

public class VendingMachine {


    private VendingMachineState machineState;
    private final PaymentProcessor paymentProcessor;
    private final ProductInventory productInventory;

    private String selectedProductId;
    private int insertedAmount;


    public VendingMachine(PaymentProcessor paymentProcessor, ProductInventory productInventory) {
        this.machineState = new IdleState(this);
        this.paymentProcessor = paymentProcessor;
        this.productInventory = productInventory;
    }

    public void setState(VendingMachineState vendingMachineState) {
        this.machineState = vendingMachineState;
    }

    public PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }

    public ProductInventory getProductInventory() {
        return productInventory;
    }

    public String getSelectedProductId() {
        return selectedProductId;
    }

    public void setSelectedProductId(String selectedProductId) {
        this.selectedProductId = selectedProductId;
    }

    public int getInsertedAmount() {
        return insertedAmount;
    }

    public void setInsertedAmount(int insertedAmount) {
        this.insertedAmount = insertedAmount;
    }

    public void selectProduct(String productId) {
        machineState.selectProduct(productId);
    }

    public void insertCoin(Coin coin) {
        machineState.insertCoin(coin);
    }

    public void cancel() {
        machineState.cancel();
    }


}
