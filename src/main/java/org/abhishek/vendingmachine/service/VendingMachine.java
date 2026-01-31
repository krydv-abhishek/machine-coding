package org.abhishek.vendingmachine.service;

import org.abhishek.vendingmachine.model.Coin;
import org.abhishek.vendingmachine.repository.ProductInventory;
import org.abhishek.vendingmachine.state.VendingMachineState;
import org.abhishek.vendingmachine.state.impl.IdleState;

import java.util.concurrent.locks.ReentrantLock;

public class VendingMachine {

    private final ReentrantLock lock = new ReentrantLock();
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
        /*
        \
           One lock
           One owner (VendingMachine)
           Linear flow
           Deadlock risk = zero
         */
        lock.lock();
        try {
            machineState.selectProduct(productId);
        } finally {
            lock.unlock();
        }
    }

    public void insertCoin(Coin coin) {
        lock.lock();
        try {
            machineState.insertCoin(coin);
        } finally {
            lock.unlock();
        }
    }

    public void cancel() {
        lock.lock();
        try {
            machineState.cancel();
        } finally {
            lock.unlock();
        }
    }

    public void beginTransaction() {
        paymentProcessor.begin();
        productInventory.begin();
    }

    public void commit(int change) {
        paymentProcessor.commit();
        productInventory.commit();
        System.out.println("Dispensing product");
        paymentProcessor.returnChange(change);
        this.setState(new IdleState(this));

    }

    public void rollback() {
        paymentProcessor.rollback();
        productInventory.rollback();
        paymentProcessor.refund(this.insertedAmount);
        this.setState(new IdleState(this));
    }


}
