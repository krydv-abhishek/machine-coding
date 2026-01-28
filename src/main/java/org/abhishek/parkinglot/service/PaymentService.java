package org.abhishek.parkinglot.service;

import org.abhishek.parkinglot.enums.PaymentMode;
import org.abhishek.parkinglot.model.Payment;
import org.abhishek.parkinglot.model.Ticket;
import org.abhishek.parkinglot.strategy.PriceStrategy;

public class PaymentService {

    private final PriceStrategy priceStrategy;

    public PaymentService(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public Payment completePayment(Ticket ticket, PaymentMode paymentMode) {
        double amount = priceStrategy.calculatePrice(ticket);
        Payment payment = new Payment(amount, paymentMode);
        payment.markSuccess();
        return payment;
    }
}
