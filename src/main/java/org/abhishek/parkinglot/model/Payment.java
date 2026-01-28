package org.abhishek.parkinglot.model;

import org.abhishek.parkinglot.enums.PaymentMode;
import org.abhishek.parkinglot.enums.PaymentStatus;

import java.util.UUID;

public class Payment {

    private String id;
    private PaymentStatus status;
    private double amount;
    private PaymentMode mode;

    public Payment(double amount, PaymentMode mode) {
        this.id = UUID.randomUUID().toString();
        this.status = PaymentStatus.STARTED;
        this.amount = amount;
        this.mode = mode;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMode getMode() {
        return mode;
    }

    public void setMode(PaymentMode mode) {
        this.mode = mode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void markSuccess() {
        this.status = PaymentStatus.COMPLETED;
    }

}
