package org.abhishek.vendingmachine.junittest;

import org.abhishek.vendingmachine.model.Product;
import org.abhishek.vendingmachine.model.Slot;
import org.abhishek.vendingmachine.repository.ProductInventory;
import org.abhishek.vendingmachine.service.PaymentProcessor;
import org.abhishek.vendingmachine.service.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendingMachineTest {

    private VendingMachine vendingMachineUnderTest;

    @BeforeEach
    public void setUp() {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        ProductInventory productInventory = new ProductInventory();
        Slot slot = new Slot("A1");
        slot.setProduct(new Product("abc", "Kitkat", 10));
        productInventory.addSlot(slot, 20);

        vendingMachineUnderTest = new VendingMachine(paymentProcessor, productInventory);
    }

    @Test
    void testSelectProduct() {
        vendingMachineUnderTest.selectProduct("A1");

    }


}
