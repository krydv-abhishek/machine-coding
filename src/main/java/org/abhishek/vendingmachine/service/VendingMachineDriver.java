package org.abhishek.vendingmachine.service;

import org.abhishek.vendingmachine.model.Coin;
import org.abhishek.vendingmachine.model.Product;
import org.abhishek.vendingmachine.model.Slot;
import org.abhishek.vendingmachine.repository.CoinInventory;
import org.abhishek.vendingmachine.repository.ProductInventory;

public class VendingMachineDriver {

    public static void main(String[] args) {

        CoinInventory coinInventory = new CoinInventory();
        PaymentProcessor paymentProcessor = new PaymentProcessor(coinInventory);

        ProductInventory productInventory = new ProductInventory();
        Slot slot = new Slot("A1");
        slot.setProduct(new Product("abc","Kitkat", 10));
        productInventory.addSlot(slot, 20);

        VendingMachine vendingMachine = new VendingMachine(paymentProcessor, productInventory);

        vendingMachine.selectProduct("abc");
        vendingMachine.insertCoin(Coin.FIVE);
        vendingMachine.insertCoin(Coin.TWO);
        vendingMachine.insertCoin(Coin.FIVE);

    }
}
